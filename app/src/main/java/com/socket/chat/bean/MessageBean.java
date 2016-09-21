package com.socket.chat.bean;

import java.io.Serializable;

public class MessageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private long messageId;// 消息id
	private long groupId;// 群id
	private boolean isGoup;// 是否是群消息
	private int chatStyle;//0,普通消息；1，群消息；2，讨论组消息；3，系统推送消息；4，好友通知消息
	private int chatType;// 消息类型;1,文本；2，图片；3，小视频；4，文件；5，地理位置；6，语音；7，视频通话
	private String content;// 文本消息内容
	private String errorMsg;// 错误信息
	private int errorCode;// 错误代码
	private int userId;//用户id
	private int friendId;//目标好友id
	private MessageFileBean chatFile;// 消息附件

	public int getChatStyle() {
		return chatStyle;
	}

	public void setChatStyle(int chatStyle) {
		this.chatStyle = chatStyle;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public boolean isGoup() {
		return isGoup;
	}

	public void setGoup(boolean isGoup) {
		this.isGoup = isGoup;
	}

	public int getChatType() {
		return chatType;
	}

	public void setChatType(int chatType) {
		this.chatType = chatType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public MessageFileBean getChatFile() {
		return chatFile;
	}

	public void setChatFile(MessageFileBean chatFile) {
		this.chatFile = chatFile;
	}

}
