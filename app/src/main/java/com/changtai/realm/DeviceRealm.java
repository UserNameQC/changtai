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
    public Long deviceno;
    @Required
    public Long bureauno;
    @Required
    public Long stationno;
    @Required
    public String gprsno;
    @Required
    public String devicename;
    public int index;
    @Required
    public String linkman;
    @Required
    public String phone;
    @Required
    public String location;
    @Required
    public String createdatetime;
    @Required
    public String longitude;
    @Required
    public String latitude;
    @Required
    public String comment;
    @Required
    public String administratorname;
    @Required
    public String timeSpan;
    @Required
    public String stopflag;
    public long version;
    public long serverversion;
    public long clientversion;

    public int key;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }


    public Long getDeviceno() {
        return deviceno;
    }

    public void setDeviceno(Long deviceno) {
        this.deviceno = deviceno;
    }

    public Long getBureauno() {
        return bureauno;
    }

    public void setBureauno(Long bureauno) {
        this.bureauno = bureauno;
    }

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

    public String getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(String timeSpan) {
        this.timeSpan = timeSpan;
    }

    public String getStopflag() {
        return stopflag;
    }

    public void setStopflag(String stopflag) {
        this.stopflag = stopflag;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

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
