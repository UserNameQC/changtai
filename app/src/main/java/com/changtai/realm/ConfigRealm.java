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
    public Long Id;
    @Required
    public String Name;
    @Required
    public String Value;
    @Required
    public String Comment;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }
}
