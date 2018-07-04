package com.changtai.newDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import io.realm.annotations.PrimaryKey;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by qjcjob on 2018/7/4.
 */
@Entity
public class PurchaseRecordDao {
    @Id
    public int Id;
    @Property
    public String purchaseRecordId;
    @Property
    public String deviceNo;
    @Property
    public String stationNo;
    @Property
    public String userName;
    @Property
    public String userNo;
    @Property
    public String purchaseTotalThisTime;
    @Property
    public String purchaseaMountThisTime;
    @Property
    public String purchaseDatetimeThisTime;
    @Property
    public Integer purchaseYear;
    @Property
    public String purchaseTotalThisYear;
    @Property
    public String purchaseTotal;
    @Property
    public String priceSj1;
    @Property
    public String totalSj1;
    @Property
    public String amountSj1;
    @Property
    public String priceSj2;
    @Property
    public String totalSj2;
    @Property
    public String amountSj2;
    @Property
    public String priceSj3;
    @Property
    public String totalSj3;
    @Property
    public String amountSj3;

    @Property
    public String comment;
    @Property
    public String administratorName;
    @Property
    public long serverVersion;
    @Property
    public long clientVersion;
    @Property
    public int key;
    public int getKey() {
        return this.key;
    }
    public void setKey(int key) {
        this.key = key;
    }
    public long getClientVersion() {
        return this.clientVersion;
    }
    public void setClientVersion(long clientVersion) {
        this.clientVersion = clientVersion;
    }
    public long getServerVersion() {
        return this.serverVersion;
    }
    public void setServerVersion(long serverVersion) {
        this.serverVersion = serverVersion;
    }
    public String getAdministratorName() {
        return this.administratorName;
    }
    public void setAdministratorName(String administratorName) {
        this.administratorName = administratorName;
    }
    public String getComment() {
        return this.comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getAmountSj3() {
        return this.amountSj3;
    }
    public void setAmountSj3(String amountSj3) {
        this.amountSj3 = amountSj3;
    }
    public String getTotalSj3() {
        return this.totalSj3;
    }
    public void setTotalSj3(String totalSj3) {
        this.totalSj3 = totalSj3;
    }
    public String getPriceSj3() {
        return this.priceSj3;
    }
    public void setPriceSj3(String priceSj3) {
        this.priceSj3 = priceSj3;
    }
    public String getAmountSj2() {
        return this.amountSj2;
    }
    public void setAmountSj2(String amountSj2) {
        this.amountSj2 = amountSj2;
    }
    public String getTotalSj2() {
        return this.totalSj2;
    }
    public void setTotalSj2(String totalSj2) {
        this.totalSj2 = totalSj2;
    }
    public String getPriceSj2() {
        return this.priceSj2;
    }
    public void setPriceSj2(String priceSj2) {
        this.priceSj2 = priceSj2;
    }
    public String getAmountSj1() {
        return this.amountSj1;
    }
    public void setAmountSj1(String amountSj1) {
        this.amountSj1 = amountSj1;
    }
    public String getTotalSj1() {
        return this.totalSj1;
    }
    public void setTotalSj1(String totalSj1) {
        this.totalSj1 = totalSj1;
    }
    public String getPriceSj1() {
        return this.priceSj1;
    }
    public void setPriceSj1(String priceSj1) {
        this.priceSj1 = priceSj1;
    }
    public String getPurchaseTotal() {
        return this.purchaseTotal;
    }
    public void setPurchaseTotal(String purchaseTotal) {
        this.purchaseTotal = purchaseTotal;
    }
    public String getPurchaseTotalThisYear() {
        return this.purchaseTotalThisYear;
    }
    public void setPurchaseTotalThisYear(String purchaseTotalThisYear) {
        this.purchaseTotalThisYear = purchaseTotalThisYear;
    }
    public Integer getPurchaseYear() {
        return this.purchaseYear;
    }
    public void setPurchaseYear(Integer purchaseYear) {
        this.purchaseYear = purchaseYear;
    }
    public String getPurchaseDatetimeThisTime() {
        return this.purchaseDatetimeThisTime;
    }
    public void setPurchaseDatetimeThisTime(String purchaseDatetimeThisTime) {
        this.purchaseDatetimeThisTime = purchaseDatetimeThisTime;
    }
    public String getPurchaseaMountThisTime() {
        return this.purchaseaMountThisTime;
    }
    public void setPurchaseaMountThisTime(String purchaseaMountThisTime) {
        this.purchaseaMountThisTime = purchaseaMountThisTime;
    }
    public String getPurchaseTotalThisTime() {
        return this.purchaseTotalThisTime;
    }
    public void setPurchaseTotalThisTime(String purchaseTotalThisTime) {
        this.purchaseTotalThisTime = purchaseTotalThisTime;
    }
    public String getUserNo() {
        return this.userNo;
    }
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getStationNo() {
        return this.stationNo;
    }
    public void setStationNo(String stationNo) {
        this.stationNo = stationNo;
    }
    public String getDeviceNo() {
        return this.deviceNo;
    }
    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
    public String getPurchaseRecordId() {
        return this.purchaseRecordId;
    }
    public void setPurchaseRecordId(String purchaseRecordId) {
        this.purchaseRecordId = purchaseRecordId;
    }
    public int getId() {
        return this.Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
    @Generated(hash = 209318636)
    public PurchaseRecordDao(int Id, String purchaseRecordId, String deviceNo,
            String stationNo, String userName, String userNo,
            String purchaseTotalThisTime, String purchaseaMountThisTime,
            String purchaseDatetimeThisTime, Integer purchaseYear,
            String purchaseTotalThisYear, String purchaseTotal, String priceSj1,
            String totalSj1, String amountSj1, String priceSj2, String totalSj2,
            String amountSj2, String priceSj3, String totalSj3, String amountSj3,
            String comment, String administratorName, long serverVersion,
            long clientVersion, int key) {
        this.Id = Id;
        this.purchaseRecordId = purchaseRecordId;
        this.deviceNo = deviceNo;
        this.stationNo = stationNo;
        this.userName = userName;
        this.userNo = userNo;
        this.purchaseTotalThisTime = purchaseTotalThisTime;
        this.purchaseaMountThisTime = purchaseaMountThisTime;
        this.purchaseDatetimeThisTime = purchaseDatetimeThisTime;
        this.purchaseYear = purchaseYear;
        this.purchaseTotalThisYear = purchaseTotalThisYear;
        this.purchaseTotal = purchaseTotal;
        this.priceSj1 = priceSj1;
        this.totalSj1 = totalSj1;
        this.amountSj1 = amountSj1;
        this.priceSj2 = priceSj2;
        this.totalSj2 = totalSj2;
        this.amountSj2 = amountSj2;
        this.priceSj3 = priceSj3;
        this.totalSj3 = totalSj3;
        this.amountSj3 = amountSj3;
        this.comment = comment;
        this.administratorName = administratorName;
        this.serverVersion = serverVersion;
        this.clientVersion = clientVersion;
        this.key = key;
    }
    @Generated(hash = 326374686)
    public PurchaseRecordDao() {
    }
}
