package com.learn.freeim.entity;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class SysUser {
	
    private Long userId;

    @NotEmpty(message = "用户名不能为空")
    private String userName;

    @NotEmpty(message = "性别不能为空")
    private String sex;

    @NotEmpty(message = "账号不能为空")
    private String account;

    @NotEmpty(message = "密码不能为空")
    private String password;

    @NotEmpty(message = "邮箱不能为空")
    private String mail;

    private String city;

    private Integer age;

    private String nickname;

    private Date createDate;

    private Date updateDate;
    
    
    private String rePassword;
    
    private String verifyCode;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
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
    
	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	
	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	@Override
	public String toString() {
		return "SysUser [userId=" + userId + ", userName=" + userName + ", sex=" + sex + ", account=" + account
				+ ", password=" + password + ", mail=" + mail + ", city=" + city + ", age=" + age + ", nickname="
				+ nickname + ", createDate=" + createDate + ", updateDate=" + updateDate + ", rePassword=" + rePassword
				+ ", verifyCode=" + verifyCode + "]";
	}

	
    
    
}