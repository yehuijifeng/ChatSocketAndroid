package com.socket.chat.appliaction;

import android.app.Application;

import com.socket.chat.bean.UserInfoBean;
import com.socket.chat.service.ChatServer;

/**
 * Created by Luhao on 2016/9/18.
 */
public class ChatAppliaction extends Application {

    public static ChatServer chatServer;
    public static UserInfoBean userInfoBean;

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
