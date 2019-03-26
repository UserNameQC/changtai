package com.changtai.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.LocalBroadcastManager;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.changtai.Utils.Entity;
import com.changtai.Utils.GreenDaoContext;
import com.changtai.Utils.SqLiteOpenHelper;
import com.changtai.sqlModel.UserModel;
import com.changtai.sqlModelDao.DaoMaster;
import com.changtai.sqlModelDao.DaoSession;
import com.changtai.sqlModelDao.UserModelDao;
import com.tencent.bugly.crashreport.CrashReport;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Qiao on 2018/1/28.
 */

public class MyApplication extends Application {

    private SqLiteOpenHelper mHelper;
    private SQLiteDatabase db;
    public DaoMaster daoMaster;
    public DaoSession mDaoSession;
    public static MyApplication myApplication;
    public Context context;

    private List<Activity> activityList = new LinkedList<Activity>();
    @Override
    public void onCreate() {
        super.onCreate();

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(getApplicationContext());
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.GCJ02);
        myApplication = this;
        context = getApplicationContext();
        setDatabase();
        initView();
        getTime();
        CrashReport.initCrashReport(getApplicationContext(), "692d620efe", true);
    }

    public static MyApplication getInstance(){
        return myApplication;
    }

    //添加Activity到容器中
    public void addActivity(Activity activity)  {
        activityList.add(activity);
    }

    //遍历所有Activity并finish
    public void exit() {
        for(Activity activity:activityList) {
            activity.finish();
        }
        activityList.clear();
    }

    /**
     * 获取当前时间
     */
    public void getTime(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Entity.time =Long.valueOf(sdf.format(d));
    }

    public void initView(){
        /**
         * 接口地址拼接
         * 后续操作根据网络连接状态进行拼接操作
         */
        Entity.paramUrl(Entity.mainUrl);
        Entity.spres = context.getSharedPreferences("MyShare", MODE_PRIVATE);

        //daoMaster.dropAllTables(daoMaster.getDatabase(),true);
        //daoMaster.createAllTables(daoMaster.getDatabase(), true);
    }

    /**
     * 数据库的初始化
     */
    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new SqLiteOpenHelper(new GreenDaoContext(context), "CHANG-TAI.db", null);
        db = mHelper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();

        Entity.broadcast = LocalBroadcastManager.getInstance(context);
    }

    public DaoSession getDaoSession() {
        mDaoSession.clear();
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }


}
