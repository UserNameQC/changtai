package com.changtai.Utils;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by xk on 2018/3/22.
 */

public class AdminZeroSave {
    private Context context;
    public String urlConnect(String sendurl){
        try {
            URL url = new URL(sendurl);
            if (url != null) {

                HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
                urlConn.setDoOutput(true);
                urlConn.setDoInput(true);
                urlConn.setRequestMethod("POST");
                urlConn.setUseCaches(false);
                urlConn.setInstanceFollowRedirects(true);
                urlConn.setRequestProperty("contentType", "utf-8");//解决中文显示乱码问题
                urlConn.connect();
                String msg="";
                if(urlConn.getResponseCode()==0000) {
                    //连接成功
                    InputStream in = urlConn.getInputStream();
                    byte[] b = new byte[1024 * 512];
                    int len = 0;

                    //建立缓存流，保存所读取的字节数组
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    while ((len = in.read(b)) > -1) {
                        baos.write(b, 0, len);
                    }
                    msg = baos.toString();
                }
                return msg;
            }
        }  catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //获取水利局及其水站号供管理员进行出厂化设置
    public boolean AdminZeroSave()  {
        String httpUrl="http://kzfc01/changtai/structure/message";
        String msg=urlConnect(httpUrl);
        //解析数据
        try {
            if(msg.isEmpty()){
                JSONObject obj= new JSONObject(msg);
                JSONObject data = obj.getJSONObject("data");
                String  name = data.getString("STRUCTURE_NAME");
                String  code = data.getString("STRUCTURE_CODE");
                System.out.println(name);
                System.out.println(code);
                //获取到数据后把数据写入文件
                FileOutputStream fos = context.openFileOutput("adminZeroSave.txt", Context.MODE_PRIVATE);
                //此处要调用接口获取信息
                String content = "name:"+name +"/n"+"code:"+code+"/n";
                fos.write(content.getBytes());
                fos.flush();
                fos.close();
                return true;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean DataToService() {
        String httpUrl = "http://kzfc01/changtai/structure/message";
        String msg=urlConnect(httpUrl);
        //解析数据
        try {
            if(msg.isEmpty()){
                JSONObject obj= new JSONObject(msg);
                JSONObject data = obj.getJSONObject("data");
                String  code = data.getString("AREA_CODE");
                String  token = data.getString("TOKEN");
                String  list = data.getString("LIST");
                String  type = data.getString("SYNC_TYPE");
                System.out.println(token);
                System.out.println(list);
                System.out.println(type);
                System.out.println(code);
                //获取到数据后把数据写入文件
                FileOutputStream fos = context.openFileOutput("dataToService.txt", Context.MODE_PRIVATE);
                //此处要调用接口获取信息
                String content = "code:"+code+"/n"+"token:"+token +"/n"+"list:"+list +"/n"+"type:"+type+"/n" ;
                fos.write(content.getBytes());
                fos.flush();
                fos.close();
                return true;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
    //服务器同步数据到app
    public boolean SyncData() {
        String httpUrl = "http://kzfc01/changtai/data/syncData";
        String msg=urlConnect(httpUrl);
        //解析数据
        try {
            if(msg.isEmpty()){
                JSONObject obj= new JSONObject(msg);
                JSONObject data = obj.getJSONObject("data");
                String  area_code = data.getString("AREA_CODE");
                String  sync_type = data.getString("SYNC_TYPE");
                //数据类型应该为date类型
                String sync_time = data.getString("SYNC_TIME");
                int  page_count = data.getInt("PAGE_COUNT");
                int page_index = data.getInt("PAGE_INDEX");
                //获取到数据后把数据写入文件
                FileOutputStream fos = context.openFileOutput("syncData.txt", Context.MODE_PRIVATE);
                //此处要调用接口获取信息
                String content = "area_code:"+area_code +"/n"+"sync_type:"+sync_type +"/n"+"sync_time:"+sync_time+"/n"
                        +"page_count:"+page_count+"/n"+"page_index:"+page_index+"/n";
                fos.write(content.getBytes());
                fos.flush();
                fos.close();
                return true;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean RestLoginPwd() {
        String httpUrl = "http://kzfc01/changtai/structure/message";
        String msg=urlConnect(httpUrl);
        //解析数据
        try {
            if(msg.isEmpty()){
                JSONObject obj= new JSONObject(msg);
                JSONObject data = obj.getJSONObject("data");
                String  token = data.getString("TOKEN");
                String  yPwd = data.getString("Y_PWD");
                String  newPwd = data.getString("NEW_PWD");
                System.out.println(token);
                System.out.println(yPwd);
                System.out.println(newPwd);
                //获取到数据后把数据写入文件
                FileOutputStream fos = context.openFileOutput("restLoginPwd.txt", Context.MODE_PRIVATE);
                //此处要调用接口获取信息
                String content = "token:"+token +"\n"+"yPwd:"+yPwd +"\n"+"newPwd:"+newPwd+"\n" ;
                fos.write(content.getBytes());
                fos.flush();
                fos.close();
                return true;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }


    public String VailLoginPwd(){
        String httpUrl = "http://kzfc01/changtai/user/vailLoginPwd";
        String msg=urlConnect(httpUrl);
        //解析数据
        try {
            if(msg.isEmpty()){
                JSONObject obj= new JSONObject(msg);
                JSONObject data = obj.getJSONObject("data");
                String  token = data.getString("TOKEN");
                String  yPwd = data.getString("Y_PWD");
                //此处读取保存的密码  然后验证


                System.out.println(token);
                System.out.println(yPwd);
                //获取到数据后把数据写入文件
                FileOutputStream fos = context.openFileOutput("vailLoginPwd.txt", Context.MODE_PRIVATE);
                //此处要调用接口获取信息
                String content = "token:"+token +"/n"+"yPwd:"+yPwd +"/n";
                fos.write(content.getBytes());
                fos.flush();
                fos.close();
                return "success";
            }
        }catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    public String  AdminLogin(){
        String httpUrl = "http://kzfc01/changtai/user/login";
        String msg=urlConnect(httpUrl);
        //解析数据
        try {
            if(msg.isEmpty()){
                JSONObject obj= new JSONObject(msg);
                JSONObject data = obj.getJSONObject("data");
                String  area_code = data.getString("AREA_CODE");
                String  user_account = data.getString("USER_ACCOUNT");
                String  user_pwd = data.getString("USER_PWD");
                String  use_type = data.getString("USE_TYPE");

                System.out.println(area_code);
                System.out.println(user_account);
                System.out.println(user_pwd);
                System.out.println(use_type);
                //获取到数据后把数据写入文件
                FileOutputStream fos = context.openFileOutput("login.txt", Context.MODE_PRIVATE);
                //此处要调用接口获取信息
                String content = "area_code:"+area_code +"/n"+"user_account:"+user_account +"/n"+"user_pwd:"+user_pwd +"/n"+"use_type:"+use_type +"/n";
                fos.write(content.getBytes());
                fos.flush();
                fos.close();
                return "Success！";
            }
        }catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return "fail！";
    }
/*

        String resultData="";
        try {
            URL url=new URL(httpUrl);
            if(url!=null){

                HttpURLConnection urlConn=(HttpURLConnection)url.openConnection();
                urlConn.setDoOutput(true);
                urlConn.setDoInput(true);
                urlConn.setRequestMethod("POST");
                urlConn.setUseCaches(false);
                urlConn.setInstanceFollowRedirects(true);
                urlConn.setRequestProperty("contentType", "utf-8");//解决中文显示乱码问题
                urlConn.connect();

                if(urlConn.getResponseCode()==0000){
                    //连接成功
                    InputStream in = urlConn.getInputStream();
                    byte[] b = new byte[1024 * 512];
                    int len = 0;

                    //建立缓存流，保存所读取的字节数组
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    while ((len = in.read(b)) > -1) {
                        baos.write(b, 0, len);
                    }
                    String msg = baos.toString();

                    //解析数据
                    JSONObject obj= new JSONObject(msg);
                    JSONObject data = obj.getJSONObject("data");
                    String  name = data.getString("STRUCTURE_NAME");
                    String  code = data.getString("STRUCTURE_CODE");
                    System.out.println(name);
                    System.out.println(code);
                    //获取到数据后把数据写入文件
                    FileOutputStream fos = context.openFileOutput("confix.txt", Context.MODE_PRIVATE);
                    //此处要调用接口获取信息
                    String content = "name:"+name +"/n"+"code:"+code+"/n";
                    fos.write(content.getBytes());
                    fos.flush();
                    fos.close();
                }else if(urlConn.getResponseCode()==0001){
                    Log.i("未认证！", "===========END=================");
                }else{
                    Log.i("响应失败！", "===========END=================");
                }
*//*
                DataOutputStream out=new DataOutputStream(urlConn.getOutputStream());
                String content="POST参数名="+ URLEncoder.encode("传入值","utf-8");
                out.writeBytes(content);
                out.flush();
                out.close();

                BufferedReader reader=new BufferedReader(new InputStreamReader(urlConn.getInputStream(),"utf-8"));
                String inputLine=null;
                while((inputLine=reader.readLine())!=null){
                    resultData+=inputLine+"\n";
                }
                reader.close();

                urlConn.disconnect();

                if(resultData!=""){
                    System.out.println(resultData);
                }else{
                    System.out.println("Sorry,the content is null");
                }*//*
            }
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }*/


}
