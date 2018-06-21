package com.changtai.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * 售水记录表
 * Created by qjcjo on 2018/4/1.
 */

public class PurchaseRecordRealm extends RealmObject {

    @PrimaryKey
    public String purchaseRecordId;
//    @Required
//    public String bureauno;
    @Required
    public String deviceNo;
    @Required
    public String stationNo;
    @Required
    public String userName;
    @Required
    public String userNo;
    @Required
    public String purchaseTotalThisTime;
    @Required
    public String purchaseaMountThisTime;
    @Required
    public String purchaseDatetimeThisTime;
    @Required
    public String purchaseYear;
    @Required
    public String purchaseTotalThisYear;
    @Required
    public String purchaseTotal;
    @Required
    public String priceSj1;
    @Required
    public String totalSj1;
    @Required
    public String amountSj1;
    @Required
    public String priceSj2;
    @Required
    public String totalSj2;
    @Required
    public String amountSj2;
    @Required
    public String priceSj3;
    @Required
    public String totalSj3;
    @Required
    public String amountSj3;

    @Required
    public String comment;
    @Required
    public String administratorName;
//    @Required
//    public String timespan;
//    @Required
//    public String version;
    public long serverVersion;

    public long clientVersion;

    public int key;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getPurchaseRecordId() {
        return purchaseRecordId;
    }

    public void setPurchaseRecordId(String purchaseRecordId) {
        this.purchaseRecordId = purchaseRecordId;
    }

//    public String getBureauno() {
//        return bureauno;
//    }
//
//    public void setBureauno(String bureauno) {
//        this.bureauno = bureauno;
//    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getStationNo() {
        return stationNo;
    }

    public void setStationNo(String stationNo) {
        this.stationNo = stationNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getPurchaseTotalThisTime() {
        return purchaseTotalThisTime;
    }

    public void setPurchaseTotalThisTime(String purchaseTotalThisTime) {
        this.purchaseTotalThisTime = purchaseTotalThisTime;
    }

    public String getPurchaseaMountThisTime() {
        return purchaseaMountThisTime;
    }

    public void setPurchaseaMountThisTime(String purchaseaMountThisTime) {
        this.purchaseaMountThisTime = purchaseaMountThisTime;
    }

    public String getPurchaseDatetimeThisTime() {
        return purchaseDatetimeThisTime;
    }

    public void setPurchaseDatetimeThisTime(String purchaseDatetimeThisTime) {
        this.purchaseDatetimeThisTime = purchaseDatetimeThisTime;
    }

    public String getPurchaseYear() {
        return purchaseYear;
    }

    public void setPurchaseYear(String purchaseYear) {
        this.purchaseYear = purchaseYear;
    }

    public String getPurchaseTotalThisYear() {
        return purchaseTotalThisYear;
    }

    public void setPurchaseTotalThisYear(String purchaseTotalThisYear) {
        this.purchaseTotalThisYear = purchaseTotalThisYear;
    }

    public String getPurchaseTotal() {
        return purchaseTotal;
    }

    public void setPurchaseTotal(String purchaseTotal) {
        this.purchaseTotal = purchaseTotal;
    }

    public String getPriceSj1() {
        return priceSj1;
    }

    public void setPriceSj1(String priceSj1) {
        this.priceSj1 = priceSj1;
    }

    public String getTotalSj1() {
        return totalSj1;
    }

    public void setTotalSj1(String totalSj1) {
        this.totalSj1 = totalSj1;
    }

    public String getAmountSj1() {
        return amountSj1;
    }

    public void setAmountSj1(String amountSj1) {
        this.amountSj1 = amountSj1;
    }

    public String getPriceSj2() {
        return priceSj2;
    }

    public void setPriceSj2(String priceSj2) {
        this.priceSj2 = priceSj2;
    }

    public String getTotalSj2() {
        return totalSj2;
    }

    public void setTotalSj2(String totalSj2) {
        this.totalSj2 = totalSj2;
    }

    public String getAmountSj2() {
        return amountSj2;
    }

    public void setAmountSj2(String amountSj2) {
        this.amountSj2 = amountSj2;
    }

    public String getPriceSj3() {
        return priceSj3;
    }

    public void setPriceSj3(String priceSj3) {
        this.priceSj3 = priceSj3;
    }

    public String getTotalSj3() {
        return totalSj3;
    }

    public void setTotalSj3(String totalSj3) {
        this.totalSj3 = totalSj3;
    }

    public String getAmountSj3() {
        return amountSj3;
    }

    public void setAmountSj3(String amountSj3) {
        this.amountSj3 = amountSj3;
    }

    public String getAdministratorName() {
        return administratorName;
    }

    public void setAdministratorName(String administratorName) {
        this.administratorName = administratorName;
    }

//    public String getTimespan() {
//        return timespan;
//    }
//
//    public void setTimespan(String timespan) {
//        this.timespan = timespan;
//    }
//
//    public String getVersion() {
//        return version;
//    }
//
//    public void setVersion(String version) {
//        this.version = version;
//    }
}
