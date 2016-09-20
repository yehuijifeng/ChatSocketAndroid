package com.socket.chat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                int status = getLogin(chat_name_text.getText().toString().trim(), chat_pwd_text.getText().toString().trim());
                if (status == -1 || status == 0) {
                    Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_LONG).show();
                    return;
                }
                getChatServer(getLogin(chat_name_text.getText().toString().trim(), chat_pwd_text.getText().toString().trim()));
                Intent intent = new Intent(LoginActivity.this, ChatListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * 返回登陆状态，1为用户，2为另一个用户，这里模拟出两个用户互相通讯
     *
     * @param name
     * @param pwd
     * @return
     */
    private int getLogin(String name, String pwd) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            return 0;//没有输入完整密码
        } else if (name.equals("admin") && pwd.equals("1")) {
            return 1;//用户1
        } else if (name.equals("admin") && pwd.equals("2")) {
            return 2;//用户2
        } else if (name.equals("admin") && pwd.equals("3")) {
            return 3;//用户2
        } else {
            return -1;//密码错误
        }
    }

    /**
     * 实例化一个聊天服务
     *
     * @param status
     */
    private void getChatServer(int status) {
        ChatAppliaction.chatServer = new ChatServer(status);
    }

}
