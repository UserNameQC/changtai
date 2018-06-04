package com.changtai.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Qiao on 2018/1/28.
 */
@Entity
public class Config {

    @Id
    public Long Id;
    @Property
    public String Name;
    @Property
    public String Value;
    @Property
    public String Comment;
    public String getComment() {
        return this.Comment;
    }
    public void setComment(String Comment) {
        this.Comment = Comment;
    }
    public String getValue() {
        return this.Value;
    }
    public void setValue(String Value) {
        this.Value = Value;
    }
    public String getName() {
        return this.Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    @Generated(hash = 390357080)
    public Config(Long Id, String Name, String Value, String Comment) {
        this.Id = Id;
        this.Name = Name;
        this.Value = Value;
        this.Comment = Comment;
    }
    @Generated(hash = 589037648)
    public Config() {
    }
    
}
