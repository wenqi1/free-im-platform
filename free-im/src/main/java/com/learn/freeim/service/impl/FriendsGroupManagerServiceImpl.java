package com.learn.freeim.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.freeim.entity.FriendsGroupManager;
import com.learn.freeim.entity.SysUser;
import com.learn.freeim.exception.CommonException;
import com.learn.freeim.mapper.FriendsGroupManagerMapper;
import com.learn.freeim.service.FriendsGroupManagerService;
import com.learn.freeim.service.UserService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class FriendsGroupManagerServiceImpl extends BaseServiceImpl<FriendsGroupManager> implements FriendsGroupManagerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FriendsGroupManagerServiceImpl.class);

	@Autowired
    private UserService userService;
	
	@Autowired
	private FriendsGroupManagerMapper groupMapper;
	
    @Override
    public void deleteGroupRequestCheck(FriendsGroupManager friendsGroupManager) {
        FriendsGroupManager group = null;
        SysUser user = null;
        try{
            group = selectById(friendsGroupManager.getGroupId());
            user = userService.selectById(friendsGroupManager.getBelongUserId());
        }catch(Exception e){
            throw new CommonException("1009", e, LOGGER);
        }
        if(group == null){
            throw new CommonException("1008", LOGGER);
        }
        if(user == null){
            throw new CommonException("1005", LOGGER);
        }
    }
    
    @Override
    public void deleteGroup(FriendsGroupManager friendsGroupManager){
        FriendsGroupManager condition = new FriendsGroupManager();
        condition.setGroupId(friendsGroupManager.getGroupId());
        condition.setBelongUserId(friendsGroupManager.getBelongUserId());
        try{
            groupMapper.delete(condition);
        }catch(Exception e){
            throw new CommonException("1010", e, LOGGER);
        }
        
    }
	
    @Override
    public FriendsGroupManager queryGroupById(Long groupId){
        Example example = new Example(FriendsGroupManager.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("groupId", groupId);
        FriendsGroupManager result = null;
        try{
            result = groupMapper.selectOneByExample(example);
        }catch(Exception e){
            throw new CommonException("1003", e, LOGGER);
        }
        return result;
    }
}
