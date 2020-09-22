package com.learn.freeim.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.freeim.entity.UserFriends;
import com.learn.freeim.mapper.UserFriendsMapper;
import com.learn.freeim.service.UserFriendsService;

@Service
public class UserFriendsServiceImpl extends BaseServiceImpl<UserFriends> implements UserFriendsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserFriendsServiceImpl.class);
	@Autowired
	private UserFriendsMapper userFriendsMapper;
}
