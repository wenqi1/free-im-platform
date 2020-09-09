package com.learn.freeim.mapper;

import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
public interface BaseMapper<E> extends Mapper<E> {

}
