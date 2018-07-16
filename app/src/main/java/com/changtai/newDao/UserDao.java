package com.changtai.newDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by qjcjob on 2018/7/4.
 */
@Entity
public class UserDao {
    @Id
    public Long Id;
    @Property
    public String userNo;
    @Property
    public String stationNo;
    @Property
    public String deviceNo;
    @Property
    public Integer index;
    @Property
    public String userName;
    @Property
    public String phone;
    @Property
    public Date createDatetime;
    @Property
    public String linkman;
    @Property
    public Integer sjId;
    @Property
    public String usedTotal;
    @Property
    public String purchaseTotal;
    @Property
    public String purchaseTotalThisYear;
    @Property
    public String overdraft;
    @Property
    public String alarmValue;
    @Property
    public String credentialNo;
    @Property
    public String limitSj1;
    @Property
    public String limitSj2;
    @Property
    public String comment;
    @Property
    public String administratorName;
    @Property
    public boolean stopFlag;
    @Property
    public String cardNo;
    @Property
    public Integer creditcardTimes;
    @Property
    public Date lastDatetime;
    //删除标志
    @Property
    public boolean DelFlag;//新增加
    @Property
    public int key;
    @Property
    public long serverVersion;
    @Property
    public long clientVersion;
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
    public int getKey() {
        return this.key;
    }
    public void setKey(int key) {
        this.key = key;
    }
    public boolean getDelFlag() {
        return this.DelFlag;
    }
    public void setDelFlag(boolean DelFlag) {
        this.DelFlag = DelFlag;
    }
    public Date getLastDatetime() {
        return this.lastDatetime;
    }
    public void setLastDatetime(Date lastDatetime) {
        this.lastDatetime = lastDatetime;
    }
    public Integer getCreditcardTimes() {
        return this.creditcardTimes;
    }
    public void setCreditcardTimes(Integer creditcardTimes) {
        this.creditcardTimes = creditcardTimes;
    }
    public String getCardNo() {
        return this.cardNo;
    }
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
    public boolean getStopFlag() {
        return this.stopFlag;
    }
    public void setStopFlag(boolean stopFlag) {
        this.stopFlag = stopFlag;
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
    public String getLimitSj2() {
        return this.limitSj2;
    }
    public void setLimitSj2(String limitSj2) {
        this.limitSj2 = limitSj2;
    }
    public String getLimitSj1() {
        return this.limitSj1;
    }
    public void setLimitSj1(String limitSj1) {
        this.limitSj1 = limitSj1;
    }
    public String getCredentialNo() {
        return this.credentialNo;
    }
    public void setCredentialNo(String credentialNo) {
        this.credentialNo = credentialNo;
    }
    public String getAlarmValue() {
        return this.alarmValue;
    }
    public void setAlarmValue(String alarmValue) {
        this.alarmValue = alarmValue;
    }
    public String getOverdraft() {
        return this.overdraft;
    }
    public void setOverdraft(String overdraft) {
        this.overdraft = overdraft;
    }
    public String getPurchaseTotalThisYear() {
        return this.purchaseTotalThisYear;
    }
    public void setPurchaseTotalThisYear(String purchaseTotalThisYear) {
        this.purchaseTotalThisYear = purchaseTotalThisYear;
    }
    public String getPurchaseTotal() {
        return this.purchaseTotal;
    }
    public void setPurchaseTotal(String purchaseTotal) {
        this.purchaseTotal = purchaseTotal;
    }
    public String getUsedTotal() {
        return this.usedTotal;
    }
    public void setUsedTotal(String usedTotal) {
        this.usedTotal = usedTotal;
    }
    public Integer getSjId() {
        return this.sjId;
    }
    public void setSjId(Integer sjId) {
        this.sjId = sjId;
    }
    public String getLinkman() {
        return this.linkman;
    }
    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }
    public Date getCreateDatetime() {
        return this.createDatetime;
    }
    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Integer getIndex() {
        return this.index;
    }
    public void setIndex(Integer index) {
        this.index = index;
    }
    public String getDeviceNo() {
        return this.deviceNo;
    }
    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
    public String getStationNo() {
        return this.stationNo;
    }
    public void setStationNo(String stationNo) {
        this.stationNo = stationNo;
    }
    public String getUserNo() {
        return this.userNo;
    }
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    @Generated(hash = 1107060793)
    public UserDao(Long Id, String userNo, String stationNo, String deviceNo,
            Integer index, String userName, String phone, Date createDatetime,
            String linkman, Integer sjId, String usedTotal, String purchaseTotal,
            String purchaseTotalThisYear, String overdraft, String alarmValue,
            String credentialNo, String limitSj1, String limitSj2, String comment,
            String administratorName, boolean stopFlag, String cardNo,
            Integer creditcardTimes, Date lastDatetime, boolean DelFlag, int key,
            long serverVersion, long clientVersion) {
        this.Id = Id;
        this.userNo = userNo;
        this.stationNo = stationNo;
        this.deviceNo = deviceNo;
        this.index = index;
        this.userName = userName;
        this.phone = phone;
        this.createDatetime = createDatetime;
        this.linkman = linkman;
        this.sjId = sjId;
        this.usedTotal = usedTotal;
        this.purchaseTotal = purchaseTotal;
        this.purchaseTotalThisYear = purchaseTotalThisYear;
        this.overdraft = overdraft;
        this.alarmValue = alarmValue;
        this.credentialNo = credentialNo;
        this.limitSj1 = limitSj1;
        this.limitSj2 = limitSj2;
        this.comment = comment;
        this.administratorName = administratorName;
        this.stopFlag = stopFlag;
        this.cardNo = cardNo;
        this.creditcardTimes = creditcardTimes;
        this.lastDatetime = lastDatetime;
        this.DelFlag = DelFlag;
        this.key = key;
        this.serverVersion = serverVersion;
        this.clientVersion = clientVersion;
    }
    @Generated(hash = 917059161)
    public UserDao() {
    }
}
