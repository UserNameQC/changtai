package com.changtai.newDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by qjcjob on 2018/7/4.
 */
@Entity
public class PriceDao {
    @Id
    public int Id;
    @Property
    public Long sjId;
    @Property
    public String stationNo;
    @Property
    public String mc;
    @Property
    public String sj1;
    @Property
    public String sj2;
    @Property
    public String sj3;
    @Property
    public String administratorName;
    //服务器端数据版本
    @Property
    public Long serverVersion;//新增加
    //电脑版售水软件数据版本
    @Property
    public long clientVersion;//新增加
    public long getClientVersion() {
        return this.clientVersion;
    }
    public void setClientVersion(long clientVersion) {
        this.clientVersion = clientVersion;
    }
    public Long getServerVersion() {
        return this.serverVersion;
    }
    public void setServerVersion(Long serverVersion) {
        this.serverVersion = serverVersion;
    }
    public String getAdministratorName() {
        return this.administratorName;
    }
    public void setAdministratorName(String administratorName) {
        this.administratorName = administratorName;
    }
    public String getSj3() {
        return this.sj3;
    }
    public void setSj3(String sj3) {
        this.sj3 = sj3;
    }
    public String getSj2() {
        return this.sj2;
    }
    public void setSj2(String sj2) {
        this.sj2 = sj2;
    }
    public String getSj1() {
        return this.sj1;
    }
    public void setSj1(String sj1) {
        this.sj1 = sj1;
    }
    public String getMc() {
        return this.mc;
    }
    public void setMc(String mc) {
        this.mc = mc;
    }
    public String getStationNo() {
        return this.stationNo;
    }
    public void setStationNo(String stationNo) {
        this.stationNo = stationNo;
    }
    public Long getSjId() {
        return this.sjId;
    }
    public void setSjId(Long sjId) {
        this.sjId = sjId;
    }
    public int getId() {
        return this.Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
    @Generated(hash = 1716026631)
    public PriceDao(int Id, Long sjId, String stationNo, String mc, String sj1,
            String sj2, String sj3, String administratorName, Long serverVersion,
            long clientVersion) {
        this.Id = Id;
        this.sjId = sjId;
        this.stationNo = stationNo;
        this.mc = mc;
        this.sj1 = sj1;
        this.sj2 = sj2;
        this.sj3 = sj3;
        this.administratorName = administratorName;
        this.serverVersion = serverVersion;
        this.clientVersion = clientVersion;
    }
    @Generated(hash = 943336286)
    public PriceDao() {
    }
}
