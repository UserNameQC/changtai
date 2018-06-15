package com.changtai.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * 操作员表
 */
public class LoginRealm extends RealmObject {

    //标识
    @PrimaryKey
    public long id;
    //登录名
    @Required
    public String loginName;
    //用户名
    @Required
    public String userName;
    //密码密文保存
    @Required
    public String password;
    //权限字符串，有两种权限，分别为管理员、操作员。登录名为admin的用户有特殊管理权限
    @Required
    public String qxString;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQxString() {
        return qxString;
    }

    public void setQxString(String qxString) {
        this.qxString = qxString;
    }
}


