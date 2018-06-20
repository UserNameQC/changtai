package com.changtai.realm;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * 机井表
 * Created by qjcjo on 2018/4/1.
 */

public class DeviceRealm extends RealmObject {

    @PrimaryKey
    public Long deviceno;//机井编号
    @Required
    public Long stationno;//售水站号
    @Required
    public String gprsno;//GPS模块编号
    @Required
    public String devicename;//机井名称
    public int index;//索引
    @Required
    public String linkman;//联系人
    @Required
    public String phone;//电话
    @Required
    public String location;//安装位置
    @Required
    public String createdatetime;//登记时间
    @Required
    public String longitude;//经度
    @Required
    public String latitude;//维度
    @Required
    public String comment;//备注
    @Required
    public String administratorname;//操作员
    @Required
    public String stopflag;//停用标志

    public long serverversion;//服务器端数据编号
    public long clientversion;//pc端数据版本

    public boolean DelFlag;//删除标志

    public int key;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isDelFlag() {
        return DelFlag;
    }

    public void setDelFlag(boolean delFlag) {
        DelFlag = delFlag;
    }

    public Long getDeviceno() {
        return deviceno;
    }

    public void setDeviceno(Long deviceno) {
        this.deviceno = deviceno;
    }

//    public Long getBureauno() {
//        return bureauno;
//    }
//
//    public void setBureauno(Long bureauno) {
//        this.bureauno = bureauno;
//    }

    public Long getStationno() {
        return stationno;
    }

    public void setStationno(Long stationno) {
        this.stationno = stationno;
    }

    public String getGprsno() {
        return gprsno;
    }

    public void setGprsno(String gprsno) {
        this.gprsno = gprsno;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(String createdatetime) {
        this.createdatetime = createdatetime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAdministratorname() {
        return administratorname;
    }

    public void setAdministratorname(String administratorname) {
        this.administratorname = administratorname;
    }

//    public String getTimeSpan() {
//        return timeSpan;
//    }
//
//    public void setTimeSpan(String timeSpan) {
//        this.timeSpan = timeSpan;
//    }

    public String getStopflag() {
        return stopflag;
    }

    public void setStopflag(String stopflag) {
        this.stopflag = stopflag;
    }

//    public long getVersion() {
//        return version;
//    }
//
//    public void setVersion(long version) {
//        this.version = version;
//    }

    public long getServerversion() {
        return serverversion;
    }

    public void setServerversion(long serverversion) {
        this.serverversion = serverversion;
    }

    public long getClientversion() {
        return clientversion;
    }

    public void setClientversion(long clientversion) {
        this.clientversion = clientversion;
    }
}
