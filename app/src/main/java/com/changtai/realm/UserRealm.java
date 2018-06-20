package com.changtai.realm;

import com.google.gson.annotations.Expose;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * 购水用户表
 * Created by qjcjo on 2018/4/1.
 */

public class UserRealm extends RealmObject {

    @PrimaryKey
    public String userno;
    @Required
    public String stationno;
    @Required
    public String deviceno;
    @Required
    public String index;
    @Required
    public String username;
    @Required
    public String phone;
    @Required
    public String createdatetime;
    @Required
    public String linkman;
    @Required
    public String sjid;
    @Required
    public String usedtotal;
    @Required
    public String purchasetotal;
    @Required
    public String purchasetotalthisyear;
    @Required
    public String overdraft;
    @Required
    public String alarmvalue;
    @Required
    public String credentialno;
    @Required
    public String limitsj1;
    @Required
    public String limitsj2;
    @Required
    public String comment;
    @Required
    public String administratorname;
    @Required
    public String stopflag;
    @Required
    public String cardno;
    @Required
    public String creditcardtimes;
    @Required
    public String lastdatetime;
    //删除标志
    public boolean DelFlag;//新增加
    public int key;
    public long serverversion;
    public long clientversion;

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

    public void setId(Long id) {
        id = id;
    }

//    public String getBureauNo() {
//        return bureauno;
//    }
//
//    public void setBureauNo(String bureauNo) {
//        this.bureauno = bureauNo;
//    }

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

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(String createdatetime) {
        this.createdatetime = createdatetime;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getSjid() {
        return sjid;
    }

    public void setSjid(String sjid) {
        this.sjid = sjid;
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

    public String getPurchasetotalthisyear() {
        return purchasetotalthisyear;
    }

    public void setPurchasetotalthisyear(String purchasetotalthisyear) {
        this.purchasetotalthisyear = purchasetotalthisyear;
    }

    public String getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(String overdraft) {
        this.overdraft = overdraft;
    }

    public String getAlarmvalue() {
        return alarmvalue;
    }

    public void setAlarmvalue(String alarmvalue) {
        this.alarmvalue = alarmvalue;
    }

    public String getCredentialno() {
        return credentialno;
    }

    public void setCredentialno(String credentialno) {
        this.credentialno = credentialno;
    }

    public String getLimitsj1() {
        return limitsj1;
    }

    public void setLimitsj1(String limitsj1) {
        this.limitsj1 = limitsj1;
    }

    public String getLimitsj2() {
        return limitsj2;
    }

    public void setLimitsj2(String limitsj2) {
        this.limitsj2 = limitsj2;
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

    public String getStopflag() {
        return stopflag;
    }

    public void setStopflag(String stopflag) {
        this.stopflag = stopflag;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getCreditcardtimes() {
        return creditcardtimes;
    }

    public void setCreditcardtimes(String creditcardtimes) {
        this.creditcardtimes = creditcardtimes;
    }

//    public String getTimespan() {
//        return timespan;
//    }
//
//    public void setTimespan(String timespan) {
//        this.timespan = timespan;
//    }

    public String getLastdatetime() {
        return lastdatetime;
    }

    public void setLastdatetime(String lastdatetime) {
        this.lastdatetime = lastdatetime;
    }

//    public String getVersion() {
//        return version;
//    }
//
//    public void setVersion(String version) {
//        this.version = version;
//    }
}
