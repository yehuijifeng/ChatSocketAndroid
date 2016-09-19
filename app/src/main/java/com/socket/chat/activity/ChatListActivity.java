package com.socket.chat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.socket.chat.R;

/**
 * Created by LuHao on 2016/9/19.
 */
public class ChatListActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout friend_ly, group_ly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatlist);
        friend_ly = (LinearLayout) findViewById(R.id.friend_ly);
        group_ly = (LinearLayout) findViewById(R.id.group_ly);
        friend_ly.setOnClickListener(this);
        group_ly.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.friend_ly://单聊
                Intent intent = new Intent(ChatListActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.group_ly://群聊
                break;
        }
    }
}
