package com.learn.freeim.service;

import com.learn.freeim.entity.SysUser;

public interface UserService extends BaseService<SysUser>{
	void usernameHasExist(SysUser user);
	SysUser queryUserByUsername(SysUser user);
}
