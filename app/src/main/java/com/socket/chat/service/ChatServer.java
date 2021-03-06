package com.socket.chat.service;


import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.socket.chat.appliaction.ChatAppliaction;
import com.socket.chat.bean.MessageBean;
import com.socket.chat.bean.UserInfoBean;
import com.socket.chat.urls.SocketUrls;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 聊天服务
 *
 * @author Luhao
 */
public class ChatServer {
    private Socket socket;
    private Handler handler;
    private MessageBean messageBean;
    private Gson gson = new Gson();
    private InputStream input;
    private OutputStream output;

    //在socket初始化成功以后发送一个空消息让服务器先保存该用户的信息
    private Handler handlers = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == -1) {
                sendMessages("[USER_LOGIN]");
            }
        }
    };

    public ChatServer(int status) {
        initMessage(status);
        initChatServer();
    }

    /**
     * 消息队列，用于传递消息
     *
     * @param handler
     */
    public void setChatHandler(Handler handler) {
        this.handler = handler;
    }

    private void initChatServer() {
        //开个线程接收消息
        receiveMessage();
    }

    /**
     * 初始化用户信息
     */
    private void initMessage(int status) {

        messageBean = new MessageBean();
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setUserId(2);
        messageBean.setMessageId(1);
        messageBean.setChatType(1);
        messageBean.setChatStyle(1);
        userInfoBean.setUserName("admin");
        userInfoBean.setUserPwd("123123123a");
        //以下操作模仿当用户点击了某个好友展开的聊天界面，将保存用户id和聊天目标用户id
        if (status == 1) {//如果是用户1，那么他就指向用户2聊天
            messageBean.setUserId(1);
            messageBean.setFriendId(2);
        } else if (status == 2) {//如果是用户2，那么他就指向用户1聊天
            messageBean.setUserId(2);
            messageBean.setFriendId(1);
        } else if (status == 3) {//如果是用户2，那么他就指向用户1聊天
            messageBean.setUserId(3);
            messageBean.setFriendId(1);
        }
        messageBean.setGroupId(1);//聊天的目标群id，这里模拟成id为1的群
        ChatAppliaction.userInfoBean = userInfoBean;
    }

    /**
     * 发送消息
     *
     * @param contentMsg
     */
    public void sendMessages(String contentMsg) {
        if (TextUtils.isEmpty(contentMsg)) return;
        try {
            if (socket == null) {
                Message message = handler.obtainMessage();
                message.what = 1;
                message.obj = "服务器已经关闭";
                handler.sendMessage(message);
                return;
            }
            byte[] str = contentMsg.getBytes("utf-8");//将内容转utf-8
            String aaa = new String(str);
            messageBean.setContent(aaa);
            /**
             * 因为服务器那边的readLine()为阻塞读取
             * 如果它读取不到换行符或者输出流结束就会一直阻塞在那里
             * 所以在json消息最后加上换行符，用于告诉服务器，消息已经发送完毕了
             * */
            String messageJson = gson.toJson(messageBean) + "\n";
            output.write(messageJson.getBytes("utf-8"));// 换行打印
            output.flush(); // 刷新输出流，使Server马上收到该字符串
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("test", "错误：" + e.toString());
        }
    }

    /**
     * 发送文件
     *
     * @param path
     */
    public void sendFileMessage(String path) {
        new ChatFileServer().sendFileMessage(socket, path, handler, messageBean);
    }

    /**
     * 接收消息，在子线程中
     */
    private void receiveMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 向本机的8080端口发出客户请求
                    socket = new Socket(SocketUrls.IP, SocketUrls.PORT);
                    input = socket.getInputStream();
                    output = socket.getOutputStream();
                    // 从客户端获取信息
                    BufferedReader bff = new BufferedReader(new InputStreamReader(input));
                    // 读取发来服务器信息
                    String line;
                    Message messages = handlers.obtainMessage();
                    messages.what = -1;
                    handlers.sendMessage(messages);
                    while (true) {
                        Thread.sleep(500);
                        // 获取客户端的信息
                        while ((line = bff.readLine()) != null) {
                            Log.i("socket", "内容 : " + line);
                            MessageBean messageBean = gson.fromJson(line, MessageBean.class);
                            Message message = handler.obtainMessage();
                            message.obj = messageBean;
                            message.what = 1;
                            handler.sendMessage(message);
                        }
                        if (socket == null)
                            break;
                    }
                    output.close();//关闭Socket输出流
                    input.close();//关闭Socket输入流
                    socket.close();//关闭Socket
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("test", "错误：" + e.toString());
                }
            }
        }).start();
    }


    public Socket getSocekt() {
        if (socket == null) return null;
        return socket;
    }
}
