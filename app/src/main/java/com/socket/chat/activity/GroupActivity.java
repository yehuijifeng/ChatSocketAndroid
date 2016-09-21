package com.socket.chat.activity;

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

import com.socket.chat.R;
import com.socket.chat.appliaction.ChatAppliaction;
import com.socket.chat.bean.MessageBean;

/**
 * Created by LuHao on 2016/9/19.
 */
public class GroupActivity extends AppCompatActivity {

    private LinearLayout chat_ly;
    private TextView left_text, right_view;
    private EditText chat_et;
    private Button send_btn;
    private ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        chat_ly = (LinearLayout) findViewById(R.id.chat_ly);
        chat_et = (EditText) findViewById(R.id.chat_et);
        send_btn = (Button) findViewById(R.id.send_btn);
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatAppliaction.chatServer.sendMessages(chat_et.getText().toString().trim());
                chat_ly.addView(initRightView(chat_et.getText().toString().trim()));
            }
        });
        //添加消息接收队列
        ChatAppliaction.chatServer.setChatHandler(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    MessageBean messageBean = (MessageBean) msg.obj;
                    //发送回来消息后，更新ui
                    chat_ly.addView(messageBean.getUserId() == 1 ? initLeftViewOne(messageBean.getContent()) : initLeftViewTow(messageBean.getContent()));
                }
            }
        });
    }

    /**
     * 靠右的消息
     *
     * @param messageContent
     * @return
     */
    private View initRightView(String messageContent) {
        right_view = new TextView(this);
        right_view.setLayoutParams(layoutParams);
        right_view.setGravity(View.FOCUS_RIGHT);
        right_view.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.ic_friend), null);
        right_view.setText(messageContent);
        return right_view;
    }

    /**
     * 靠左的消息
     *
     * @param messageContent
     * @return
     */
    private View initLeftViewOne(String messageContent) {
        left_text = new TextView(this);
        left_text.setLayoutParams(layoutParams);
        left_text.setGravity(View.FOCUS_LEFT);
        left_text.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.ic_chat), null);
        left_text.setText(messageContent);
        return left_text;
    }

    /**
     * 靠左的消息
     *
     * @param messageContent
     * @return
     */
    private View initLeftViewTow(String messageContent) {
        left_text = new TextView(this);
        left_text.setLayoutParams(layoutParams);
        left_text.setGravity(View.FOCUS_LEFT);
        left_text.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.ic_group), null);
        left_text.setText(messageContent);
        return left_text;
    }
}
