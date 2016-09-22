package com.socket.chat;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.text.format.Formatter;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by Luhao on 2016/6/22.
 * 本机文件操作工具类
 */
public class FileUtils {
    /**
     * 判断SDCard是否可用
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

    }

    /**
     * 是否存在该文件夹
     */
    public boolean isHaveFileDirectory(String path) {
        return new File(path).isDirectory();
    }

    /**
     * 是否存在该文件
     */
    public boolean isHaveFile(String path) {

        File file = new File(path);
        if (file.isFile()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * sd卡根目录路径
     */
    public static File getSDFile() {
        return Environment.getExternalStorageDirectory();
    }

    /**
     * 获得SD卡总大小
     */
    public String getSDAllSize(Context con) {
        File path = getSDFile();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return Formatter.formatFileSize(con, blockSize * totalBlocks);
    }

    /**
     * 获得sd卡剩余容量，即可用大小
     */
    public String getSDRemainingSize(Context con) {
        File path = getSDFile();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return Formatter.formatFileSize(con, blockSize * availableBlocks);
    }

    /**
     * 获得机身内存总大小
     */
    public String getRomAllSize(Context con) {
        File path = getSDFile();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return Formatter.formatFileSize(con, blockSize * totalBlocks);
    }

    /**
     * 获得机身可用内存
     */
    public String getRomRemainingSize(Context con) {
        File path = getSDFile();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return Formatter.formatFileSize(con, blockSize * availableBlocks);
    }



    /**
     * 添加文件到指定路径,该文件创建出来是空的
     *
     * @return
     */
    public boolean createFile(String filePath, String fileContent) {
        if (TextUtils.isEmpty(fileContent) || TextUtils.isEmpty(filePath)) return false;
        try {
            File file1 = new File(filePath);
            if (!file1.exists()) {
                file1.createNewFile();
            } else {
                file1.delete();
                file1.createNewFile();
            }
            FileOutputStream fout1 = new FileOutputStream(filePath);
            byte[] bytes1 = fileContent.getBytes();
            fout1.write(bytes1);
            fout1.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //读SD中的文件
    public String readZhongduanFile(String filePath) {
        String res = "";
        try {
            File file = new File(filePath);
            if (!file.exists()) return null;
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String lineStr;
            while ((lineStr = bufferedReader.readLine()) != null) {
                res += lineStr;
            }
            inputStreamReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
