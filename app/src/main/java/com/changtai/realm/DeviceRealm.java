package com.changtai.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * 机井表
 * Created by qjcjo on 2018/4/1.
 */

public class DeviceRealm extends RealmObject {

    @PrimaryKey
    public Long deviceNo;//机井编号
    @Required
    public Long stationNo;//售水站号
    @Required
    public String gprsNo;//GPS模块编号
    @Required
    public String deviceName;//机井名称
    public int index;//索引
    @Required
    public String linkman;//联系人
    @Required
    public String phone;//电话
    @Required
    public String location;//安装位置
    @Required
    public String createDatetime;//登记时间
    @Required
    public String longitude;//经度
    @Required
    public String latitude;//维度
    @Required
    public String comment;//备注
    @Required
    public String administratorName;//操作员
    @Required
    public String stopFlag;//停用标志

    public long serverVersion;//服务器端数据编号
    public long clientVersion;//pc端数据版本

    public boolean DelFlag;//删除标志

    public int key;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isDelFlag() {
        return DelFlag;
    }

    public void setDelFlag(boolean delFlag) {
        DelFlag = delFlag;
    }

    public Long getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(Long deviceNo) {
        this.deviceNo = deviceNo;
    }

//    public Long getBureauno() {
//        return bureauno;
//    }
//
//    public void setBureauno(Long bureauno) {
//        this.bureauno = bureauno;
//    }

    public Long getStationNo() {
        return stationNo;
    }

    public void setStationNo(Long stationNo) {
        this.stationNo = stationNo;
    }

    public String getGprsNo() {
        return gprsNo;
    }

    public void setGprsNo(String gprsNo) {
        this.gprsNo = gprsNo;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAdministratorName() {
        return administratorName;
    }

    public void setAdministratorName(String administratorName) {
        this.administratorName = administratorName;
    }

//    public String getTimeSpan() {
//        return timeSpan;
//    }
//
//    public void setTimeSpan(String timeSpan) {
//        this.timeSpan = timeSpan;
//    }

    public String getStopFlag() {
        return stopFlag;
    }

    public void setStopFlag(String stopFlag) {
        this.stopFlag = stopFlag;
    }

//    public long getVersion() {
//        return version;
//    }
//
//    public void setVersion(long version) {
//        this.version = version;
//    }

    public long getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(long serverVersion) {
        this.serverVersion = serverVersion;
    }

    public long getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(long clientVersion) {
        this.clientVersion = clientVersion;
    }
}
