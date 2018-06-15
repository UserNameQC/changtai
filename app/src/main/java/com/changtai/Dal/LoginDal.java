package com.changtai.Dal;

import android.content.Context;

import com.changtai.realm.LoginRealm;

import io.realm.RealmResults;

/**
 * LoginRealm的业务处理类
 */
public class LoginDal extends BaseDal {

    /**
     * 按登录名查找操作员
     * 找不到报错
     * 找到重复报错
     * @param loginName
     * @return
     */
    public LoginRealm findSingleByLoginName(String loginName) throws Exception {
        RealmResults<LoginRealm> list = mRealm.where(LoginRealm.class).equalTo("loginName",loginName).findAll();
        if(list.isEmpty()){
            throw new Exception(String.format("按登录名%s找不到用户",loginName));
        }
        if(list.toArray().length>1) {
            throw new Exception(String.format("该登录名%s存在多个用户",loginName));
        }
        return list.get(0);
    }
}
