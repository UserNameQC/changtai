package com.changtai.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Qiao on 2018/1/28.
 */

@Entity
public class User {
    @Id
    public Long Id;
    @Property
    public Long BureauNo;
    @Property
    public Long StationNo;
    @Property
    public Long DeviceNo;
    @Property
    public Long UserNo;
    @Property
    public String Index;
    @Property
    public String UserName;
    @Property
    public String Phone;
    @Property
    public String CreateDateTime;
    @Property
    public String Linkman;
    @Property
    public String SjId;
    @Property
    public String UsedTotal;
    @Property
    public String PurchaseTotal;
    @Property
    public String PurchaseTotalThisYear;
    @Property
    public String Overdraft;
    @Property
    public String AlarmValue;
    @Property
    public String CredentialNo;
    @Property
    public String LimitSj1;
    @Property
    public String LimitSj2;
    @Property
    public String Comment;
    @Property
    public String AdministratorName;
    @Property
    public String StopFlag;
    @Property
    public String CardNo;
    @Property
    public String CreditCardTimes;
    @Property
    public String TimeSpan;
    @Property
    public String LastDateTime;
    @Property
    public String Version;
    public String getVersion() {
        return this.Version;
    }
    public void setVersion(String Version) {
        this.Version = Version;
    }
    public String getLastDateTime() {
        return this.LastDateTime;
    }
    public void setLastDateTime(String LastDateTime) {
        this.LastDateTime = LastDateTime;
    }
    public String getTimeSpan() {
        return this.TimeSpan;
    }
    public void setTimeSpan(String TimeSpan) {
        this.TimeSpan = TimeSpan;
    }
    public String getCreditCardTimes() {
        return this.CreditCardTimes;
    }
    public void setCreditCardTimes(String CreditCardTimes) {
        this.CreditCardTimes = CreditCardTimes;
    }
    public String getCardNo() {
        return this.CardNo;
    }
    public void setCardNo(String CardNo) {
        this.CardNo = CardNo;
    }
    public String getStopFlag() {
        return this.StopFlag;
    }
    public void setStopFlag(String StopFlag) {
        this.StopFlag = StopFlag;
    }
    public String getAdministratorName() {
        return this.AdministratorName;
    }
    public void setAdministratorName(String AdministratorName) {
        this.AdministratorName = AdministratorName;
    }
    public String getComment() {
        return this.Comment;
    }
    public void setComment(String Comment) {
        this.Comment = Comment;
    }
    public String getLimitSj2() {
        return this.LimitSj2;
    }
    public void setLimitSj2(String LimitSj2) {
        this.LimitSj2 = LimitSj2;
    }
    public String getLimitSj1() {
        return this.LimitSj1;
    }
    public void setLimitSj1(String LimitSj1) {
        this.LimitSj1 = LimitSj1;
    }
    public String getCredentialNo() {
        return this.CredentialNo;
    }
    public void setCredentialNo(String CredentialNo) {
        this.CredentialNo = CredentialNo;
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
    public String getPurchaseTotalThisYear() {
        return this.PurchaseTotalThisYear;
    }
    public void setPurchaseTotalThisYear(String PurchaseTotalThisYear) {
        this.PurchaseTotalThisYear = PurchaseTotalThisYear;
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
    public String getSjId() {
        return this.SjId;
    }
    public void setSjId(String SjId) {
        this.SjId = SjId;
    }
    public String getLinkman() {
        return this.Linkman;
    }
    public void setLinkman(String Linkman) {
        this.Linkman = Linkman;
    }
    public String getCreateDateTime() {
        return this.CreateDateTime;
    }
    public void setCreateDateTime(String CreateDateTime) {
        this.CreateDateTime = CreateDateTime;
    }
    public String getPhone() {
        return this.Phone;
    }
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
    public String getUserName() {
        return this.UserName;
    }
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public String getIndex() {
        return this.Index;
    }
    public void setIndex(String Index) {
        this.Index = Index;
    }
    public Long getUserNo() {
        return this.UserNo;
    }
    public void setUserNo(Long UserNo) {
        this.UserNo = UserNo;
    }
    public Long getDeviceNo() {
        return this.DeviceNo;
    }
    public void setDeviceNo(Long DeviceNo) {
        this.DeviceNo = DeviceNo;
    }
    public Long getStationNo() {
        return this.StationNo;
    }
    public void setStationNo(Long StationNo) {
        this.StationNo = StationNo;
    }
    public Long getBureauNo() {
        return this.BureauNo;
    }
    public void setBureauNo(Long BureauNo) {
        this.BureauNo = BureauNo;
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    @Generated(hash = 1824497178)
    public User(Long Id, Long BureauNo, Long StationNo, Long DeviceNo, Long UserNo,
            String Index, String UserName, String Phone, String CreateDateTime,
            String Linkman, String SjId, String UsedTotal, String PurchaseTotal,
            String PurchaseTotalThisYear, String Overdraft, String AlarmValue,
            String CredentialNo, String LimitSj1, String LimitSj2, String Comment,
            String AdministratorName, String StopFlag, String CardNo,
            String CreditCardTimes, String TimeSpan, String LastDateTime,
            String Version) {
        this.Id = Id;
        this.BureauNo = BureauNo;
        this.StationNo = StationNo;
        this.DeviceNo = DeviceNo;
        this.UserNo = UserNo;
        this.Index = Index;
        this.UserName = UserName;
        this.Phone = Phone;
        this.CreateDateTime = CreateDateTime;
        this.Linkman = Linkman;
        this.SjId = SjId;
        this.UsedTotal = UsedTotal;
        this.PurchaseTotal = PurchaseTotal;
        this.PurchaseTotalThisYear = PurchaseTotalThisYear;
        this.Overdraft = Overdraft;
        this.AlarmValue = AlarmValue;
        this.CredentialNo = CredentialNo;
        this.LimitSj1 = LimitSj1;
        this.LimitSj2 = LimitSj2;
        this.Comment = Comment;
        this.AdministratorName = AdministratorName;
        this.StopFlag = StopFlag;
        this.CardNo = CardNo;
        this.CreditCardTimes = CreditCardTimes;
        this.TimeSpan = TimeSpan;
        this.LastDateTime = LastDateTime;
        this.Version = Version;
    }
    @Generated(hash = 586692638)
    public User() {
    }
}

