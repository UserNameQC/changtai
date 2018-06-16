package com.changtai.SynchronizationWithPCModels;

import java.util.Date;

/**
 * 机井表
 * 用于gson序列化与反序列化
 * 当数据下载到PDA时，把json数据反序列化到该实体，通过程序映射到数据库实体再保存到PDA
 * 当数据上传时到PC时,把数据库实体通过程序映射为该实体,然后序列化为json数据,再上传到PC
 */
public class SwpDeviceModel {
    public String AreaNo;//新增加
    public String DeviceNo;
    public String GprsNo;
    public String DeviceName;
    public String Linkman;
    public String Phone;
    public int Index;
    public String Location;
    //售水断为Datetime类型
    public Date CreateDateTime;
    //售水断为decimal类型
    public String Longitude;
    //售水断为decimal类型
    public String Latitude;
    public String Comment;
    public String AdministratorName;
    public boolean StopFlag;
    public Long ServerVersion;//新增加
    public long ClientVersion;//新增加
    public boolean DelFlag;

//    public Long Id;
//    public Long BureauNo;
//    public Long StationNo;
//    public String TimeSpan;
//    public long Version;
}
