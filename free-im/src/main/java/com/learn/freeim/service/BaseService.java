package com.learn.freeim.service;

import java.util.List;

/**
 * 公共服务
 * @param <E>服务类实体类
 */
public interface  BaseService<E> {

    void insert(E e);

    void deleteById(Object id);

    void deleteByExample(E e);

    void updateById(E e);

    void updateByIdSelective(E e);

    E selectById(Object id);

    List<E> selectForList(E e);

    List<E> selectAll();


}
