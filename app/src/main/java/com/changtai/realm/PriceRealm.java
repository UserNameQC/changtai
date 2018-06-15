package com.changtai.realm;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * 水价表
 * Created by qjcjo on 2018/4/1.
 */

public class PriceRealm extends RealmObject {

    @PrimaryKey
    public Long Id;
    @Required
    public Long StationNo;
    @Required
    public Long SjId;
    @Required
    public String BureauNo;
    @Required
    public String TimeSpan;
    @Required
    public String Version;
    @Required
    public String Mc;
    @Required
    public String Sj1;
    @Required
    public String Sj2;
    @Required
    public String Sj3;
    @Required
    public String AdministratorName;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getStationNo() {
        return StationNo;
    }

    public void setStationNo(Long stationNo) {
        StationNo = stationNo;
    }

    public Long getSjId() {
        return SjId;
    }

    public void setSjId(Long sjId) {
        SjId = sjId;
    }

    public String getBureauNo() {
        return BureauNo;
    }

    public void setBureauNo(String bureauNo) {
        BureauNo = bureauNo;
    }

    public String getTimeSpan() {
        return TimeSpan;
    }

    public void setTimeSpan(String timeSpan) {
        TimeSpan = timeSpan;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getMc() {
        return Mc;
    }

    public void setMc(String mc) {
        Mc = mc;
    }

    public String getSj1() {
        return Sj1;
    }

    public void setSj1(String sj1) {
        Sj1 = sj1;
    }

    public String getSj2() {
        return Sj2;
    }

    public void setSj2(String sj2) {
        Sj2 = sj2;
    }

    public String getSj3() {
        return Sj3;
    }

    public void setSj3(String sj3) {
        Sj3 = sj3;
    }

    public String getAdministratorName() {
        return AdministratorName;
    }

    public void setAdministratorName(String administratorName) {
        AdministratorName = administratorName;
    }
}
