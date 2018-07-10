package com.changtai.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.LocalBroadcastManager;

import com.baidu.mapapi.SDKInitializer;
import com.changtai.Utils.Entity;
import com.changtai.Utils.RealmUtils;
import com.example.john.greendaodemo.gen.DaoMaster;
import com.example.john.greendaodemo.gen.DaoSession;
import com.tencent.bugly.crashreport.CrashReport;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Qiao on 2018/1/28.
 */

public class MyApplication extends Application {

    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    public DaoMaster daoMaster;
    public DaoSession mDaoSession;
    public static MyApplication myApplication;
    public Context context;
    public boolean isFirst;

    @Override
    public void onCreate() {
        super.onCreate();
       // SDKInitializer.initialize(getApplicationContext());
        myApplication = this;
        context = getApplicationContext();
        setDatabase();
        initView();
        Realm.init(myApplication);
        //Entity.realm = Realm.getDefaultInstance();
        initRealm();
        getTime();
        getIsFirst();
        CrashReport.initCrashReport(getApplicationContext(), "692d620efe", true);
    }

    /**
     * 当前使用的缓存数据库
     */
    public void initRealm(){
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().
                name("CtaiRealm.realm")
                .schemaVersion(1)
                .build();
        Entity.realm = Realm.getInstance(realmConfiguration);
        //Realm.setDefaultConfiguration(realmConfiguration);
    }



    /**
     * 获取当前时间
     */
    public void getTime(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Entity.time =Long.valueOf(sdf.format(d));
    }

    public boolean getIsFirst(){
        Entity.isFirst = Entity.spres.getBoolean(Entity.isFirstUseApp, true);
//        if (isFirst)
//        {
//            Entity.spres.edit().putBoolean(Entity.isFirstUseApp, false).apply();
//        }
        return Entity.isFirst;
    }

    public void initView(){
        /**
         * 接口地址拼接
         * 后续操作根据网络连接状态进行拼接操作
         */
        Entity.paramUrl(Entity.mainUrl);
        Entity.spres = context.getSharedPreferences("MyShare", MODE_PRIVATE);
    }

    /**
     * 数据库的初始化
     * 已舍弃
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(context, "changtai.db", null);
        db = mHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();

        Entity.broadcast = LocalBroadcastManager.getInstance(context);
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }


}
