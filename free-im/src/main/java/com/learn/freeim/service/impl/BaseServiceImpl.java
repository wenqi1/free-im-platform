package com.learn.freeim.service.impl;

import com.learn.freeim.mapper.BaseMapper;
import com.learn.freeim.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseServiceImpl<E, M extends BaseMapper<E>> implements BaseService<E, M> {

    @Autowired
    private M baseMapper;

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
