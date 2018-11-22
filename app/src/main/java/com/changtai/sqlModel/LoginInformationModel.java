package com.changtai.sqlModel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by qjcjob on 2018/7/4.
 */
@Entity
public class LoginInformationModel {
    @Id
    public Long Id;
    @Property
    public Long loginId;
    @Property
    public String UserName;
    @Property
    public String UserPwd;
    @Property
    public String toke;
    public String getToke() {
        return this.toke;
    }
    public void setToke(String toke) {
        this.toke = toke;
    }
    public String getUserPwd() {
        return this.UserPwd;
    }
    public void setUserPwd(String UserPwd) {
        this.UserPwd = UserPwd;
    }
    public String getUserName() {
        return this.UserName;
    }
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public Long getLoginId() {
        return this.loginId;
    }
    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    @Generated(hash = 2100713508)
    public LoginInformationModel(Long Id, Long loginId, String UserName,
            String UserPwd, String toke) {
        this.Id = Id;
        this.loginId = loginId;
        this.UserName = UserName;
        this.UserPwd = UserPwd;
        this.toke = toke;
    }
    @Generated(hash = 1685421123)
    public LoginInformationModel() {
    }
}
