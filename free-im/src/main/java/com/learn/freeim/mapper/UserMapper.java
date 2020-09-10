package com.learn.freeim.mapper;

import com.learn.freeim.entity.SysUser;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
public interface UserMapper extends Mapper<SysUser>{

}
