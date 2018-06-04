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
import android.util.TimeUtils;
import android.widget.Toast;

import com.changtai.Utils.Entity;
import com.changtai.Utils.RealmUtils;
import com.changtai.application.MyApplication;
import com.changtai.realm.DeviceRealm;
import com.changtai.realm.PurchaseRecordRealm;
import com.changtai.realm.UserRealm;
import com.changtai.realm.WaterBeUsedDateRealm;

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

public class BaseActivity extends Activity {

    public RealmUtils realmUtils = new RealmUtils();
    //public AlertDialog.Builder dialog;
    public ProgressDialog dialog;
    public MyApplication application;
    public Context context;

    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what)
            {
                case 4:
                    realmUtils.saveInRealm(DeviceRealm.class, getMap(msg.obj.toString()));
                    break;
                case 3:

                    realmUtils.saveInRealm(UserRealm.class, getMap(msg.obj.toString()));
                    break;
                case 2:
                    realmUtils.saveInRealm(WaterBeUsedDateRealm.class, getMap(msg.obj.toString()));
                    break;
                case 1:
                    realmUtils.saveInRealm(PurchaseRecordRealm.class, getMap(msg.obj.toString()));
                    break;
                case 11:
                    if (dialog!=null)
                    {
                        dialog.setMessage("同步用户取水信息...");
                    }
                    realmUtils.getDataFromService(handler,"02", 1,WaterBeUsedDateRealm.class);
                    break;
                case 12:
                    if (dialog!=null)
                    {
                        dialog.setMessage("同步用户信息...");
                    }
                    realmUtils.getDataFromService(handler, "03", 1, UserRealm.class);
                    break;
                case 13:
                    if (dialog!=null)
                    {
                        dialog.setMessage("同步机井信息...");
                    }
                    realmUtils.getDataFromService(handler, "04", 1, DeviceRealm.class);
                    break;
                case 14:
                    if (dialog!=null)
                    {
                        dialog.setMessage("同步完成");
                        dialog.cancel();
                    }
                    Toast.makeText(BaseActivity.this, "同步完成", Toast.LENGTH_SHORT).show();
                    break;
                case 444:
                    Toast.makeText(BaseActivity.this, "请求异常，请检查网络。", Toast.LENGTH_SHORT).show();
                    break;
                case 555:
                    startSyncData(false);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //RealmUtils realmUtils = new RealmUtils();
        boolean isFirst = MyApplication.myApplication.getIsFirst();
        if (isFirst) {
            startSyncData(true);
            Entity.spres.edit().putBoolean(Entity.isFirstUseApp, false).apply();
            syncDataTime(10, 10);
        }
        else
        {
            syncDataTime(10, 10);
        }
    }

    public void syncDataTime(long startTime, long syncTime){
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(555);
            }
        }, startTime, syncTime, TimeUnit.MINUTES);
    }

    public void startSyncData(boolean flag){
        realmUtils.getDataFromService(handler, "01", 1, PurchaseRecordRealm.class);
        progressDialog("同步用户购水信息...", flag);
    }

    public void progressDialog(String msg, boolean flag){
        if (flag) {
            dialog = new ProgressDialog(BaseActivity.this);
            dialog.setTitle("正在同步信息...");
            dialog.setMessage(msg);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
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
