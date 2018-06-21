package com.changtai.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * 购水用户表
 * Created by qjcjo on 2018/4/1.
 */

public class UserRealm extends RealmObject {

    @PrimaryKey
    public String userNo;
    @Required
    public String stationNo;
    @Required
    public String deviceNo;
    @Required
    public String index;
    @Required
    public String userName;
    @Required
    public String phone;
    @Required
    public String createDatetime;
    @Required
    public String linkman;
    @Required
    public String sjId;
    @Required
    public String usedTotal;
    @Required
    public String purchaseTotal;
    @Required
    public String purchaseTotalThisYear;
    @Required
    public String overdraft;
    @Required
    public String alarmValue;
    @Required
    public String credentialNo;
    @Required
    public String limitSj1;
    @Required
    public String limitSj2;
    @Required
    public String comment;
    @Required
    public String administratorName;
    @Required
    public String stopFlag;
    @Required
    public String cardNo;
    @Required
    public String creditcardTimes;
    @Required
    public String lastDatetime;
    //删除标志
    public boolean DelFlag;//新增加
    public int key;
    public long serverVersion;
    public long clientVersion;

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

    public long getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(long serverVersion) {
        this.serverVersion = serverVersion;
    }

    public long getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(long clientVersion) {
        this.clientVersion = clientVersion;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getStationNo() {
        return stationNo;
    }

    public void setStationNo(String stationNo) {
        this.stationNo = stationNo;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getSjId() {
        return sjId;
    }

    public void setSjId(String sjId) {
        this.sjId = sjId;
    }

    public String getUsedTotal() {
        return usedTotal;
    }

    public void setUsedTotal(String usedTotal) {
        this.usedTotal = usedTotal;
    }

    public String getPurchaseTotal() {
        return purchaseTotal;
    }

    public void setPurchaseTotal(String purchaseTotal) {
        this.purchaseTotal = purchaseTotal;
    }

    public String getPurchaseTotalThisYear() {
        return purchaseTotalThisYear;
    }

    public void setPurchaseTotalThisYear(String purchaseTotalThisYear) {
        this.purchaseTotalThisYear = purchaseTotalThisYear;
    }

    public String getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(String overdraft) {
        this.overdraft = overdraft;
    }

    public String getAlarmValue() {
        return alarmValue;
    }

    public void setAlarmValue(String alarmValue) {
        this.alarmValue = alarmValue;
    }

    public String getCredentialNo() {
        return credentialNo;
    }

    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }

    public String getLimitSj1() {
        return limitSj1;
    }

    public void setLimitSj1(String limitSj1) {
        this.limitSj1 = limitSj1;
    }

    public String getLimitSj2() {
        return limitSj2;
    }

    public void setLimitSj2(String limitSj2) {
        this.limitSj2 = limitSj2;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAdministratorName() {
        return administratorName;
    }

    public void setAdministratorName(String administratorName) {
        this.administratorName = administratorName;
    }

    public String getStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(String stopFlag) {
        this.stopFlag = stopFlag;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCreditcardTimes() {
        return creditcardTimes;
    }

    public void setCreditcardTimes(String creditcardTimes) {
        this.creditcardTimes = creditcardTimes;
    }

    public String getLastDatetime() {
        return lastDatetime;
    }

    public void setLastDatetime(String lastDatetime) {
        this.lastDatetime = lastDatetime;
    }

}
