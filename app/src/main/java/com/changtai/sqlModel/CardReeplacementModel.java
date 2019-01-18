package com.changtai.sqlModel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.math.BigDecimal;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class CardReeplacementModel {

    @Id
    public Long id;
    @Property
    public String CardReplacementId;//表示字段,相关表的主键,新建该实体时该属性的位为UUID

    @Property
    public String StationNo;//售水站号

    @Property
    public String DeviceNo;//机井号

    @Property
    public String Linkman;//联系人

    @Property
    public String Phone;//联系方式

    @Property
    public String Location;//安装位置

    @Property
    public String AdministratorName;//操作员
    @Property
    public Long ServerVersion;//新增加 //服务器端数据版本

    @Property
    public long ClientVersion;//新增加 //电脑版售水软件数据版本

    @Property
    public String UserName;//用户名

    @Property
    public String UserNo;//用户号

    @Property
    public String UsedTotal;//累计用水量

    @Property
    public String PurchaseTotal;//累计购水量

    @Property
    public Date CreateDateTime;//补卡时间

    @Property
    public String LastTotal;//剩余水量=累计购水量-累计用水量

    @Property
    public Date LastDateTime;//上一次购水日期;补卡前最后购水时间

    @Generated(hash = 1122107132)
    public CardReeplacementModel(Long id, String CardReplacementId,
            String StationNo, String DeviceNo, String Linkman, String Phone,
            String Location, String AdministratorName, Long ServerVersion,
            long ClientVersion, String UserName, String UserNo, String UsedTotal,
            String PurchaseTotal, Date CreateDateTime, String LastTotal,
            Date LastDateTime) {
        this.id = id;
        this.CardReplacementId = CardReplacementId;
        this.StationNo = StationNo;
        this.DeviceNo = DeviceNo;
        this.Linkman = Linkman;
        this.Phone = Phone;
        this.Location = Location;
        this.AdministratorName = AdministratorName;
        this.ServerVersion = ServerVersion;
        this.ClientVersion = ClientVersion;
        this.UserName = UserName;
        this.UserNo = UserNo;
        this.UsedTotal = UsedTotal;
        this.PurchaseTotal = PurchaseTotal;
        this.CreateDateTime = CreateDateTime;
        this.LastTotal = LastTotal;
        this.LastDateTime = LastDateTime;
    }

    @Generated(hash = 996574645)
    public CardReeplacementModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardReplacementId() {
        return this.CardReplacementId;
    }

    public void setCardReplacementId(String CardReplacementId) {
        this.CardReplacementId = CardReplacementId;
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

    public String getLinkman() {
        return this.Linkman;
    }

    public void setLinkman(String Linkman) {
        this.Linkman = Linkman;
    }

    public String getPhone() {
        return this.Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getLocation() {
        return this.Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getAdministratorName() {
        return this.AdministratorName;
    }

    public void setAdministratorName(String AdministratorName) {
        this.AdministratorName = AdministratorName;
    }

    public Long getServerVersion() {
        return this.ServerVersion;
    }

    public void setServerVersion(Long ServerVersion) {
        this.ServerVersion = ServerVersion;
    }

    public long getClientVersion() {
        return this.ClientVersion;
    }

    public void setClientVersion(long ClientVersion) {
        this.ClientVersion = ClientVersion;
    }

    public String getUserName() {
        return this.UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserNo() {
        return this.UserNo;
    }

    public void setUserNo(String UserNo) {
        this.UserNo = UserNo;
    }

    public String getUsedTotal() {
        return this.UsedTotal;
    }

    public void setUsedTotal(String UsedTotal) {
        this.UsedTotal = UsedTotal;
    }

    public String getPurchaseTotal() {
        return this.PurchaseTotal;
    }

    public void setPurchaseTotal(String PurchaseTotal) {
        this.PurchaseTotal = PurchaseTotal;
    }

    public Date getCreateDateTime() {
        return this.CreateDateTime;
    }

    public void setCreateDateTime(Date CreateDateTime) {
        this.CreateDateTime = CreateDateTime;
    }

    public String getLastTotal() {
        return this.LastTotal;
    }

    public void setLastTotal(String LastTotal) {
        this.LastTotal = LastTotal;
    }

    public Date getLastDateTime() {
        return this.LastDateTime;
    }

    public void setLastDateTime(Date LastDateTime) {
        this.LastDateTime = LastDateTime;
    }

}
