package com.changtai.sqlModel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by qjcjob on 2018/7/4.
 */
@Entity
public class UserModel {
    @Id
    public Long Id;
    @Property
    public String UserNo;
    @Property
    public String StationNo;
    @Property
    public String DeviceNo;
    @Property
    public Integer Index;
    @Property
    public String UserName;
    @Property
    public String Phone;
    @Property
    public Date CreateDatetime;
    @Property
    public String Linkman;
    @Property
    public Integer SjId;
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
    public boolean StopFlag;
    @Property
    public String CardNo;
    @Property
    public Integer CreditcardTimes;
    @Property
    public Date LastDatetime;
    //删除标志
    @Property
    public boolean DelFlag;//新增加
    @Property
    public int key;
    @Property
    public long ServerVersion;
    @Property
    public long ClientVersion;

    @Generated(hash = 1785884839)
    public UserModel(Long Id, String UserNo, String StationNo, String DeviceNo,
            Integer Index, String UserName, String Phone, Date CreateDatetime,
            String Linkman, Integer SjId, String UsedTotal, String PurchaseTotal,
            String PurchaseTotalThisYear, String Overdraft, String AlarmValue,
            String CredentialNo, String LimitSj1, String LimitSj2, String Comment,
            String AdministratorName, boolean StopFlag, String CardNo,
            Integer CreditcardTimes, Date LastDatetime, boolean DelFlag, int key,
            long ServerVersion, long ClientVersion) {
        this.Id = Id;
        this.UserNo = UserNo;
        this.StationNo = StationNo;
        this.DeviceNo = DeviceNo;
        this.Index = Index;
        this.UserName = UserName;
        this.Phone = Phone;
        this.CreateDatetime = CreateDatetime;
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
        this.CreditcardTimes = CreditcardTimes;
        this.LastDatetime = LastDatetime;
        this.DelFlag = DelFlag;
        this.key = key;
        this.ServerVersion = ServerVersion;
        this.ClientVersion = ClientVersion;
    }

    @Generated(hash = 782181818)
    public UserModel() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUserNo() {
        return UserNo;
    }

    public void setUserNo(String userNo) {
        UserNo = userNo;
    }

    public String getStationNo() {
        return StationNo;
    }

    public void setStationNo(String stationNo) {
        StationNo = stationNo;
    }

    public String getDeviceNo() {
        return DeviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        DeviceNo = deviceNo;
    }

    public Integer getIndex() {
        return Index;
    }

    public void setIndex(Integer index) {
        Index = index;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public Date getCreateDatetime() {
        return CreateDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        CreateDatetime = createDatetime;
    }

    public String getLinkman() {
        return Linkman;
    }

    public void setLinkman(String linkman) {
        Linkman = linkman;
    }

    public Integer getSjId() {
        return SjId;
    }

    public void setSjId(Integer sjId) {
        SjId = sjId;
    }

    public String getUsedTotal() {
        return UsedTotal;
    }

    public void setUsedTotal(String usedTotal) {
        UsedTotal = usedTotal;
    }

    public String getPurchaseTotal() {
        return PurchaseTotal;
    }

    public void setPurchaseTotal(String purchaseTotal) {
        PurchaseTotal = purchaseTotal;
    }

    public String getPurchaseTotalThisYear() {
        return PurchaseTotalThisYear;
    }

    public void setPurchaseTotalThisYear(String purchaseTotalThisYear) {
        PurchaseTotalThisYear = purchaseTotalThisYear;
    }

    public String getOverdraft() {
        return Overdraft;
    }

    public void setOverdraft(String overdraft) {
        Overdraft = overdraft;
    }

    public String getAlarmValue() {
        return AlarmValue;
    }

    public void setAlarmValue(String alarmValue) {
        AlarmValue = alarmValue;
    }

    public String getCredentialNo() {
        return CredentialNo;
    }

    public void setCredentialNo(String credentialNo) {
        CredentialNo = credentialNo;
    }

    public String getLimitSj1() {
        return LimitSj1;
    }

    public void setLimitSj1(String limitSj1) {
        LimitSj1 = limitSj1;
    }

    public String getLimitSj2() {
        return LimitSj2;
    }

    public void setLimitSj2(String limitSj2) {
        LimitSj2 = limitSj2;
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

    public boolean isStopFlag() {
        return StopFlag;
    }

    public void setStopFlag(boolean stopFlag) {
        StopFlag = stopFlag;
    }

    public String getCardNo() {
        return CardNo;
    }

    public void setCardNo(String cardNo) {
        CardNo = cardNo;
    }

    public Integer getCreditcardTimes() {
        return CreditcardTimes;
    }

    public void setCreditcardTimes(Integer creditcardTimes) {
        CreditcardTimes = creditcardTimes;
    }

    public Date getLastDatetime() {
        return LastDatetime;
    }

    public void setLastDatetime(Date lastDatetime) {
        LastDatetime = lastDatetime;
    }

    public boolean isDelFlag() {
        return DelFlag;
    }

    public void setDelFlag(boolean delFlag) {
        DelFlag = delFlag;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
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

    public boolean getStopFlag() {
        return this.StopFlag;
    }

    public boolean getDelFlag() {
        return this.DelFlag;
    }
}
