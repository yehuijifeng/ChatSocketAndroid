package com.socket.chat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.socket.chat.R;
import com.socket.chat.appliaction.ChatAppliaction;
import com.socket.chat.service.ChatServer;

/**
 * Created by Luhao on 2016/9/18.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText chat_name_text, chat_pwd_text;
    private Button chat_login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        chat_name_text = (EditText) findViewById(R.id.chat_name_text);
        chat_pwd_text = (EditText) findViewById(R.id.chat_pwd_text);
        chat_login_btn = (Button) findViewById(R.id.chat_login_btn);
        chat_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getLogin(chat_name_text.getText().toString().trim(), chat_pwd_text.getText().toString().trim()) == 1) {
                    getChatServer();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else if (getLogin(chat_name_text.getText().toString().trim(), chat_pwd_text.getText().toString().trim()) == 2) {
                    getChatServer();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private int getLogin(String name, String pwd) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            return 0;
        } else if (name.equals("admin") && pwd.equals("1")) {
            return 1;
        } else if (name.equals("admin") && pwd.equals("2")) {
            return 2;
        } else {
            return -1;
        }
    }

    private void getChatServer() {
        ChatAppliaction.chatServer = new ChatServer();
    }

}
