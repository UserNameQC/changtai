package com.changtai.realm;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * 售水记录表
 * Created by qjcjo on 2018/4/1.
 */

public class PurchaseRecordRealm extends RealmObject {

    @PrimaryKey
    public String purchaserecordid;
    @Required
    public String bureauno;
    @Required
    public String deviceno;
    @Required
    public String stationno;
    @Required
    public String username;
    @Required
    public String userno;
    @Required
    public String purchasetotalthistime;
    @Required
    public String purchaseamountthistime;
    @Required
    public String purchasedatetimethistime;
    @Required
    public String purchaseyear;
    @Required
    public String purchasetotalthisyear;
    @Required
    public String purchasetotal;
    @Required
    public String pricesj1;
    @Required
    public String totalsj1;
    @Required
    public String amountsj1;
    @Required
    public String pricesj2;
    @Required
    public String totalsj2;
    @Required
    public String amountsj2;
    @Required
    public String pricesj3;
    @Required
    public String totalsj3;
    @Required
    public String amountsj3;
    @Required
    public String administratorname;
    @Required
    public String timespan;
    @Required
    public String version;
    public long serverversion;
    public long clientversion;

    public int key;

    public long getServerversion() {
        return serverversion;
    }

    public void setServerversion(long serverversion) {
        this.serverversion = serverversion;
    }

    public long getClientversion() {
        return clientversion;
    }

    public void setClientversion(long clientversion) {
        this.clientversion = clientversion;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getPurchaserecordid() {
        return purchaserecordid;
    }

    public void setPurchaserecordid(String purchaserecordid) {
        this.purchaserecordid = purchaserecordid;
    }

    public String getBureauno() {
        return bureauno;
    }

    public void setBureauno(String bureauno) {
        this.bureauno = bureauno;
    }

    public String getDeviceno() {
        return deviceno;
    }

    public void setDeviceno(String deviceno) {
        this.deviceno = deviceno;
    }

    public String getStationno() {
        return stationno;
    }

    public void setStationno(String stationno) {
        this.stationno = stationno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno;
    }

    public String getPurchasetotalthistime() {
        return purchasetotalthistime;
    }

    public void setPurchasetotalthistime(String purchasetotalthistime) {
        this.purchasetotalthistime = purchasetotalthistime;
    }

    public String getPurchaseamountthistime() {
        return purchaseamountthistime;
    }

    public void setPurchaseamountthistime(String purchaseamountthistime) {
        this.purchaseamountthistime = purchaseamountthistime;
    }

    public String getPurchasedatetimethistime() {
        return purchasedatetimethistime;
    }

    public void setPurchasedatetimethistime(String purchasedatetimethistime) {
        this.purchasedatetimethistime = purchasedatetimethistime;
    }

    public String getPurchaseyear() {
        return purchaseyear;
    }

    public void setPurchaseyear(String purchaseyear) {
        this.purchaseyear = purchaseyear;
    }

    public String getPurchasetotalthisyear() {
        return purchasetotalthisyear;
    }

    public void setPurchasetotalthisyear(String purchasetotalthisyear) {
        this.purchasetotalthisyear = purchasetotalthisyear;
    }

    public String getPurchasetotal() {
        return purchasetotal;
    }

    public void setPurchasetotal(String purchasetotal) {
        this.purchasetotal = purchasetotal;
    }

    public String getPricesj1() {
        return pricesj1;
    }

    public void setPricesj1(String pricesj1) {
        this.pricesj1 = pricesj1;
    }

    public String getTotalsj1() {
        return totalsj1;
    }

    public void setTotalsj1(String totalsj1) {
        this.totalsj1 = totalsj1;
    }

    public String getAmountsj1() {
        return amountsj1;
    }

    public void setAmountsj1(String amountsj1) {
        this.amountsj1 = amountsj1;
    }

    public String getPricesj2() {
        return pricesj2;
    }

    public void setPricesj2(String pricesj2) {
        this.pricesj2 = pricesj2;
    }

    public String getTotalsj2() {
        return totalsj2;
    }

    public void setTotalsj2(String totalsj2) {
        this.totalsj2 = totalsj2;
    }

    public String getAmountsj2() {
        return amountsj2;
    }

    public void setAmountsj2(String amountsj2) {
        this.amountsj2 = amountsj2;
    }

    public String getPricesj3() {
        return pricesj3;
    }

    public void setPricesj3(String pricesj3) {
        this.pricesj3 = pricesj3;
    }

    public String getTotalsj3() {
        return totalsj3;
    }

    public void setTotalsj3(String totalsj3) {
        this.totalsj3 = totalsj3;
    }

    public String getAmountsj3() {
        return amountsj3;
    }

    public void setAmountsj3(String amountsj3) {
        this.amountsj3 = amountsj3;
    }

    public String getAdministratorname() {
        return administratorname;
    }

    public void setAdministratorname(String administratorname) {
        this.administratorname = administratorname;
    }

    public String getTimespan() {
        return timespan;
    }

    public void setTimespan(String timespan) {
        this.timespan = timespan;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
