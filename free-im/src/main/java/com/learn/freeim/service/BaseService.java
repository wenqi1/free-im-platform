package com.learn.freeim.service;

import java.util.List;

import com.learn.freeim.mapper.BaseMapper;

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
