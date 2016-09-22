package com.socket.chat.service;

import android.util.Log;

import com.socket.chat.bean.MessageBean;
import com.socket.chat.bean.MessageFileBean;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Luhao on 2016/9/21.
 */
public class ChatFileServer {
    private MessageFileBean messageFileBean;

    public void getFilePath(MessageBean messageBean, Socket socket, String filePath) {
        boolean bool = false;
        FileInputStream fis = null;
        try {
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
            messageFileBean.setFileName("test.gif");
            messageFileBean.setFileId(1);
            messageFileBean.setFileType("image");
            messageFileBean.setFileTitle("gif");
            messageFileBean.setFileByte(getBytes(file));
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            fis = new FileInputStream(file);
            byte[] sendBytes = new byte[1024];
            int length;
            double sumL = 0;
            while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0) {
                sumL += length;
                Log.i("socket", "已传输：" + ((sumL / messageFileBean.getFileLength()) * 100) + "%");
                dos.write(sendBytes, 0, length);
                dos.flush();
            }

            //虽然数据类型不同，但JAVA会自动转换成相同数据类型后在做比较
            if (sumL == messageFileBean.getFileLength()) {
                bool = true;
            }
        } catch (Exception e) {
            Log.i("socket", "客户端文件传输异常");
            bool = false;
            e.printStackTrace();
        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        Log.i("socket", bool ? "成功" : "失败");
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
