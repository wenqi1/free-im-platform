package com.learn.freeim.service;

import com.learn.freeim.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 公共服务
 * @param <E>服务类实体类
 * @param <M>服务类映射类
 */
public interface  BaseService<E,M extends BaseMapper<E>> {

    void insert(E e);

    void deleteById(Object id);

    void deleteByExample(E e);

    void updateById(E e);

    void updateByIdSelective(E e);

    E selectById(Object id);

    List<E> selectForList(E e);

    List<E> selectAll();


}
