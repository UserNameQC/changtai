package com.changtai.Utils;

import com.alibaba.fastjson.JSONObject;
import com.baidu.mapapi.http.HttpClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.params.CoreConnectionPNames;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * HTTP测试基类
 *
 * @author xuzhen
 * @create 2018-03-06 23:44
 */

public class HttpBaseTest {

    public static String sendPost( Map<String, String> headMap, Map<String, Object> dataMap,String url) {
        Map<String, Object> datas= new HashMap<>();
        if(headMap!=null){
            datas.put("head",headMap);
        }
        if(dataMap!=null) {
            datas.put("data", dataMap);
        }
        Map<String, String> map= new HashMap<>();

       map.put("data", new Gson().toJson(datas));
        return  sendPost( map, url);
    }

    /**
     * 向指定URL发送POST请求
     * @param url
     * @param paramMap
     * @return 响应结果
     */
    public static String sendPost( Map<String, String> paramMap,String url) {
        System.out.println("请求参数"+JSONObject.toJSONString(paramMap));
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // conn.setRequestProperty("Charset", "UTF-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());

            // 设置请求属性
            String param = "";
            if (paramMap != null && paramMap.size() > 0) {
                Iterator<String> ite = paramMap.keySet().iterator();
                while (ite.hasNext()) {
                    String key = ite.next();// key
                    String value = paramMap.get(key);
                    param += key + "=" + value + "&";
                }
                param = param.substring(0, param.length() - 1);
            }

            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.err.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 数据流post请求
     * @param urlStr
     */
    public static String doPost(String urlStr, String strInfo) {
        String reStr="";
        try {
            URL url = new URL(urlStr);
            URLConnection con = url.openConnection();
            con.setDoOutput(true);
            con.setRequestProperty("Pragma:", "no-cache");
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            out.write(new String(strInfo.getBytes("utf-8")));
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            String line = "";
            for (line = br.readLine(); line != null; line = br.readLine()) {
                reStr += line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reStr;
    }




//    public static String httpPostWithJson(Map<String,String> jsonObj, String url){
//        boolean isSuccess = false;
//
//        HttpPost post = null;
//        try {
//            HttpClient httpClient = new DefaultHttpClient();
//
//            // 设置超时时间
//            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
//            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);
//
//            post = new HttpPost(url);
//            // 构造消息头
//            post.setHeader("Content-type", "application/json; charset=utf-8");
//            post.setHeader("Connection", "Close");
//
//            // 构建消息实体
//            StringEntity entity = new StringEntity(jsonObj.toString(), Charset.forName("UTF-8"));
//            entity.setContentEncoding("UTF-8");
//            // 发送Json格式的数据请求
//            entity.setContentType("application/json");
//            post.setEntity(entity);
//
//
//
//            HttpResponse response = httpClient.execute(post);
//
//            // 检验返回码
//            int statusCode = response.getStatusLine().getStatusCode();
//            if(statusCode != HttpStatus.SC_OK){
//                isSuccess = false;
//            }else{
//                int retCode = 0;
//                String sessendId = "";
//                // 返回码中包含retCode及会话Id
//                for(Header header : response.getAllHeaders()){
//                    if(header.getName().equals("retcode")){
//                        retCode = Integer.parseInt(header.getValue());
//                    }
//                    if(header.getName().equals("SessionId")){
//                        sessendId = header.getValue();
//                    }
//                }
//                 HttpEntity entitys = response.getEntity();
//                if (entitys != null) {
//
//                    return inputStreamToString(entitys.getContent());
//                }
//
//                return  null;
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            isSuccess = false;
//        }finally{
//            if(post != null){
//                try {
//                    post.releaseConnection();
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return null;
//    }
//
//
//    public static String inputStreamToString(InputStream is) {
//
//        String line = "";
//        StringBuilder total = new StringBuilder();
//
//        // Wrap a BufferedReader around the InputStream
//        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//
//        try {
//            // Read response until the end
//            while ((line = rd.readLine()) != null) {
//                total.append(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // Return full string
//        return total.toString();
//    }

}
