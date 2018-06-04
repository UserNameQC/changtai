package com.changtai.Utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by Qiao on 2018/2/6.
 */

public class HttpUtils {

    public void getInforPost()
    {

    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param urlHttp 发送请求的 URL
     * @param param   请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public String sendPost(String urlHttp, String param)
    {
        //Entity.log("param=" + param);
        HttpURLConnection conn = null;
        try
        {
            URL url = new URL(urlHttp);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            PrintWriter writer = new PrintWriter(conn.getOutputStream());
            writer.write(param);
            writer.flush();
            writer.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
            return sb.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.i("KZFC", "===========ERROR=================");
            if (conn != null)
            {
                Map<String, List<String>> map = conn.getHeaderFields();
                if (!map.isEmpty())
                {
                    for (String key : map.keySet())
                    {
                        StringBuilder sb = new StringBuilder();
                        List<String> list = map.get(key);
                        for (String str : list)
                        {
                            sb.append(str);
                        }
                        Log.i("KZFC", key + ":" + sb.toString());
                    }
                }
            }
            Log.i("KZFC", "===========END=================");
            try
            {
                return conn.getResponseCode() + "";
            }
            catch (IOException e1)
            {
                e1.printStackTrace();
                return null;
            }
        }
    }
}
