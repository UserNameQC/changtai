package com.changtai.newDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;

import io.realm.annotations.PrimaryKey;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by qjcjob on 2018/7/4.
 */
@Entity
public class DeviceDao {
    @Id
    public int Id;
    @Property
    public String deviceNo;//机井编号
    @Property
    public String stationNo;//售水站号
    @Property
    public String gprsNo;//GPS模块编号
    @Property
    public String deviceName;//机井名称

    public int index;//索引
    @Property
    public String linkman;//联系人
    @Property
    public String phone;//电话
    @Property
    public String location;//安装位置
    @Property
    public Date createDatetime;//登记时间
    @Property
    public String longitude;//经度
    @Property
    public String latitude;//维度
    @Property
    public String comment;//备注
    @Property
    public String administratorName;//操作员
    @Property
    public boolean stopFlag;//停用标志
    @Property
    public long serverVersion;//服务器端数据编号
    @Property
    public long clientVersion;//pc端数据版本
    @Property
    public boolean DelFlag;//删除标志
    @Property
    public int key;
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
    public String getLatitude() {
        return this.latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return this.longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public Date getCreateDatetime() {
        return this.createDatetime;
    }
    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }
    public String getLocation() {
        return this.location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getLinkman() {
        return this.linkman;
    }
    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }
    public int getIndex() {
        return this.index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public String getDeviceName() {
        return this.deviceName;
    }
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    public String getGprsNo() {
        return this.gprsNo;
    }
    public void setGprsNo(String gprsNo) {
        this.gprsNo = gprsNo;
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
    public int getId() {
        return this.Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
    @Generated(hash = 796415932)
    public DeviceDao(int Id, String deviceNo, String stationNo, String gprsNo,
            String deviceName, int index, String linkman, String phone,
            String location, Date createDatetime, String longitude,
            String latitude, String comment, String administratorName,
            boolean stopFlag, long serverVersion, long clientVersion,
            boolean DelFlag, int key) {
        this.Id = Id;
        this.deviceNo = deviceNo;
        this.stationNo = stationNo;
        this.gprsNo = gprsNo;
        this.deviceName = deviceName;
        this.index = index;
        this.linkman = linkman;
        this.phone = phone;
        this.location = location;
        this.createDatetime = createDatetime;
        this.longitude = longitude;
        this.latitude = latitude;
        this.comment = comment;
        this.administratorName = administratorName;
        this.stopFlag = stopFlag;
        this.serverVersion = serverVersion;
        this.clientVersion = clientVersion;
        this.DelFlag = DelFlag;
        this.key = key;
    }
    @Generated(hash = 1468206029)
    public DeviceDao() {
    }
}
