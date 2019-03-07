package com.changtai.sqlModel;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by qjcjob on 2018/7/4.
 */
@Entity
public class PriceModel {
    @Id
    public Long Id;
    @Property
    public Integer SjId;
    @Property
    public String StationNo;
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
    //服务器端数据版本
    @Property
    public Long ServerVersion;//新增加
    //电脑版售水软件数据版本
    @Property
    public long ClientVersion;//新增加

    @Generated(hash = 1688317656)
    public PriceModel(Long Id, Integer SjId, String StationNo, String Mc,
            String Sj1, String Sj2, String Sj3, String AdministratorName,
            Long ServerVersion, long ClientVersion) {
        this.Id = Id;
        this.SjId = SjId;
        this.StationNo = StationNo;
        this.Mc = Mc;
        this.Sj1 = Sj1;
        this.Sj2 = Sj2;
        this.Sj3 = Sj3;
        this.AdministratorName = AdministratorName;
        this.ServerVersion = ServerVersion;
        this.ClientVersion = ClientVersion;
    }

    @Generated(hash = 1120898458)
    public PriceModel() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Integer getSjId() {
        return SjId;
    }

    public void setSjId(Integer sjId) {
        SjId = sjId;
    }

    public String getStationNo() {
        return StationNo;
    }

    public void setStationNo(String stationNo) {
        StationNo = stationNo;
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

    public Long getServerVersion() {
        return ServerVersion;
    }

    public void setServerVersion(Long serverVersion) {
        ServerVersion = serverVersion;
    }

    public long getClientVersion() {
        return ClientVersion;
    }

    public void setClientVersion(long clientVersion) {
        ClientVersion = clientVersion;
    }
}
