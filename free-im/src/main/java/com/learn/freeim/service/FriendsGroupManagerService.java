package com.learn.freeim.service;

import com.learn.freeim.entity.FriendsGroupManager;

public interface FriendsGroupManagerService extends BaseService<FriendsGroupManager>{
	void deleteGroupRequestCheck(FriendsGroupManager friendsGroupManager);
	void deleteGroup(FriendsGroupManager friendsGroupManager);
	FriendsGroupManager queryGroupById(Long groupId);
}
