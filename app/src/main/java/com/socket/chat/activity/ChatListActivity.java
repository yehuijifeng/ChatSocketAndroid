package com.socket.chat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.socket.chat.FileUtils;
import com.socket.chat.R;
import com.socket.chat.appliaction.ChatAppliaction;

/**
 * Created by LuHao on 2016/9/19.
 */
public class ChatListActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout friend_ly, group_ly, file_ly;
    private final String filePath = FileUtils.getSDFile() + "/" + "test.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatlist);
        friend_ly = (LinearLayout) findViewById(R.id.friend_ly);
        group_ly = (LinearLayout) findViewById(R.id.group_ly);
        file_ly = (LinearLayout) findViewById(R.id.file_ly);
        friend_ly.setOnClickListener(this);
        group_ly.setOnClickListener(this);
        file_ly.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.friend_ly://单聊
                Intent intent = new Intent(ChatListActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.group_ly://群聊
                Intent intent1 = new Intent(ChatListActivity.this, GroupActivity.class);
                startActivity(intent1);
                break;
            case R.id.file_ly://传文件
                ChatAppliaction.chatServer.sendFileMessage(filePath);
                break;
        }
    }
}
