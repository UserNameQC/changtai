package com.changtai.realm;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by qjcjo on 2018/4/1.
 */

public class dbEntityRealm extends RealmObject {
    @PrimaryKey
    public Long Id;
    @Required
    public String USER_NAME;
    @Required
    public String PASS_WORD;

    public int ADDRESS_ID;
    public int DEFAULT_NUM;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getPASS_WORD() {
        return PASS_WORD;
    }

    public void setPASS_WORD(String PASS_WORD) {
        this.PASS_WORD = PASS_WORD;
    }

    public int getADDRESS_ID() {
        return ADDRESS_ID;
    }

    public void setADDRESS_ID(int ADDRESS_ID) {
        this.ADDRESS_ID = ADDRESS_ID;
    }

    public int getDEFAULT_NUM() {
        return DEFAULT_NUM;
    }

    public void setDEFAULT_NUM(int DEFAULT_NUM) {
        this.DEFAULT_NUM = DEFAULT_NUM;
    }
}
