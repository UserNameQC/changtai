package com.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * HTTP通讯帮助类
 */
public class WebMethodHelper {

    public static String getStringByWebMethodPost(String path, Map<String, String> params) throws Exception {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.setRequestProperty("contentType", "utf-8");
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            if (!params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    dataOutputStream.write(String.format("%s=%s&", entry.getKey(), entry.getValue()).getBytes());
                }
                dataOutputStream.write(String.format("a123456=a123456").getBytes());
            }
            dataOutputStream.flush();
            dataOutputStream.close();
            //得到响应代码
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line);
                }
                return sb.toString();
            }
            throw new Exception(String.format("%s,服务器返回错误代码",path));
        } finally {
            connection.disconnect();
        }
    }

    /**
     * 以GET方法请求服务器数据
     * @param path uri
     * @return 数据字符串
     * @throws Exception
     */
    public static String getStringByWebMethodGet(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection connection =(HttpURLConnection) url.openConnection();
        try{
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            //connection.setDoOutput(true);
            connection.connect();
            //得到响应代码
            int responseCode = connection.getResponseCode();
            if(responseCode==200){
                InputStream inputStream = connection.getInputStream();
                byte[] bytes = new byte[10000];
                int length = inputStream.read(bytes);
                inputStream.close();
                if(length==-1){
                    return "";
                }
                String s =new String(bytes,0,length);
                return s;
            }
            throw new Exception(String.format("%s,服务器返回错误代码",path));
        }
        finally {
            connection.disconnect();
        }
    }

}
