package com.socket.chat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.socket.chat.service.ChatServer;

public class MainActivity extends AppCompatActivity {
    private LinearLayout chat_ly;
    private TextView left_text, right_view;
    private EditText chat_et;
    private Button send_btn;
    private ChatServer chatServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chat_ly = (LinearLayout) findViewById(R.id.chat_ly);
        chat_et = (EditText) findViewById(R.id.chat_et);
        send_btn = (Button) findViewById(R.id.send_btn);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatServer.sendMessage(chat_et.getText().toString().trim());
            }
        });
        initTextView();
        chatServer = new ChatServer(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    //发送回来消息后，更新ui
                    right_view.append(msg.obj.toString());
                }
            }
        });
    }
    private void initTextView() {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        left_text = new TextView(this);
        right_view = new TextView(this);
        left_text.setLayoutParams(layoutParams);
        right_view.setLayoutParams(layoutParams);
        left_text.setGravity(View.FOCUS_LEFT);
        right_view.setGravity(View.FOCUS_RIGHT);
    }


}
