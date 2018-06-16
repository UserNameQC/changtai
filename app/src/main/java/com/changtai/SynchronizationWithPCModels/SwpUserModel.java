package com.changtai.SynchronizationWithPCModels;

import android.provider.ContactsContract;

import java.util.Date;

/**
 * 购水用户表
 * 用于gson序列化与反序列化
 * 当数据下载到PDA时，把json数据反序列化到该实体，通过程序映射到数据库实体再保存到PDA
 * 当数据上传时到PC时,把数据库实体通过程序映射为该实体,然后序列化为json数据,再上传到PC
 */
public class SwpUserModel {
    public String AreaNo;//新增加
    public String DeviceNo;
    public String UserNo;
    public String UserName;
    public String Linkman;
    public String Phone;
    public Date CreateDateTime;
    public Integer SjId;
    public String UsedTotal;//?
    public String PurchaseTotal;
    public String PurchaseTotalThisYear;
    public String Overdraft;
    public String AlarmValue;
    public String CredentialNo;
    public String LimitSj1;
    public String LimitSj2;
    public String Comment;
    public String AdministratorName;
    public boolean Stopflag;
    public Date LastDateTime;
    public Integer Index;
    public String CardNo;
    public Integer CreditCardTimes;
    public long ServerVersion;
    public long ClientVersion;
    public boolean DelFlag;//新增加

//    public String bureauno;
//    public String stationno;
//    public String timespan;
//    public String version;
//    public int key;
}
