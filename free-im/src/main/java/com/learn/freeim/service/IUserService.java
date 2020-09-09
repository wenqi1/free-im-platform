package com.learn.freeim.service;

import com.learn.freeim.entity.SysUser;
import com.learn.freeim.entity.vo.UserLogin;
import com.learn.freeim.entity.vo.UserVO;
import com.learn.freeim.mapper.BaseMapper;

public interface IUserService extends BaseService<SysUser>{
	public String checkRegister(UserVO user);
	public SysUser convertUser(UserVO user);
	public String checkLogin(UserLogin user);
}
