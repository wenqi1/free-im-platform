package com.learn.freeim.entity;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

public class UserFriends {
    
    @NotEmpty(message = "用户不能为空")
    private Long userId;

    @NotEmpty(message = "好友不能为空")
    private Long friendUserId;

    @NotEmpty(message = "分组不能为空")
    private Long groupId;

    private String remark;

    private Date createDate;

    private Date updateDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendUserId() {
        return friendUserId;
    }

    public void setFriendUserId(Long friendUserId) {
        this.friendUserId = friendUserId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        return "UserFriends [userId=" + userId + ", friendUserId=" + friendUserId + ", groupId=" + groupId + ", remark="
                + remark + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
    }
    
}