package com.changtai.Dal;

import android.app.Application;
import android.content.Context;

import com.changtai.Utils.Entity;

import io.realm.Realm;

public class BaseDal {

    protected Realm mRealm ;

    public BaseDal() {
        mRealm= Entity.realm;
    }

}
