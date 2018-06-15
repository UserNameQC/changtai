package com.changtai.realm;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * 取水用户表，该表在PDA端不维护   不维护
 *
 * Created by qjcjo on 2018/4/1.
 */

public class WaterBeUsedDateRealm extends RealmObject {

    @PrimaryKey
    public String id;
    @Required
    public String idwatertake;
    @Required
    public String bureauno;
    @Required
    public String stationno;
    @Required
    public String deviceno;
    @Required
    public String userno;
    @Required
    public String flow;
    @Required
    public String usedtotal;
    @Required
    public String purchasetotal;
    @Required
    public String overdraft;
    @Required
    public String alarmvalue;
    @Required
    public String purchasedate;
    @Required
    public String createdatetime;
    public long serverversion;
    public long clientversion;

    public int key;


    public long getServerversion() {
        return serverversion;
    }

    public void setServerversion(long serverversion) {
        this.serverversion = serverversion;
    }

    public long getClientversion() {
        return clientversion;
    }

    public String getIdwatertake() {
        return idwatertake;
    }

    public void setIdwatertake(String idwatertake) {
        this.idwatertake = idwatertake;
    }

    public String getBureauno() {
        return bureauno;
    }

    public void setBureauno(String bureauno) {
        this.bureauno = bureauno;
    }

    public String getStationno() {
        return stationno;
    }

    public void setStationno(String stationno) {
        this.stationno = stationno;
    }

    public String getDeviceno() {
        return deviceno;
    }

    public void setDeviceno(String deviceno) {
        this.deviceno = deviceno;
    }

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno;
    }

    public String getUsedtotal() {
        return usedtotal;
    }

    public void setUsedtotal(String usedtotal) {
        this.usedtotal = usedtotal;
    }

    public String getPurchasetotal() {
        return purchasetotal;
    }

    public void setPurchasetotal(String purchasetotal) {
        this.purchasetotal = purchasetotal;
    }

    public String getAlarmvalue() {
        return alarmvalue;
    }

    public void setAlarmvalue(String alarmvalue) {
        this.alarmvalue = alarmvalue;
    }

    public String getPurchasedate() {
        return purchasedate;
    }

    public void setPurchasedate(String purchasedate) {
        this.purchasedate = purchasedate;
    }

    public String getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(String createdatetime) {
        this.createdatetime = createdatetime;
    }

    public void setClientversion(long clientversion) {
        this.clientversion = clientversion;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }
}