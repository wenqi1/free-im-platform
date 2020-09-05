package com.learn.freeim.util;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class SerializationUtil {

    private static final ThreadLocal<LinkedBuffer> BUFFERS = new ThreadLocal();

    private static final Schema<SerializeDeserializeWrapper> WRAPPER_SCHEMA = RuntimeSchema.getSchema(SerializeDeserializeWrapper.class);

    /**
     * 序列化对象
     */
    public static byte[] serialize(Object obj) {
        Class<?> clazz = (Class<?>) obj.getClass();
        LinkedBuffer buffer = BUFFERS.get();
        if (buffer == null) {
            buffer = LinkedBuffer.allocate(512);
            //存储buffer到线程局部变量中，避免每次序列化操作都分配内存提高序列化性能
            BUFFERS.set(buffer);
        }
        try {
            Object serializeObject = obj;
            Schema schema = WRAPPER_SCHEMA;
            //Protostuff不支持序列化/反序列化数组、集合等对象,特殊处理
            if (clazz.isArray() || Collection.class.isAssignableFrom(clazz)
                    || Map.class.isAssignableFrom(clazz) || Set.class.isAssignableFrom(clazz)) {
                serializeObject = SerializeDeserializeWrapper.builder(obj);
            } else {
                schema = RuntimeSchema.getSchema(clazz);
            }
            return ProtostuffIOUtil.toByteArray(serializeObject, schema, buffer);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化对象
     */
    public static <T> T deserialize(Class<T> clazz, byte[] data) {
        if (clazz.isArray() || Collection.class.isAssignableFrom(clazz)
                || Map.class.isAssignableFrom(clazz) || Set.class.isAssignableFrom(clazz)) {//Protostuff 不支持序列化/反序列化数组、集合等对象,特殊处理
            SerializeDeserializeWrapper<T> wrapper = new SerializeDeserializeWrapper<>();
            ProtostuffIOUtil.mergeFrom(data, wrapper, WRAPPER_SCHEMA);
            return wrapper.getData();
        } else {
            Schema<T> schema = RuntimeSchema.getSchema(clazz);
            T message = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(data, message, schema);
            return message;
        }
    }

    /**
     * 序列化/反序列化对象包装类专为基于 Protostuff 进行序列化/反序列化而定义；
     * Protostuff是基于POJO进行序列化和反序列化操作，如果进行序列化/反序列化的对象不知道其类型，就不能进行序列化/反序列化；
     * 比如Map、List、String、Enum等是不能进行正确的序列化/反序列化，因此需要映入一个包装类，把这些需要序列化/反序列化的对象放到这个包装类中。
     */
    static class SerializeDeserializeWrapper<T> {

        private T data;

        public static <T> SerializeDeserializeWrapper<T> builder(T data) {
            SerializeDeserializeWrapper<T> wrapper = new SerializeDeserializeWrapper<>();
            wrapper.setData(data);
            return wrapper;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

    }
}
