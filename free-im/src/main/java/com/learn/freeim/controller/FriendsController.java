package com.learn.freeim.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.freeim.entity.FriendsGroupManager;
import com.learn.freeim.entity.Result;
import com.learn.freeim.entity.SysUser;
import com.learn.freeim.entity.UserFriends;
import com.learn.freeim.exception.CommonException;
import com.learn.freeim.service.FriendsGroupManagerService;
import com.learn.freeim.service.UserFriendsService;
import com.learn.freeim.service.UserService;
import com.learn.freeim.util.SnowflakeIdGenerator;

@RestController
@RequestMapping("friend")
public class FriendsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FriendsController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private FriendsGroupManagerService friendsGroupManagerService;
    @Autowired
    private UserFriendsService userFriendsService;

    @Autowired
    private SnowflakeIdGenerator idGenerator;

    @RequestMapping("addGroup")
    public Result<String> addGroup(@Validated FriendsGroupManager group) {
        SysUser user = userService.selectById(group.getBelongUserId());
        if(user == null){
            throw new CommonException("1005", LOGGER);
        }
        Date now = new Date();
        group.setCreateDate(now);
        group.setUpdateDate(now);
        group.setGroupId(idGenerator.nextId());
        friendsGroupManagerService.insert(group);
        return Result.success();
    }
    
    @RequestMapping("deleteGroup")
    public Result<String> deleteGroup(FriendsGroupManager group) {
        friendsGroupManagerService.deleteGroupRequestCheck(group);
        friendsGroupManagerService.deleteGroup(group);
        return Result.success();
    }
    
    @RequestMapping("addFriend")
    public Result<String> addFriend(@Validated UserFriends friend) {
        SysUser friendUser = userService.selectById(friend.getFriendUserId());
        if(friendUser == null){
            throw new CommonException("1007", LOGGER);
        }
        SysUser user = userService.selectById(friend.getUserId());
        if(user == null){
            throw new CommonException("1005", LOGGER);
        }
        FriendsGroupManager group = friendsGroupManagerService.selectById(friend.getGroupId());
        if(group == null){
            throw new CommonException("1008", LOGGER);
        }
        Date now = new Date();
        friend.setCreateDate(now);
        friend.setUpdateDate(now);
        userFriendsService.insert(friend);
        return Result.success();
    }

}
