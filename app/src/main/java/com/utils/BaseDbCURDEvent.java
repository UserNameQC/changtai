package com.utils;


import android.database.DatabaseUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.internal.android.JsonUtils;

public abstract class BaseDbCURDEvent<T extends RealmObject>{

    protected String TABLE_FLAG="";
    protected Class clss;
    public Gson gson;

//    public BaseDbCURDEvent(WebView mWebViewc) {
//        super(mWebViewc);
//        setCRUD_TABLE();
//    }
    public abstract void setCRUD_TABLE();

    public void setCRUD_TABLE(String table_flag,Class clss){
        this.TABLE_FLAG=table_flag;
        this.clss=clss;
    }

    public void toCURD(final String callback,final String type,final String queryMap,final String dataMap) {
        Map<String,String> dataParam=null;
        Map<String,String> queryParam=null;
        if(dataMap!=null){
            System.out.println(dataMap);
            dataParam= gson.fromJson(dataMap,HashMap.class);
        }
        if(queryMap!=null){
            System.out.println(queryMap);
            queryParam= gson.fromJson(queryMap,HashMap.class);
        }
        Realm mRealm= Realm.getDefaultInstance();
        String str="";
        if((TABLE_FLAG+"_GETOBJ").equalsIgnoreCase(type)){//��ȡ����
            T dogs =findByObj( queryParam);
            dogs = mRealm.copyFromRealm(dogs);
            str= gson.toJson(dogs);
        }else  if((TABLE_FLAG+"_GETALL").equalsIgnoreCase(type)){//��ȡ����
            List<T> dogs =queryAllData(queryParam);
            dogs=mRealm.copyFromRealm(dogs); //detach from Realm, copy values to fields
            str = gson.toJson(dogs);
        }else  if((TABLE_FLAG+"_ADD").equalsIgnoreCase(type)){//���
            T obj=(T)gson.fromJson(dataMap,clss);
            addObj(obj);
            //str=toSucc();
        }else  if((TABLE_FLAG+"_EDIT").equalsIgnoreCase(type)){//�޸�
            T obj=(T)gson.fromJson(dataMap,clss);
            updateObj(obj,queryParam);
            //str=toSucc();
        }

        //invokeJsMethod(callback,type, str);
    }

    public  T findByObj(Map<String,String> queryParam){
        Realm mRealm= Realm.getDefaultInstance();
        RealmQuery query= mRealm.where(clss);
        if(queryParam!=null) {
            for (Map.Entry<String, String> entry : queryParam.entrySet()) {
                query.equalTo(entry.getKey(), entry.getValue());
            }
        }
      return (T)query.findFirst();
    }

    public  List<T>   queryAllData(Map<String,String> queryParam){
        Realm mRealm= Realm.getDefaultInstance();
        RealmQuery query= mRealm.where(clss);
        if(queryParam!=null){
            for (Map.Entry<String,String> entry:queryParam.entrySet() ) {
                query.equalTo(entry.getKey(),entry.getValue());
            }
        }
        return query.findAll();
    }

    public  void  addObj(T obj ){
        Realm mRealm= Realm.getDefaultInstance();
        mRealm.beginTransaction();
        T d = (T)mRealm.createObject(clss); // Create a new object
        d=mRealm.copyToRealm(obj);
    /*    try {
            BeanUtils.resolveAllFieldsSet(d,obj);
        }  catch ( Exception e) {
            e.printStackTrace();
        }*/

        mRealm.commitTransaction();
    }

    public  void  updateObj(T obj ,  Map<String,String> queryParam){
        Realm mRealm= Realm.getDefaultInstance();
        mRealm.beginTransaction();
        RealmQuery query= mRealm.where(clss);
        if(queryParam!=null) {
            for (Map.Entry<String, String> entry : queryParam.entrySet()) {
                query.equalTo(entry.getKey(), entry.getValue());
            }
        }
        Object dogs =query.findFirst();
        BeanUtils.resolveAllFieldsSet(dogs,obj);
        mRealm.commitTransaction();
    }
}
