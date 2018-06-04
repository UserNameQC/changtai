package com.changtai.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Qiao on 2018/1/28.
 */

@Entity
public class LoginUser {
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
    @Generated(hash = 304148405)
    public LoginUser(Long Id, Long loginId, String UserName, String UserPwd,
            String toke) {
        this.Id = Id;
        this.loginId = loginId;
        this.UserName = UserName;
        this.UserPwd = UserPwd;
        this.toke = toke;
    }
    @Generated(hash = 1159929338)
    public LoginUser() {
    }
}
