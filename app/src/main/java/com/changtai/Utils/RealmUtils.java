package com.changtai.Utils;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.changtai.application.MyApplication;
import com.changtai.sqlModel.DeviceModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by qjcjo on 2018/4/12.
 */

public class  RealmUtils<T> {

    /**
     * 批量插入数据
     * 首先查一遍数据
     */
    public T createData(Map<Integer, EditText> edMap, int key){
        try {
            final JSONObject json = new JSONObject();
            for (int i = 0; i < edMap.size(); i++) {
                EditText et = edMap.get(i);
                json.put(et.getTag().toString(), et.getText().toString());
            }
            json.put("serverVersion", Entity.time);
            json.put("key", key);
            T t = new Gson().fromJson(json.toString(), new TypeToken<T>(){}.getType());
            return t;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量修改
     */
    public void updateData(Map<Integer, EditText> etMap, int key) {
//        try {
//            final JSONObject object = new JSONObject();
//            for (int i = 0; i < etMap.size(); i++)
//            {
//                EditText et = etMap.get(i);
//                object.put(et.getTag().toString(), et.getText().toString());
//            }
//            object.put("key", 0);
//
//            Entity.realm.executeTransaction(new Realm.Transaction() {
//                @Override
//                public void execute(Realm realm) {
//                   //T t1 = (T) new Gson().fromJson(object.toString(), t.getClass());
//                   Entity.realm.createOrUpdateObjectFromJson(t.getClass(), object.toString());
//                }
//            });
//        }catch (JSONException e){
//            e.printStackTrace();
//        }
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
//        ConfigRealm configRealm = Entity.realm.where(ConfigRealm.class).equalTo("id", 10010).findFirst();
//        final String baseId = configRealm.getValue();
//        RealmResults<T> realmResults = Entity.realm.where(clss).equalTo("key", 2).findAll();
//        if (!realmResults.isEmpty())
//        {
//            realmResults = realmResults.sort("serverVersion", Sort.DESCENDING);
//            try {
//                JSONObject object = new JSONObject(new Gson().toJson(Entity.realm.copyFromRealm(realmResults.get(0))));
//                sync_time = object.getLong("serverVersion");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        else
//        {
//            sync_time = 0;
//        }
//        final Map<String, String> datamap = new HashMap<>();
//        final Message msg = new Message();
//        Entity.executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                Map<String, Object> map = new HashMap<>();
//                Map<String, String> headMap = new HashMap<>();
//                headMap.put("token", Entity.token);
//                map.put("AREA_CODE", baseId);
//                map.put("SYNC_TYPE", String.valueOf(type));
//                map.put("SYNC_TIME", sync_time + "");
//                map.put("PAGE_COUNT", "100");
//                map.put("PAGE_INDEX", page + "");
//                result = HttpBaseTest.sendPost(headMap, map, Entity.getDataUrl);
//                Log.e("Result", result);
//                if (!TextUtils.isEmpty(result))
//                {
//
//                  //  String result = intent.getExtras().getString("well_list");
//                   // Map<String, String> datamap = new HashMap<>();
////                    String code;
////                    String data;
////                    try{
////                        JSONObject object = new JSONObject(result);
////                        JSONObject jsonObject = object.getJSONObject("head");
////                        code = jsonObject.getString("code");
////                        data = object.getJSONObject("data").toString();
////                        datamap.put("code", code);
////                        datamap.put("result", data);
////                        //RealmUtils<DeviceRealm> realmUtils = new RealmUtils<>();
////                       // realmUtils.saveInRealm(DeviceRealm.class, "04", datamap, Entity.Well_list);
////                    }catch (JSONException e){
////                        e.printStackTrace();
////                    }
//                    msg.obj = result;
//                    msg.what = Integer.valueOf(type);
//                    handler.sendMessage(msg);
//
//                    try {
//                        JSONObject jsonObject = new JSONObject(result);
//                        JSONObject object = jsonObject.getJSONObject("data");
//                        int num = (int) object.getDouble("SUM_COUNT");
//                        a = page;
//                        if(num%100>0)
//                        {
//                            num /= 100 +1;
//                        }
//                        else
//                        {
//                            num/=100;
//                        }
//                        if (a++ <= num){
//                            MainHandler.getInstance().post(new Runnable() {
//                                @Override
//                                public void run() {
//                                    getDataFromService(handler, type, a, clss);
//                                }
//                            });
//
//                        }
//                        else
//                        {
//                            if (type.equals("01"))
//                            {
//                                handler.sendEmptyMessage(11);
//                            }
//                            else if(type.equals("02"))
//                            {
//                                handler.sendEmptyMessage(12);
//                            }
//                            else if(type.equals("03"))
//                            {
//                                handler.sendEmptyMessage(13);
//                            }
//                            else
//                            {
//                                handler.sendEmptyMessage(14);
//                            }
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    //Entity.broadcast.sendBroadcast(new Intent(action).putExtra("well_list", result));
//                }
//                else
//                {
//                    handler.sendEmptyMessage(444);
//                }
//
//            }
//        });
//        return datamap;
        return null;
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
//        final RealmResults realmUtils = Entity.realm.where(c).equalTo("key", 0).findAll();
//        ConfigRealm configRealm = Entity.realm.where(ConfigRealm.class).equalTo("id", 10010).findFirst();
//        final String baseId = configRealm.getValue();
//        final Map<String, Object> map = new HashMap<>();
//        final Map<String, String> headMap = new HashMap<>();
//        headMap.put("token", Entity.token);
//        map.put("AREA_CODE", baseId);
//        map.put("SYNC_TYPE", String.valueOf(type));
//        List list = new ArrayList<>();
//        for (int i = 0; i < realmUtils.size(); i++)
//        {
//            list.add(Entity.realm.copyFromRealm((RealmModel) realmUtils.get(i)));
//        }
//
//        map.put("LIST", list);
//        Entity.executorService.execute(new Runnable() {
//            @Override
//            public void run() {
////                Map<String, String> map = new HashMap<>();
////                Map<String, String> headMap = new HashMap<>();
////                headMap.put("token", Entity.token);
////                map.put("AREA_CODE", baseId);
////                map.put("SYNC_TYPE", String.valueOf(type));
////                map.put("LIST", new JSONArray(realmUtils).toString());
//
//                result = HttpBaseTest.sendPost(headMap, map, Entity.updateUrl);
//            }
//        });
    }
}
