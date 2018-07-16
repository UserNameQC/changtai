package com.changtai.newDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import io.realm.annotations.Required;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by qjcjob on 2018/7/4.
 */

@Entity
public class ConfigModel {

    @Id
    public Long id;
    @Property
    public String name;
    @Property
    public String value;
    @Property
    public String comment;
    public String getComment() {
        return this.comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1101971209)
    public ConfigModel(Long id, String name, String value, String comment) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.comment = comment;
    }
    @Generated(hash = 341491210)
    public ConfigModel() {
    }
}
