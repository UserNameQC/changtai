package com.changtai.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Qiao on 2018/1/28.
 */

@Entity
public class Price {
    @Id
    public Long Id;
    @Property
    @NotNull
    public Long StationNo;
    @Property
    public Long SjId;
    @Property
    public String BureauNo;
    @Property
    public String TimeSpan;
    @Property
    public String Version;
    @Property
    public String Mc;
    @Property
    public String Sj1;
    @Property
    public String Sj2;
    @Property
    public String Sj3;
    @Property
    public String AdministratorName;
    public String getAdministratorName() {
        return this.AdministratorName;
    }
    public void setAdministratorName(String AdministratorName) {
        this.AdministratorName = AdministratorName;
    }
    public String getSj3() {
        return this.Sj3;
    }
    public void setSj3(String Sj3) {
        this.Sj3 = Sj3;
    }
    public String getSj2() {
        return this.Sj2;
    }
    public void setSj2(String Sj2) {
        this.Sj2 = Sj2;
    }
    public String getSj1() {
        return this.Sj1;
    }
    public void setSj1(String Sj1) {
        this.Sj1 = Sj1;
    }
    public String getMc() {
        return this.Mc;
    }
    public void setMc(String Mc) {
        this.Mc = Mc;
    }
    public String getVersion() {
        return this.Version;
    }
    public void setVersion(String Version) {
        this.Version = Version;
    }
    public String getTimeSpan() {
        return this.TimeSpan;
    }
    public void setTimeSpan(String TimeSpan) {
        this.TimeSpan = TimeSpan;
    }
    public String getBureauNo() {
        return this.BureauNo;
    }
    public void setBureauNo(String BureauNo) {
        this.BureauNo = BureauNo;
    }
    public Long getSjId() {
        return this.SjId;
    }
    public void setSjId(Long SjId) {
        this.SjId = SjId;
    }
    public Long getStationNo() {
        return this.StationNo;
    }
    public void setStationNo(Long StationNo) {
        this.StationNo = StationNo;
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    @Generated(hash = 1883445052)
    public Price(Long Id, @NotNull Long StationNo, Long SjId, String BureauNo,
            String TimeSpan, String Version, String Mc, String Sj1, String Sj2,
            String Sj3, String AdministratorName) {
        this.Id = Id;
        this.StationNo = StationNo;
        this.SjId = SjId;
        this.BureauNo = BureauNo;
        this.TimeSpan = TimeSpan;
        this.Version = Version;
        this.Mc = Mc;
        this.Sj1 = Sj1;
        this.Sj2 = Sj2;
        this.Sj3 = Sj3;
        this.AdministratorName = AdministratorName;
    }
    @Generated(hash = 812905808)
    public Price() {
    }
}
