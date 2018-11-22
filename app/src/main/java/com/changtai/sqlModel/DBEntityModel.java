package com.changtai.sqlModel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by qjcjob on 2018/7/4.
 */
@Entity
public class DBEntityModel {
    @Id
    public Long Id;
    @Property
    public String USER_NAME;
    @Property
    public String PASS_WORD;
    @Property
    public int ADDRESS_ID;
    @Property
    public int DEFAULT_NUM;
    @Property
    public String token;

    public int getDEFAULT_NUM() {
        return this.DEFAULT_NUM;
    }
    public void setDEFAULT_NUM(int DEFAULT_NUM) {
        this.DEFAULT_NUM = DEFAULT_NUM;
    }
    public int getADDRESS_ID() {
        return this.ADDRESS_ID;
    }
    public void setADDRESS_ID(int ADDRESS_ID) {
        this.ADDRESS_ID = ADDRESS_ID;
    }
    public String getPASS_WORD() {
        return this.PASS_WORD;
    }
    public void setPASS_WORD(String PASS_WORD) {
        this.PASS_WORD = PASS_WORD;
    }
    public String getUSER_NAME() {
        return this.USER_NAME;
    }
    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    @Generated(hash = 1370338039)
    public DBEntityModel(Long Id, String USER_NAME, String PASS_WORD,
            int ADDRESS_ID, int DEFAULT_NUM, String token) {
        this.Id = Id;
        this.USER_NAME = USER_NAME;
        this.PASS_WORD = PASS_WORD;
        this.ADDRESS_ID = ADDRESS_ID;
        this.DEFAULT_NUM = DEFAULT_NUM;
        this.token = token;
    }
    @Generated(hash = 714593783)
    public DBEntityModel() {
    }
}
