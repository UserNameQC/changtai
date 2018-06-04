package com.changtai.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Qiao on 2018/1/28.
 */

@Entity
public class Device {
    @Id
    public Long Id;
    @Property
    public Long BureauNo;
    @Property
    public Long StationNo;
    @Property
    public Long DeviceNo;
    @Property
    public String GprsNo;
    @Property
    public String DeviceName;
    @Property
    public int Index;
    @Property
    public String Linkman;
    @Property
    public String Phone;
    @Property
    public String Location;
    @Property
    public String CreateDateTime;
    @Property
    public String Longitude;
    @Property
    public String Latitude;
    @Property
    public String Comment;
    @Property
    public String AdministratorName;
    @Property
    public String TimeSpan;
    @Property
    public String StopFlag;
    @Property
    public long Version;

    public long getVersion() {
        return this.Version;
    }
    public void setVersion(long Version) {
        this.Version = Version;
    }
    public String getStopFlag() {
        return this.StopFlag;
    }
    public void setStopFlag(String StopFlag) {
        this.StopFlag = StopFlag;
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
    public String getComment() {
        return this.Comment;
    }
    public void setComment(String Comment) {
        this.Comment = Comment;
    }
    public String getLatitude() {
        return this.Latitude;
    }
    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }
    public String getLongitude() {
        return this.Longitude;
    }
    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }
    public String getCreateDateTime() {
        return this.CreateDateTime;
    }
    public void setCreateDateTime(String CreateDateTime) {
        this.CreateDateTime = CreateDateTime;
    }
    public String getLocation() {
        return this.Location;
    }
    public void setLocation(String Location) {
        this.Location = Location;
    }
    public String getPhone() {
        return this.Phone;
    }
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
    public String getLinkman() {
        return this.Linkman;
    }
    public void setLinkman(String Linkman) {
        this.Linkman = Linkman;
    }
    public int getIndex() {
        return this.Index;
    }
    public void setIndex(int Index) {
        this.Index = Index;
    }
    public String getDeviceName() {
        return this.DeviceName;
    }
    public void setDeviceName(String DeviceName) {
        this.DeviceName = DeviceName;
    }
    public String getGprsNo() {
        return this.GprsNo;
    }
    public void setGprsNo(String GprsNo) {
        this.GprsNo = GprsNo;
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
    @Generated(hash = 1204935263)
    public Device(Long Id, Long BureauNo, Long StationNo, Long DeviceNo,
            String GprsNo, String DeviceName, int Index, String Linkman,
            String Phone, String Location, String CreateDateTime, String Longitude,
            String Latitude, String Comment, String AdministratorName,
            String TimeSpan, String StopFlag, long Version) {
        this.Id = Id;
        this.BureauNo = BureauNo;
        this.StationNo = StationNo;
        this.DeviceNo = DeviceNo;
        this.GprsNo = GprsNo;
        this.DeviceName = DeviceName;
        this.Index = Index;
        this.Linkman = Linkman;
        this.Phone = Phone;
        this.Location = Location;
        this.CreateDateTime = CreateDateTime;
        this.Longitude = Longitude;
        this.Latitude = Latitude;
        this.Comment = Comment;
        this.AdministratorName = AdministratorName;
        this.TimeSpan = TimeSpan;
        this.StopFlag = StopFlag;
        this.Version = Version;
    }
    @Generated(hash = 1469582394)
    public Device() {
    }
}
