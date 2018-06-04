package com.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Device implements Serializable {
    private String bureauno;

    private String stationno;

    private String deviceno;

    private String gprsno;

    private String devicename;

    private Integer index;

    private String linkman;

    private String phone;

    private String location;

    private Date createdatetime;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String comment;

    private String administratorname;

    private Long timespan;

    private Boolean stopflag;

    private Long version;

    private static final long serialVersionUID = 1L;

    public String getBureauno() {
        return bureauno;
    }

    public void setBureauno(String bureauno) {
        this.bureauno = bureauno;
    }

    public String getStationno() {
        return stationno;
    }

    public void setStationno(String stationno) {
        this.stationno = stationno;
    }

    public String getDeviceno() {
        return deviceno;
    }

    public void setDeviceno(String deviceno) {
        this.deviceno = deviceno;
    }

    public String getGprsno() {
        return gprsno;
    }

    public void setGprsno(String gprsno) {
        this.gprsno = gprsno;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
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

    public Date getCreatedatetime() {
        return createdatetime;
    }

    public void setCreatedatetime(Date createdatetime) {
        this.createdatetime = createdatetime;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAdministratorname() {
        return administratorname;
    }

    public void setAdministratorname(String administratorname) {
        this.administratorname = administratorname;
    }

    public Long getTimespan() {
        return timespan;
    }

    public void setTimespan(Long timespan) {
        this.timespan = timespan;
    }

    public Boolean getStopflag() {
        return stopflag;
    }

    public void setStopflag(Boolean stopflag) {
        this.stopflag = stopflag;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bureauno=").append(bureauno);
        sb.append(", stationno=").append(stationno);
        sb.append(", deviceno=").append(deviceno);
        sb.append(", gprsno=").append(gprsno);
        sb.append(", devicename=").append(devicename);
        sb.append(", index=").append(index);
        sb.append(", linkman=").append(linkman);
        sb.append(", phone=").append(phone);
        sb.append(", location=").append(location);
        sb.append(", createdatetime=").append(createdatetime);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", comment=").append(comment);
        sb.append(", administratorname=").append(administratorname);
        sb.append(", timespan=").append(timespan);
        sb.append(", stopflag=").append(stopflag);
        sb.append(", version=").append(version);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Device other = (Device) that;
        return (this.getBureauno() == null ? other.getBureauno() == null : this.getBureauno().equals(other.getBureauno()))
            && (this.getStationno() == null ? other.getStationno() == null : this.getStationno().equals(other.getStationno()))
            && (this.getDeviceno() == null ? other.getDeviceno() == null : this.getDeviceno().equals(other.getDeviceno()))
            && (this.getGprsno() == null ? other.getGprsno() == null : this.getGprsno().equals(other.getGprsno()))
            && (this.getDevicename() == null ? other.getDevicename() == null : this.getDevicename().equals(other.getDevicename()))
            && (this.getIndex() == null ? other.getIndex() == null : this.getIndex().equals(other.getIndex()))
            && (this.getLinkman() == null ? other.getLinkman() == null : this.getLinkman().equals(other.getLinkman()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getCreatedatetime() == null ? other.getCreatedatetime() == null : this.getCreatedatetime().equals(other.getCreatedatetime()))
            && (this.getLongitude() == null ? other.getLongitude() == null : this.getLongitude().equals(other.getLongitude()))
            && (this.getLatitude() == null ? other.getLatitude() == null : this.getLatitude().equals(other.getLatitude()))
            && (this.getComment() == null ? other.getComment() == null : this.getComment().equals(other.getComment()))
            && (this.getAdministratorname() == null ? other.getAdministratorname() == null : this.getAdministratorname().equals(other.getAdministratorname()))
            && (this.getTimespan() == null ? other.getTimespan() == null : this.getTimespan().equals(other.getTimespan()))
            && (this.getStopflag() == null ? other.getStopflag() == null : this.getStopflag().equals(other.getStopflag()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBureauno() == null) ? 0 : getBureauno().hashCode());
        result = prime * result + ((getStationno() == null) ? 0 : getStationno().hashCode());
        result = prime * result + ((getDeviceno() == null) ? 0 : getDeviceno().hashCode());
        result = prime * result + ((getGprsno() == null) ? 0 : getGprsno().hashCode());
        result = prime * result + ((getDevicename() == null) ? 0 : getDevicename().hashCode());
        result = prime * result + ((getIndex() == null) ? 0 : getIndex().hashCode());
        result = prime * result + ((getLinkman() == null) ? 0 : getLinkman().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getCreatedatetime() == null) ? 0 : getCreatedatetime().hashCode());
        result = prime * result + ((getLongitude() == null) ? 0 : getLongitude().hashCode());
        result = prime * result + ((getLatitude() == null) ? 0 : getLatitude().hashCode());
        result = prime * result + ((getComment() == null) ? 0 : getComment().hashCode());
        result = prime * result + ((getAdministratorname() == null) ? 0 : getAdministratorname().hashCode());
        result = prime * result + ((getTimespan() == null) ? 0 : getTimespan().hashCode());
        result = prime * result + ((getStopflag() == null) ? 0 : getStopflag().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}