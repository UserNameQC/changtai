package com.changtai.SynchronizationWithPCModels;

import java.math.BigDecimal;

/**
 * 水价表(确定完毕)
 * 用于gson序列化与反序列化
 * 当数据下载到PDA时，把json数据反序列化到该实体，通过程序映射到数据库实体再保存到PDA
 * 当数据上传时到PC时,把数据库实体通过程序映射为该实体,然后序列化为json数据,再上传到PC
 */
public class SwpPriceModel {
    //售水站号
    public String StationNo;
    //某售水站内的水价序号，设置多个售水站号时，该字段值可能重复,一个PDA只有一个售水站号,该编号不可能重复,该字段在PDA中可以做为站键
    public Integer SjId;
    //水价类型名称
    public String Mc;
    //一级水价
    public BigDecimal Sj1;
    //二级水价
    public BigDecimal Sj2;
    //三级水价
    public BigDecimal Sj3;
    //服务器端数据版本
    public Long ServerVersion;//新增加
    //电脑版售水软件数据版本
    public long ClientVersion;//新增加
    //操作员
    public String AdministratorName;

//    public Long Id;
//    public String BureauNo;
//    public String TimeSpan;
//    public String Version;
}
