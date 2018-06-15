package com.changtai.Dal;

import android.content.Context;

import com.changtai.realm.LoginRealm;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * 操作员（LoginRealm）的业务处理类
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
        if(list.size()>1) {
            throw new Exception(String.format("该登录名%s存在多个用户",loginName));
        }
        return list.get(0);
    }

    /**
     * 按登录名查找，该用户是否存在
     * @param loginName
     * @return 存在返回true,不存在返回 false
     */
    public boolean existByLoginName(String loginName){
        RealmResults<LoginRealm> list = mRealm.where(LoginRealm.class).equalTo("loginName",loginName).findAll();
        return !list.isEmpty();
    }

    /**
     * 添加一个用户，添加失败返回错误
     * @param loginName
     * @param password
     * @param qxString
     * @throws Exception
     */
    public void Add(final String loginName, final String password, final String qxString) throws Exception {
        if(existByLoginName(loginName)){
            throw new Exception(String.format("该用户%s已经存在",loginName));
        }
        final Integer max = (Integer) mRealm.where(LoginRealm.class).max("id");
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Integer max2 = 0;
                if(max==null){
                    max2=1;
                }else {
                    max2=max+1;
                }

                LoginRealm user = realm.createObject(LoginRealm.class,max2);
//                user.id=max+1;
                user.loginName=loginName;
                user.userName=loginName;
                user.password=password;
                user.qxString=qxString;
            }
        });


    }
}
