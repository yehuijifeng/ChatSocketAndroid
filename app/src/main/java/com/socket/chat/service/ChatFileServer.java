package com.socket.chat.service;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.socket.chat.bean.MessageBean;
import com.socket.chat.bean.MessageFileBean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Luhao on 2016/9/21.
 */
public class ChatFileServer {

    private MessageFileBean messageFileBean;
    private Gson gson = new Gson();

    public void sendFileMessage(Socket socket, String filePath, Handler handler, MessageBean messageBean) {
        if (TextUtils.isEmpty(filePath)) return;
        try {
            if (socket == null) {
                Message message = handler.obtainMessage();
                message.what = 1;
                message.obj = "服务器已经关闭";
                handler.sendMessage(message);
                return;
            }
            File file = new File(filePath); //要传输的文件路径
            if (!file.exists()) {
                //说明不存在该文件
                return;
            }
            if (file.isDirectory()) {
                //说明该文件是一个文件夹
                return;
            }
            messageFileBean = new MessageFileBean();
            messageFileBean.setFileLength(file.length());
            messageFileBean.setFileName("test");
            messageFileBean.setFileId(1);
            messageFileBean.setFileType("image");
            messageFileBean.setFileTitle("gif");
            //messageFileBean.setFileByte(getBytes(file));
            messageBean.setChatFile(messageFileBean);
            OutputStream outputStream = socket.getOutputStream();
            String json = gson.toJson(messageBean) + "\n";
            //1、发送文件信息实体类
            outputStream.write(json.getBytes("utf-8"));
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) != -1) {
                //2、把文件写入socket输出流
                outputStream.write(b, 0, length);
            }
            fis.close();
            //outputStream.write("\n".getBytes());
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("test", "错误：" + e.toString());
        }
    }


    private byte[] getBytes(File file) {
        if (file == null) return null;
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer;
    }

}
