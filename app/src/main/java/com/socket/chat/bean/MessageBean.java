package com.socket.chat.bean;

public class MessageBean extends UserInfoBean {

    private long messageId;// 消息id
    private long groupId;// 群id
    private boolean isGoup;// 是否是群消息
    private int chatType;// 消息类型;1,文本；2，图片；3，小视频；4，文件；5，地理位置；6，语音；7，视频通话
    private String content;// 文本消息内容
    private String errorMsg;// 错误信息
    private int errorCode;// 错误代码
    private MessageFileBean chatFile;// 消息附件

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
