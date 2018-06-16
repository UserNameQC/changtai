package com.changtai.SynchronizationWithPCModels;

/**
 * 水价表
 * 用于gson序列化与反序列化
 * 当数据下载到PDA时，把json数据反序列化到该实体，通过程序映射到数据库实体再保存到PDA
 * 当数据上传时到PC时,把数据库实体通过程序映射为该实体,然后序列化为json数据,再上传到PC
 */
public class SwpPriceModel {

    public String AreaNo;//新增加
    public Long SjId;
    public String Mc;
    public String Sj1;
    public String Sj2;
    public String Sj3;
    public Long ServerVersion;//新增加
    public long ClientVersion;//新增加
    public String AdministratorName;

//    public Long Id;
//    public Long StationNo;
//    public String BureauNo;
//    public String TimeSpan;
//    public String Version;
}
