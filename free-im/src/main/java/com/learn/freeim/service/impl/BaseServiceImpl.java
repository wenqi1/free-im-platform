package com.learn.freeim.service.impl;

import com.learn.freeim.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseServiceImpl<E> implements BaseService<E> {

    @Autowired
    private Mapper<E> baseMapper;

    private Class<E> clazz;

    public BaseServiceImpl() {
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType pType = (ParameterizedType) type;
        this.clazz = (Class<E>) pType.getActualTypeArguments()[0];
    }

    @Override
    public void insert(E e) {
        baseMapper.insert(e);
    }

    @Override
    public void deleteById(Object id) {
        baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteByExample(E e) {
        baseMapper.deleteByExample(e);
    }

    @Override
    public void updateById(E e) {
        baseMapper.updateByPrimaryKey(e);
    }

    @Override
    public void updateByIdSelective(E e) {
        baseMapper.updateByPrimaryKeySelective(e);
    }

    @Override
    public E selectById(Object id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<E> selectForList(E e) {
        return baseMapper.selectByExample(e);
    }

    @Override
    public List<E> selectAll() {
        return baseMapper.selectAll();
    }
}
