package com.changtai.Utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qjcjo on 2018/3/24.
 */

public class SaveDataToFile {
    public File file;
    public static BufferedOutputStream bufferedOutputStream = null;
    public static FileOutputStream fileOutputStream = null;
    public void saveFile(String fileName, String data, int type){
        String pata = Environment.getExternalStorageDirectory().toString() + "/.ChangTai/";
        //Log.e("NoFlyZone", pata);
        Log.e("Path", pata);
        File fileMk = new File(pata);
        if (!fileMk.exists())
        {
            fileMk.mkdirs();
        }
        file = new File(pata + File.separator + fileName + ".txt");
        if (!file.exists())
        {
            file = new File(pata + File.separator + fileName + ".txt");
        }
        else
        {
            if (type != 0) {
                file.delete();
                file = new File(pata + File.separator + fileName + ".txt");
            }
        }
        try {
            //String path1 = pata + File.separator + path + ".txt";
            fileOutputStream = new FileOutputStream(file, true);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(data.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            fileOutputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Map<String, String> readFile(String fileName){
        try {
            String filePath = Environment.getExternalStorageDirectory().toString() + "/.ChangTai/" + fileName;
            File file = new File(filePath);
            if (!file.exists()) {
                return null;
            }
            InputStream fileIn = new FileInputStream(filePath);
            InputStreamReader readerIn = new InputStreamReader(fileIn);
            BufferedReader reader = new BufferedReader(readerIn);
            //StringBuilder builder = new StringBuilder("");
            String result;
            Map<String, String> dMap = new HashMap<String, String>();
            while ((result = reader.readLine()) != null) {
                String[] data = result.split("=");
                dMap.put(data[0], data[1]);
                //builder.append(result).append("\r\f");
            }
            return dMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int readUserPassWord(String fileName, String userMessage){
        try {
            String filePath = Environment.getExternalStorageDirectory().toString() + "/.ChangTai/" + fileName;
            File file = new File(filePath);
            if (!file.exists()) {
                return 0;//文件不存在
            }
            InputStream fileIn = new FileInputStream(filePath);
            InputStreamReader readerIn = new InputStreamReader(fileIn);
            BufferedReader reader = new BufferedReader(readerIn);
            //StringBuilder builder = new StringBuilder("");
            String result;
            while ((result = reader.readLine()) != null) {
                if (userMessage.equals(result))
                {
                    return 1;//用户名存在
                }
                else
                {
                    return 2;//用户名不存在
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;//读取错误
    }
}
