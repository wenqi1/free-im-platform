package com.learn.freeim.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.learn.freeim.entity.SysUser;
import com.learn.freeim.entity.vo.UserLogin;
import com.learn.freeim.entity.vo.UserVO;
import com.learn.freeim.mapper.BaseMapper;
import com.learn.freeim.service.IUserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<SysUser, BaseMapper<SysUser>> implements IUserService{
	
	private static final int USERNAME_LENGTH = 8;
	
	@Override
	public String checkRegister(UserVO user) {
		if(user == null){
			return "用户不能为空";
		}
		if(StringUtils.isEmpty(user.getUsername())){
			return "用户名不能为空";
		}
		if(user.getUsername().length() <= USERNAME_LENGTH){
			return "用户名必须大于8位";
		}
		if(StringUtils.isEmpty(user.getPassword())){
			return "密码不能为空";
		}
		if(!user.getPassword().equals(user.getRePassword())){
			return "确认密码不一致";
		}
		if(StringUtils.isEmpty(user.getNickname())){
			return "昵称不能为空";
		}
		if(StringUtils.isEmpty(user.getMail())){
			return "邮箱不能为空";
		}
		return null;
	}
	
	@Override
	public String checkLogin(UserLogin user) {
		if(user == null){
			return "用户不能为空";
		}
		if(StringUtils.isEmpty(user.getUsername())){
			return "用户名不能为空";
		}
		if(user.getUsername().length() <= USERNAME_LENGTH){
			return "用户名必须大于8位";
		}
		if(StringUtils.isEmpty(user.getPassword())){
			return "密码不能为空";
		}
		return null;
	}
	
	@Override
	public SysUser convertUser(UserVO user){
		SysUser result = new SysUser();
		result.setUserName(user.getUsername());
		result.setPassword(user.getPassword());
		result.setAge(user.getAge());
		result.setAccount(user.getAccount());
		result.setCity(user.getCity());
		result.setMail(user.getMail());
		result.setNickname(user.getNickname());
		result.setSex(user.getSex());
		result.setCreateDate(new Date());
		return result;
	}

}
