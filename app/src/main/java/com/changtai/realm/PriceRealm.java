package com.changtai.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * 水价表
 * Created by qjcjo on 2018/4/1.
 */

public class PriceRealm extends RealmObject {


    @PrimaryKey
    public Long sjId;
    @Required
    public Long stationNo;
    @Required
    public String mc;
    @Required
    public String sj1;
    @Required
    public String sj2;
    @Required
    public String sj3;
    @Required
    public String administratorName;
    //服务器端数据版本
    public Long serverVersion;//新增加
    //电脑版售水软件数据版本
    public long clientVersion;//新增加

    public Long getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(Long serverVersion) {
        this.serverVersion = serverVersion;
    }

    public long getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(long clientVersion) {
        this.clientVersion = clientVersion;
    }

    public Long getStationNo() {
        return stationNo;
    }

    public void setStationNo(Long stationNo) {
        this.stationNo = stationNo;
    }

    public Long getSjId() {
        return sjId;
    }

    public void setSjId(Long sjId) {
        this.sjId = sjId;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getSj1() {
        return sj1;
    }

    public void setSj1(String sj1) {
        this.sj1 = sj1;
    }

    public String getSj2() {
        return sj2;
    }

    public void setSj2(String sj2) {
        this.sj2 = sj2;
    }

    public String getSj3() {
        return sj3;
    }

    public void setSj3(String sj3) {
        this.sj3 = sj3;
    }

    public String getAdministratorName() {
        return administratorName;
    }

    public void setAdministratorName(String administratorName) {
        this.administratorName = administratorName;
    }
}
