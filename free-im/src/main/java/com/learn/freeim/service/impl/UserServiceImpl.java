package com.learn.freeim.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.freeim.entity.SysUser;
import com.learn.freeim.exception.CommonException;
import com.learn.freeim.mapper.UserMapper;
import com.learn.freeim.service.UserService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserServiceImpl extends BaseServiceImpl<SysUser> implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	public void usernameHasExist(SysUser user){
		Example example = new Example(SysUser.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userName", user.getUserName());
		List<SysUser> users = null;
		try{
			users = userMapper.selectByExample(example);
		}catch(Exception e){
			throw new CommonException("1003", e, LOGGER);
		}
		if(users != null && !users.isEmpty()){
			throw new CommonException("1002", LOGGER);
		}
	}
	
	public SysUser queryUserByUsername(SysUser user){
		Example example = new Example(SysUser.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userName", user.getUserName());
		SysUser result = null;
		try{
			result = userMapper.selectOneByExample(example);
		}catch(Exception e){
			throw new CommonException("1003", e, LOGGER);
		}
		return result;
	}
}
