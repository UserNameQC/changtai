package com.changtai.activites;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.TimeUtils;
import android.widget.Toast;

import com.changtai.Utils.Entity;
import com.changtai.Utils.RealmUtils;
import com.changtai.application.MyApplication;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import okio.Timeout;

/**
 * Created by SAMSUNG on 2018/5/22.
 */

public class BaseActivity extends FragmentActivity {

    public ProgressDialog dialog;
    public MyApplication application;
    public Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
    }


    public Map<String,String> getMap(String result){
        Map<String, String> datamap = new HashMap<>();
        //RealmUtils realmUtils = new RealmUtils<>();
        String code;
        String data;
        try{
            JSONObject object = new JSONObject(result);
            JSONObject jsonObject = object.getJSONObject("head");
            code = jsonObject.getString("code");
            data = object.getJSONObject("data").toString();
            datamap.put("code", code);
            datamap.put("result", data);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return datamap;
    }

}
