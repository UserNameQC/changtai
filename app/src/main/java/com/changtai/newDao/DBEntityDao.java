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
public class DBEntityDao {
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
    @Generated(hash = 1369926054)
    public DBEntityDao(Long Id, String USER_NAME, String PASS_WORD, int ADDRESS_ID,
            int DEFAULT_NUM) {
        this.Id = Id;
        this.USER_NAME = USER_NAME;
        this.PASS_WORD = PASS_WORD;
        this.ADDRESS_ID = ADDRESS_ID;
        this.DEFAULT_NUM = DEFAULT_NUM;
    }
    @Generated(hash = 846738141)
    public DBEntityDao() {
    }
}
