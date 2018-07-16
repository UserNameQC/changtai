package com.changtai.newDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by qjcjob on 2018/7/4.
 */
@Entity
public class LoginDao {

    //标识
    @Id
    public Long id;
    //登录名
    @Property
    public String loginName;
    //用户名
    @Property
    public String userName;
    //密码密文保存
    @Property
    public String password;
    //权限字符串，有两种权限，分别为管理员、操作员。登录名为admin的用户有特殊管理权限
    @Property
    public String qxString;
    public String getQxString() {
        return this.qxString;
    }
    public void setQxString(String qxString) {
        this.qxString = qxString;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getLoginName() {
        return this.loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1000235547)
    public LoginDao(Long id, String loginName, String userName, String password,
            String qxString) {
        this.id = id;
        this.loginName = loginName;
        this.userName = userName;
        this.password = password;
        this.qxString = qxString;
    }
    @Generated(hash = 1277520868)
    public LoginDao() {
    }
}
