package com.changtai.realm;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * 配置表
 * Created by qjcjo on 2018/4/1.
 */

public class ConfigRealm extends RealmObject {
    @PrimaryKey
    public Long id;
    @Required
    public String name;
    @Required
    public String value;
    @Required
    public String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
