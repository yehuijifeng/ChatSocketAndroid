package com.socket.chat.bean;

/**
 * 用户的群管理
 * @author Luhao
 *
 */
public class UserGroupBean {
	private int groupId;//群id
	private int userId;//用户id
	private int groupNumberByMax;//总人数
	private int groupNumberByCurrent;//当前人数
	private int groupType;//群类型，朋友，家人，同事
	private int groupUserType;//当前用户在群里面的角色，管理员-2，创建者-1，普通人等级：1，2，3，4，5

	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGroupNumberByMax() {
		return groupNumberByMax;
	}
	public void setGroupNumberByMax(int groupNumberByMax) {
		this.groupNumberByMax = groupNumberByMax;
	}
	public int getGroupNumberByCurrent() {
		return groupNumberByCurrent;
	}
	public void setGroupNumberByCurrent(int groupNumberByCurrent) {
		this.groupNumberByCurrent = groupNumberByCurrent;
	}
	public int getGroupType() {
		return groupType;
	}
	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}
	public int getGroupUserType() {
		return groupUserType;
	}
	public void setGroupUserType(int groupUserType) {
		this.groupUserType = groupUserType;
	}
	
}
