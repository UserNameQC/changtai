package com.changtai.sqlModel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

/**
 * Created by qjcjob on 2018/7/4.
 */
@Entity
public class PurchaseRecordModel {
    @Id(autoincrement = true)
    public Long Id;
    @Property
    public String PurchaseRecordId;
    @Property
    public String DeviceNo;
    @Property
    public String StationNo;
    @Property
    public String UserName;
    @Property
    public String UserNo;
    @Property
    public String PurchaseTotalThisTime;
    @Property
    public String PurchaseaMountThisTime;
    @Property
    public Date PurchaseDatetimeThisTime;
    @Property
    public Integer PurchaseYear;
    @Property
    public String PurchaseTotalThisYear;
    @Property
    public String PurchaseTotal;
    @Property
    public String PriceSj1;
    @Property
    public String TotalSj1;
    @Property
    public String AmountSj1;
    @Property
    public String PriceSj2;
    @Property
    public String TotalSj2;
    @Property
    public String AmountSj2;
    @Property
    public String PriceSj3;
    @Property
    public String TotalSj3;
    @Property
    public String AmountSj3;

    @Property
    public String Comment;
    @Property
    public String AdministratorName;
    @Property
    public long ServerVersion;
    @Property
    public long ClientVersion;
    @Property
    public int key;
    @Generated(hash = 820737512)
    public PurchaseRecordModel(Long Id, String PurchaseRecordId, String DeviceNo,
            String StationNo, String UserName, String UserNo,
            String PurchaseTotalThisTime, String PurchaseaMountThisTime,
            Date PurchaseDatetimeThisTime, Integer PurchaseYear,
            String PurchaseTotalThisYear, String PurchaseTotal, String PriceSj1,
            String TotalSj1, String AmountSj1, String PriceSj2, String TotalSj2,
            String AmountSj2, String PriceSj3, String TotalSj3, String AmountSj3,
            String Comment, String AdministratorName, long ServerVersion,
            long ClientVersion, int key) {
        this.Id = Id;
        this.PurchaseRecordId = PurchaseRecordId;
        this.DeviceNo = DeviceNo;
        this.StationNo = StationNo;
        this.UserName = UserName;
        this.UserNo = UserNo;
        this.PurchaseTotalThisTime = PurchaseTotalThisTime;
        this.PurchaseaMountThisTime = PurchaseaMountThisTime;
        this.PurchaseDatetimeThisTime = PurchaseDatetimeThisTime;
        this.PurchaseYear = PurchaseYear;
        this.PurchaseTotalThisYear = PurchaseTotalThisYear;
        this.PurchaseTotal = PurchaseTotal;
        this.PriceSj1 = PriceSj1;
        this.TotalSj1 = TotalSj1;
        this.AmountSj1 = AmountSj1;
        this.PriceSj2 = PriceSj2;
        this.TotalSj2 = TotalSj2;
        this.AmountSj2 = AmountSj2;
        this.PriceSj3 = PriceSj3;
        this.TotalSj3 = TotalSj3;
        this.AmountSj3 = AmountSj3;
        this.Comment = Comment;
        this.AdministratorName = AdministratorName;
        this.ServerVersion = ServerVersion;
        this.ClientVersion = ClientVersion;
        this.key = key;
    }
    @Generated(hash = 88488600)
    public PurchaseRecordModel() {
    }
    public int getKey() {
        return this.key;
    }
    public void setKey(int key) {
        this.key = key;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPurchaseRecordId() {
        return PurchaseRecordId;
    }

    public void setPurchaseRecordId(String purchaseRecordId) {
        PurchaseRecordId = purchaseRecordId;
    }

    public String getDeviceNo() {
        return DeviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        DeviceNo = deviceNo;
    }

    public String getStationNo() {
        return StationNo;
    }

    public void setStationNo(String stationNo) {
        StationNo = stationNo;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserNo() {
        return UserNo;
    }

    public void setUserNo(String userNo) {
        UserNo = userNo;
    }

    public String getPurchaseTotalThisTime() {
        return PurchaseTotalThisTime;
    }

    public void setPurchaseTotalThisTime(String purchaseTotalThisTime) {
        PurchaseTotalThisTime = purchaseTotalThisTime;
    }

    public String getPurchaseaMountThisTime() {
        return PurchaseaMountThisTime;
    }

    public void setPurchaseaMountThisTime(String purchaseaMountThisTime) {
        PurchaseaMountThisTime = purchaseaMountThisTime;
    }

    public Date getPurchaseDatetimeThisTime() {
        return PurchaseDatetimeThisTime;
    }

    public void setPurchaseDatetimeThisTime(Date purchaseDatetimeThisTime) {
        PurchaseDatetimeThisTime = purchaseDatetimeThisTime;
    }

    public Integer getPurchaseYear() {
        return PurchaseYear;
    }

    public void setPurchaseYear(Integer purchaseYear) {
        PurchaseYear = purchaseYear;
    }

    public String getPurchaseTotalThisYear() {
        return PurchaseTotalThisYear;
    }

    public void setPurchaseTotalThisYear(String purchaseTotalThisYear) {
        PurchaseTotalThisYear = purchaseTotalThisYear;
    }

    public String getPurchaseTotal() {
        return PurchaseTotal;
    }

    public void setPurchaseTotal(String purchaseTotal) {
        PurchaseTotal = purchaseTotal;
    }

    public String getPriceSj1() {
        return PriceSj1;
    }

    public void setPriceSj1(String priceSj1) {
        PriceSj1 = priceSj1;
    }

    public String getTotalSj1() {
        return TotalSj1;
    }

    public void setTotalSj1(String totalSj1) {
        TotalSj1 = totalSj1;
    }

    public String getAmountSj1() {
        return AmountSj1;
    }

    public void setAmountSj1(String amountSj1) {
        AmountSj1 = amountSj1;
    }

    public String getPriceSj2() {
        return PriceSj2;
    }

    public void setPriceSj2(String priceSj2) {
        PriceSj2 = priceSj2;
    }

    public String getTotalSj2() {
        return TotalSj2;
    }

    public void setTotalSj2(String totalSj2) {
        TotalSj2 = totalSj2;
    }

    public String getAmountSj2() {
        return AmountSj2;
    }

    public void setAmountSj2(String amountSj2) {
        AmountSj2 = amountSj2;
    }

    public String getPriceSj3() {
        return PriceSj3;
    }

    public void setPriceSj3(String priceSj3) {
        PriceSj3 = priceSj3;
    }

    public String getTotalSj3() {
        return TotalSj3;
    }

    public void setTotalSj3(String totalSj3) {
        TotalSj3 = totalSj3;
    }

    public String getAmountSj3() {
        return AmountSj3;
    }

    public void setAmountSj3(String amountSj3) {
        AmountSj3 = amountSj3;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getAdministratorName() {
        return AdministratorName;
    }

    public void setAdministratorName(String administratorName) {
        AdministratorName = administratorName;
    }

    public long getServerVersion() {
        return ServerVersion;
    }

    public void setServerVersion(long serverVersion) {
        ServerVersion = serverVersion;
    }

    public long getClientVersion() {
        return ClientVersion;
    }

    public void setClientVersion(long clientVersion) {
        ClientVersion = clientVersion;
    }
}
