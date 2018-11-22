package com.changtai.Utils;

import com.changtai.application.MyApplication;
import com.example.john.greendaodemo.gen.DaoSession;

import org.greenrobot.greendao.AbstractDao;

import java.security.Key;
import java.util.List;

/**
 * Created by qjcjob on 2018/11/21.
 */

public class GreenDaoUtil {

    public GreenDaoUtil(){

    }

    public DaoSession getSession(){
        return MyApplication.getInstance().getDaoSession();
    }
}
