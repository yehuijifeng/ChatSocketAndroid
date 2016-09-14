package com.socket.chat.service;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.socket.chat.SocketUrls;
import com.socket.chat.bean.MessageBean;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
    // 由Socket对象得到输出流，并构造PrintWriter对象
    PrintWriter printWriter;
    InputStream input;
    OutputStream output;
    DataOutputStream dataOutputStream;

    public ChatServer(Handler handler) {
        this.handler = handler;
        initMessage();
        initChatServer();
    }

    private void initChatServer() {
        receiveMessage();//开个线程接收消息
    }

    private void initMessage() {
        messageBean = new MessageBean();
        messageBean.setUserId(1);
        messageBean.setMessageId(1);
        messageBean.setChatType(1);
        messageBean.setUserName("夜辉疾风");
    }

    public void sendMessage(String contentMsg) {
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
            String messageJson = gson.toJson(messageBean);
            //printWriter.println(messageJson);
            //printWriter.flush();
            /**
             * 因为服务器那边的readLine()为阻塞读取
             * 如果它读取不到换行符或者输出流结束就会一直阻塞在那里
             *
             * */

            messageJson += "\n";
            output.write(messageJson.getBytes("utf-8"));// 换行打印
            output.flush(); // 刷新输出流，使Server马上收到该字符串
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("test", "错误：" + e.toString());
        }
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
                    // 由Socket对象得到输入流，并构造相应的BufferedReader对象
                    printWriter = new PrintWriter(socket.getOutputStream());
                    input = socket.getInputStream();
                    output = socket.getOutputStream();
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    // 从客户端获取信息
                    BufferedReader bff = new BufferedReader(new InputStreamReader(input));
                    // 读取发来服务器信息
                    String line;
                    while (true) {
                        Thread.sleep(500);
                        // 获取客户端的信息
                        while ((line = bff.readLine()) != null) {
                            Log.i("socket", "内容 : " + line);
                            Message message = handler.obtainMessage();
                            message.obj = line;
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

    /**
     * 将字节数组转换成对象
     *
     * @param bytes
     * @return
     */
    private Object byteToObject(byte[] bytes) {
        if (bytes == null)
            return null;
        Object obj = null;
        try {
            // bytearray to object
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);
            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 将输入流穿换成字节数组
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static byte[] readBytes(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int n;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

}
