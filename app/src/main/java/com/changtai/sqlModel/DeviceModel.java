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
public class DeviceModel {
    @Id
    public Long DeviceId;
    @Property
    public String DeviceNo;//机井编号
    @Property
    public String BureauNo;
    @Property
    public String StationNo;//售水站号
    @Property
    public String GprsNo;//GPS模块编号
    @Property
    public String DeviceName;//机井名称

    public int Index;//索引
    @Property
    public String Linkman;//联系人
    @Property
    public String Phone;//电话
    @Property
    public String Location;//安装位置
    @Property
    public Date CreateDatetime;//登记时间
    @Property
    public String Longitude;//经度
    @Property
    public String Latitude;//维度
    @Property
    public String Comment;//备注
    @Property
    public String AdministratorName;//操作员
    @Property
    public boolean StopFlag;//停用标志
    @Property
    public long ServerVersion;//服务器端数据编号
    @Property
    public long ClientVersion;//pc端数据版本
    @Property
    public boolean DelFlag;//删除标志
    @Property
    public int key;
    @Generated(hash = 1711113101)
    public DeviceModel(Long DeviceId, String DeviceNo, String BureauNo,
            String StationNo, String GprsNo, String DeviceName, int Index,
            String Linkman, String Phone, String Location, Date CreateDatetime,
            String Longitude, String Latitude, String Comment,
            String AdministratorName, boolean StopFlag, long ServerVersion,
            long ClientVersion, boolean DelFlag, int key) {
        this.DeviceId = DeviceId;
        this.DeviceNo = DeviceNo;
        this.BureauNo = BureauNo;
        this.StationNo = StationNo;
        this.GprsNo = GprsNo;
        this.DeviceName = DeviceName;
        this.Index = Index;
        this.Linkman = Linkman;
        this.Phone = Phone;
        this.Location = Location;
        this.CreateDatetime = CreateDatetime;
        this.Longitude = Longitude;
        this.Latitude = Latitude;
        this.Comment = Comment;
        this.AdministratorName = AdministratorName;
        this.StopFlag = StopFlag;
        this.ServerVersion = ServerVersion;
        this.ClientVersion = ClientVersion;
        this.DelFlag = DelFlag;
        this.key = key;
    }
    @Generated(hash = 210163102)
    public DeviceModel() {
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
    public long getClientVersion() {
        return this.ClientVersion;
    }
    public void setClientVersion(long clientVersion) {
        this.ClientVersion = clientVersion;
    }

    public Long getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(Long deviceId) {
        DeviceId = deviceId;
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

    public String getGprsNo() {
        return GprsNo;
    }

    public void setGprsNo(String gprsNo) {
        GprsNo = gprsNo;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    public int getIndex() {
        return Index;
    }

    public void setIndex(int index) {
        Index = index;
    }

    public String getLinkman() {
        return Linkman;
    }

    public void setLinkman(String linkman) {
        Linkman = linkman;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Date getCreateDatetime() {
        return CreateDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        CreateDatetime = createDatetime;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
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

    public long getServerVersion() {
        return ServerVersion;
    }

    public void setServerVersion(long serverVersion) {
        ServerVersion = serverVersion;
    }

    public boolean isDelFlag() {
        return DelFlag;
    }

    public String getBureauNo() {
        return BureauNo;
    }

    public void setBureauNo(String bureauNo) {
        BureauNo = bureauNo;
    }
    public boolean getStopFlag() {
        return this.StopFlag;
    }
}
