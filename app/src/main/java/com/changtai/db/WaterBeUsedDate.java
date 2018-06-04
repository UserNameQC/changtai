package com.changtai.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Qiao on 2018/1/28.
 */

@Entity
public class WaterBeUsedDate {
    @Id
    public Long Id;
    @Property
    public String BureauNo;
    @Property
    public String StationNo;
    @Property
    public String DeviceNo;
    @Property
    public String UserNo;
    @Property
    public String Flow;
    @Property
    public String UsedTotal;
    @Property
    public String PurchaseTotal;
    @Property
    public String Overdraft;
    @Property
    public String AlarmValue;
    @Property
    public String PurchaseDate;
    @Property
    public String CreateDateTime;
    public String getCreateDateTime() {
        return this.CreateDateTime;
    }
    public void setCreateDateTime(String CreateDateTime) {
        this.CreateDateTime = CreateDateTime;
    }
    public String getPurchaseDate() {
        return this.PurchaseDate;
    }
    public void setPurchaseDate(String PurchaseDate) {
        this.PurchaseDate = PurchaseDate;
    }
    public String getAlarmValue() {
        return this.AlarmValue;
    }
    public void setAlarmValue(String AlarmValue) {
        this.AlarmValue = AlarmValue;
    }
    public String getOverdraft() {
        return this.Overdraft;
    }
    public void setOverdraft(String Overdraft) {
        this.Overdraft = Overdraft;
    }
    public String getPurchaseTotal() {
        return this.PurchaseTotal;
    }
    public void setPurchaseTotal(String PurchaseTotal) {
        this.PurchaseTotal = PurchaseTotal;
    }
    public String getUsedTotal() {
        return this.UsedTotal;
    }
    public void setUsedTotal(String UsedTotal) {
        this.UsedTotal = UsedTotal;
    }
    public String getFlow() {
        return this.Flow;
    }
    public void setFlow(String Flow) {
        this.Flow = Flow;
    }
    public String getUserNo() {
        return this.UserNo;
    }
    public void setUserNo(String UserNo) {
        this.UserNo = UserNo;
    }
    public String getDeviceNo() {
        return this.DeviceNo;
    }
    public void setDeviceNo(String DeviceNo) {
        this.DeviceNo = DeviceNo;
    }
    public String getStationNo() {
        return this.StationNo;
    }
    public void setStationNo(String StationNo) {
        this.StationNo = StationNo;
    }
    public String getBureauNo() {
        return this.BureauNo;
    }
    public void setBureauNo(String BureauNo) {
        this.BureauNo = BureauNo;
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    @Generated(hash = 1916784431)
    public WaterBeUsedDate(Long Id, String BureauNo, String StationNo,
            String DeviceNo, String UserNo, String Flow, String UsedTotal,
            String PurchaseTotal, String Overdraft, String AlarmValue,
            String PurchaseDate, String CreateDateTime) {
        this.Id = Id;
        this.BureauNo = BureauNo;
        this.StationNo = StationNo;
        this.DeviceNo = DeviceNo;
        this.UserNo = UserNo;
        this.Flow = Flow;
        this.UsedTotal = UsedTotal;
        this.PurchaseTotal = PurchaseTotal;
        this.Overdraft = Overdraft;
        this.AlarmValue = AlarmValue;
        this.PurchaseDate = PurchaseDate;
        this.CreateDateTime = CreateDateTime;
    }
    @Generated(hash = 1281596652)
    public WaterBeUsedDate() {
    }
}
