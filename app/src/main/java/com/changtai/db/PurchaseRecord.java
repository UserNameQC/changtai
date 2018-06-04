package com.changtai.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Qiao on 2018/1/28.
 */

@Entity
public class PurchaseRecord {
    @Id
    public Long Id;
    @Property
    public Long PurchaseRecordId;
    @Property
    public String BureauNo;
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
    public String PurchaseAmountThisTime;
    @Property
    public String PurchaseDateTimeThisTime;
    @Property
    public String PurchaseYear;
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
    public String AdministratorName;
    @Property
    public String TimeSpan;
    @Property
    public String Version;
    public String getVersion() {
        return this.Version;
    }
    public void setVersion(String Version) {
        this.Version = Version;
    }
    public String getTimeSpan() {
        return this.TimeSpan;
    }
    public void setTimeSpan(String TimeSpan) {
        this.TimeSpan = TimeSpan;
    }
    public String getAdministratorName() {
        return this.AdministratorName;
    }
    public void setAdministratorName(String AdministratorName) {
        this.AdministratorName = AdministratorName;
    }
    public String getAmountSj3() {
        return this.AmountSj3;
    }
    public void setAmountSj3(String AmountSj3) {
        this.AmountSj3 = AmountSj3;
    }
    public String getTotalSj3() {
        return this.TotalSj3;
    }
    public void setTotalSj3(String TotalSj3) {
        this.TotalSj3 = TotalSj3;
    }
    public String getPriceSj3() {
        return this.PriceSj3;
    }
    public void setPriceSj3(String PriceSj3) {
        this.PriceSj3 = PriceSj3;
    }
    public String getAmountSj2() {
        return this.AmountSj2;
    }
    public void setAmountSj2(String AmountSj2) {
        this.AmountSj2 = AmountSj2;
    }
    public String getTotalSj2() {
        return this.TotalSj2;
    }
    public void setTotalSj2(String TotalSj2) {
        this.TotalSj2 = TotalSj2;
    }
    public String getPriceSj2() {
        return this.PriceSj2;
    }
    public void setPriceSj2(String PriceSj2) {
        this.PriceSj2 = PriceSj2;
    }
    public String getAmountSj1() {
        return this.AmountSj1;
    }
    public void setAmountSj1(String AmountSj1) {
        this.AmountSj1 = AmountSj1;
    }
    public String getTotalSj1() {
        return this.TotalSj1;
    }
    public void setTotalSj1(String TotalSj1) {
        this.TotalSj1 = TotalSj1;
    }
    public String getPriceSj1() {
        return this.PriceSj1;
    }
    public void setPriceSj1(String PriceSj1) {
        this.PriceSj1 = PriceSj1;
    }
    public String getPurchaseTotal() {
        return this.PurchaseTotal;
    }
    public void setPurchaseTotal(String PurchaseTotal) {
        this.PurchaseTotal = PurchaseTotal;
    }
    public String getPurchaseTotalThisYear() {
        return this.PurchaseTotalThisYear;
    }
    public void setPurchaseTotalThisYear(String PurchaseTotalThisYear) {
        this.PurchaseTotalThisYear = PurchaseTotalThisYear;
    }
    public String getPurchaseYear() {
        return this.PurchaseYear;
    }
    public void setPurchaseYear(String PurchaseYear) {
        this.PurchaseYear = PurchaseYear;
    }
    public String getPurchaseDateTimeThisTime() {
        return this.PurchaseDateTimeThisTime;
    }
    public void setPurchaseDateTimeThisTime(String PurchaseDateTimeThisTime) {
        this.PurchaseDateTimeThisTime = PurchaseDateTimeThisTime;
    }
    public String getPurchaseAmountThisTime() {
        return this.PurchaseAmountThisTime;
    }
    public void setPurchaseAmountThisTime(String PurchaseAmountThisTime) {
        this.PurchaseAmountThisTime = PurchaseAmountThisTime;
    }
    public String getPurchaseTotalThisTime() {
        return this.PurchaseTotalThisTime;
    }
    public void setPurchaseTotalThisTime(String PurchaseTotalThisTime) {
        this.PurchaseTotalThisTime = PurchaseTotalThisTime;
    }
    public String getUserNo() {
        return this.UserNo;
    }
    public void setUserNo(String UserNo) {
        this.UserNo = UserNo;
    }
    public String getUserName() {
        return this.UserName;
    }
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public String getStationNo() {
        return this.StationNo;
    }
    public void setStationNo(String StationNo) {
        this.StationNo = StationNo;
    }
    public String getDeviceNo() {
        return this.DeviceNo;
    }
    public void setDeviceNo(String DeviceNo) {
        this.DeviceNo = DeviceNo;
    }
    public String getBureauNo() {
        return this.BureauNo;
    }
    public void setBureauNo(String BureauNo) {
        this.BureauNo = BureauNo;
    }
    public Long getPurchaseRecordId() {
        return this.PurchaseRecordId;
    }
    public void setPurchaseRecordId(Long PurchaseRecordId) {
        this.PurchaseRecordId = PurchaseRecordId;
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    @Generated(hash = 1668499666)
    public PurchaseRecord(Long Id, Long PurchaseRecordId, String BureauNo,
            String DeviceNo, String StationNo, String UserName, String UserNo,
            String PurchaseTotalThisTime, String PurchaseAmountThisTime,
            String PurchaseDateTimeThisTime, String PurchaseYear,
            String PurchaseTotalThisYear, String PurchaseTotal, String PriceSj1,
            String TotalSj1, String AmountSj1, String PriceSj2, String TotalSj2,
            String AmountSj2, String PriceSj3, String TotalSj3, String AmountSj3,
            String AdministratorName, String TimeSpan, String Version) {
        this.Id = Id;
        this.PurchaseRecordId = PurchaseRecordId;
        this.BureauNo = BureauNo;
        this.DeviceNo = DeviceNo;
        this.StationNo = StationNo;
        this.UserName = UserName;
        this.UserNo = UserNo;
        this.PurchaseTotalThisTime = PurchaseTotalThisTime;
        this.PurchaseAmountThisTime = PurchaseAmountThisTime;
        this.PurchaseDateTimeThisTime = PurchaseDateTimeThisTime;
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
        this.AdministratorName = AdministratorName;
        this.TimeSpan = TimeSpan;
        this.Version = Version;
    }
    @Generated(hash = 1397732425)
    public PurchaseRecord() {
    }
}
