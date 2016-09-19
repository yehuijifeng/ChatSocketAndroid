package com.socket.chat.bean;

import java.io.Serializable;
import java.util.List;

public class UserInfoBean implements Serializable {
	private static final long serialVersionUID = 2L;
	private long userId;// 用户id
	private String userName;// 用户名
	private String likeName;// 昵称
	private String userPwd;// 用户密码
	private String userIcon;// 用户头像
	private List<UserGroupBean> groupList;//当前用户所拥有的群

	public List<UserGroupBean> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<UserGroupBean> groupList) {
		this.groupList = groupList;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLikeName() {
		return likeName;
	}

	public void setLikeName(String likeName) {
		this.likeName = likeName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

}
