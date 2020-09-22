package com.learn.freeim.entity;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

public class FriendsGroupManager {
    
    private Long groupId;

    @NotEmpty(message = "用户不能为空")
    private Long belongUserId;

    @NotEmpty(message = "组名不能为空")
    private String groupName;

    private Date createDate;

    private Date updateDate;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getBelongUserId() {
        return belongUserId;
    }

    public void setBelongUserId(Long belongUserId) {
        this.belongUserId = belongUserId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "FriendsGroupManager [groupId=" + groupId + ", belongUserId=" + belongUserId + ", groupName=" + groupName
                + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
    }
    
    
}