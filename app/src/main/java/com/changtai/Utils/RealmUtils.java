package com.changtai.Utils;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.changtai.realm.ConfigRealm;
import com.changtai.realm.DeviceRealm;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by qjcjo on 2018/4/12.
 */

public class RealmUtils <T extends RealmObject> {

    /**
     * 批量插入数据
     * 首先查一遍数据
     */
    public static void createData(Map<Integer, EditText> edMap, final Class clss, int key){
        try {
            //long time = System.currentTimeMillis()/1000;
            final JSONObject json = new JSONObject();
            for (int i = 0; i < edMap.size(); i++) {
                EditText et = edMap.get(i);
                json.put("serverversion", Entity.time);
                json.put("key", key);
                json.put(et.getTag().toString(), et.getText().toString());
            }
            Entity.realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Entity.realm.createOrUpdateObjectFromJson(clss, json.toString());
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量修改
     */
    public void updateData(final T t, Class clss, Map<Integer, EditText> etMap, int key) {
        try {
            final JSONObject object = new JSONObject(new Gson().toJson(Entity.realm.copyFromRealm(t)));
            for (int i = 0; i < etMap.size(); i++)
            {
                EditText et = etMap.get(i);
                object.put(et.getTag().toString(), et.getText().toString());
                object.put("key", 0);
            }
            Entity.realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                   //T t1 = (T) new Gson().fromJson(object.toString(), t.getClass());
                   Entity.realm.createOrUpdateObjectFromJson(t.getClass(), object.toString());
                }
            });
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * 请求表信息
     */
    public static String result = null;
    public String code;
    public long sync_time = 0;
    public String data = "";
    public int a;
    public Map<String,String> getDataFromService(final Handler handler, final String type, final int page , final Class clss)
    {
        ConfigRealm configRealm = Entity.realm.where(ConfigRealm.class).equalTo("Id", 10010).findFirst();
        final String baseId = configRealm.getValue();
        RealmResults<T> realmResults = Entity.realm.where(clss).equalTo("key", 2).findAll();
        if (!realmResults.isEmpty())
        {
            realmResults = realmResults.sort("serverversion", Sort.DESCENDING);
            try {
                JSONObject object = new JSONObject(new Gson().toJson(Entity.realm.copyFromRealm(realmResults.get(0))));
                sync_time = object.getLong("serverversion");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
        {
            sync_time = 0;
        }
        final Map<String, String> datamap = new HashMap<>();
        final Message msg = new Message();
        Entity.executorService.execute(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> map = new HashMap<>();
                Map<String, String> headMap = new HashMap<>();
                headMap.put("token", Entity.token);
                map.put("AREA_CODE", baseId);
                map.put("SYNC_TYPE", String.valueOf(type));
                map.put("SYNC_TIME", sync_time + "");
                map.put("PAGE_COUNT", "100");
                map.put("PAGE_INDEX", page + "");
                result = HttpBaseTest.sendPost(headMap, map, Entity.getDataUrl);
                Log.e("Result", result);
                if (!TextUtils.isEmpty(result))
                {

                  //  String result = intent.getExtras().getString("well_list");
                   // Map<String, String> datamap = new HashMap<>();
//                    String code;
//                    String data;
//                    try{
//                        JSONObject object = new JSONObject(result);
//                        JSONObject jsonObject = object.getJSONObject("head");
//                        code = jsonObject.getString("code");
//                        data = object.getJSONObject("data").toString();
//                        datamap.put("code", code);
//                        datamap.put("result", data);
//                        //RealmUtils<DeviceRealm> realmUtils = new RealmUtils<>();
//                       // realmUtils.saveInRealm(DeviceRealm.class, "04", datamap, Entity.Well_list);
//                    }catch (JSONException e){
//                        e.printStackTrace();
//                    }
                    msg.obj = result;
                    msg.what = Integer.valueOf(type);
                    handler.sendMessage(msg);

                    try {
                        JSONObject jsonObject = new JSONObject(result);
                        JSONObject object = jsonObject.getJSONObject("data");
                        int num = (int) object.getDouble("SUM_COUNT");
                        a = page;
                        if(num%100>0)
                        {
                            num /= 100 +1;
                        }
                        else
                        {
                            num/=100;
                        }
                        if (a++ <= num){
                            MainHandler.getInstance().post(new Runnable() {
                                @Override
                                public void run() {
                                    getDataFromService(handler, type, a, clss);
                                }
                            });

                        }
                        else
                        {
                            if (type.equals("01"))
                            {
                                handler.sendEmptyMessage(11);
                            }
                            else if(type.equals("02"))
                            {
                                handler.sendEmptyMessage(12);
                            }
                            else if(type.equals("03"))
                            {
                                handler.sendEmptyMessage(13);
                            }
                            else
                            {
                                handler.sendEmptyMessage(14);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //Entity.broadcast.sendBroadcast(new Intent(action).putExtra("well_list", result));
                }
                else
                {
                    handler.sendEmptyMessage(444);
                }

            }
        });
        return datamap;
    }

    /*public class getData extends AsyncTask<Map<String,String>,Void,String>{

        @Override
        protected String doInBackground(Map<String, String>[] maps) {
            Map<String, String> map=getDataFromService("04",1, DeviceRealm.class,Entity.Well_list);

            return map;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }*/


    public void saveInRealm(final Class t/*, String type*/, Map<String, String> resultMap/*, String action*/){
       // Map<String, String> resultMap = getDataFromService(type, 1, t);
        String code = resultMap.get("code");
        if (code .equals("0000")) {
            int size = 0;
            try {
                if (!TextUtils.isEmpty(resultMap.get("result"))) {
                    JSONObject jsonObject = new JSONObject(resultMap.get("result"));
                    //JSONObject object = jsonObject.getJSONObject("data");
                    //int num = (int) (jsonObject.getDouble("SUM_COUNT"));
                    JSONArray array = jsonObject.getJSONArray("LIST");
                    //if (num == 0) return;
                    //else if (num <= 10) {
                        for (int i = 0; i < array.length(); i++) {
                            final JSONObject json = array.getJSONObject(i);
                            json.put("key", 2);
                            Entity.realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    Entity.realm.createOrUpdateObjectFromJson(t, json);
                                }
                            });
                        }
//                    } else {
//                        int a = num % 10;
//                        if (a > 0) {
//                            size = (num - a) / 10 + 1;
//                        } else {
//                            size = num / 10;
//                        }
//                        for (int i = 2; i <= size; i++) {
//                            String resultCode = resultMap.get("code");
//                            if (resultCode.equals("0000")) {
//                                for (int j = 0; j < array.length(); j++) {
//                                    final JSONObject json = array.getJSONObject(j);
//                                    json.put("key", 2);
//                                    Entity.realm.executeTransaction(new Realm.Transaction() {
//                                        @Override
//                                        public void execute(Realm realm) {
//                                            Entity.realm.createOrUpdateObjectFromJson(t, json);
//                                        }
//                                    });
//                                }
//                            }
//                        }
//                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setEditEnable(Map<Integer, EditText> map, boolean flag){
        for (int i = 0; i < map.size(); i++)
        {
            EditText et = map.get(i);
            et.setEnabled(flag);
        }
    }

    public static void setEditWatch(Map<Integer, EditText> map, TextWatcher textWatcher){
        for (int i = 0; i < map.size(); i++)
        {
            EditText et = map.get(i);
            et.addTextChangedListener(textWatcher);
        }
    }

    public static void setTimeUpdateToServer(Class c, final String type){
        final RealmResults realmUtils = Entity.realm.where(c).equalTo("key", 0).findAll();
        ConfigRealm configRealm = Entity.realm.where(ConfigRealm.class).equalTo("Id", 10010).findFirst();
        final String baseId = configRealm.getValue();
        final Map<String, Object> map = new HashMap<>();
        final Map<String, String> headMap = new HashMap<>();
        headMap.put("token", Entity.token);
        map.put("AREA_CODE", baseId);
        map.put("SYNC_TYPE", String.valueOf(type));
        List list = new ArrayList<>();
        for (int i = 0; i < realmUtils.size(); i++)
        {
            list.add(Entity.realm.copyFromRealm((RealmModel) realmUtils.get(i)));
        }

        map.put("LIST", list);
        Entity.executorService.execute(new Runnable() {
            @Override
            public void run() {
//                Map<String, String> map = new HashMap<>();
//                Map<String, String> headMap = new HashMap<>();
//                headMap.put("token", Entity.token);
//                map.put("AREA_CODE", baseId);
//                map.put("SYNC_TYPE", String.valueOf(type));
//                map.put("LIST", new JSONArray(realmUtils).toString());

                result = HttpBaseTest.sendPost(headMap, map, Entity.updateUrl);
            }
        });
    }
}
