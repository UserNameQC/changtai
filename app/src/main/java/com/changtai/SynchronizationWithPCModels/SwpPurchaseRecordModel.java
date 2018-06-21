package com.changtai.SynchronizationWithPCModels;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 购水记录表(已经确定)
 * 用于gson序列化与反序列化
 * 当数据下载到PDA时，把json数据反序列化到该实体，通过程序映射到数据库实体再保存到PDA
 * 当数据上传时到PC时,把数据库实体通过程序映射为该实体,然后序列化为json数据,再上传到PC
 */
public class SwpPurchaseRecordModel {
    //表示字段,相关表的主键,新建该实体时该属性的位为UUID
    public String PurchaseRecordId;
    //售水站号
    public String StationNo;
    //机井号
    public String DeviceNo;
    //用户名
    public String UserName;
    //用户号
    public String UserNo;
    //本次购水量
    public BigDecimal PurchaseTotalThisTime;
    //本次购水金额
    public BigDecimal PurchaseAmountThisTime;
    //本次购水时间
    public Date PurchaseDateTimeThisTime;
    //购水年份
    public Integer PurchaseYear;
    //当年购水量
    public BigDecimal PurchaseTotalThisYear;
    //累计购水量
    public BigDecimal PurchaseTotal;
    //一级水价
    public BigDecimal PriceSj1;
    //一级水量
    public BigDecimal TotalSj1;
    //一级水费
    public BigDecimal AmountSj1;
    //二级水价
    public BigDecimal PriceSj2;
    //二级水量
    public BigDecimal TotalSj2;
    //二级水费
    public BigDecimal AmountSj2;
    //三级水价
    public BigDecimal PriceSj3;
    //三级水量
    public BigDecimal TotalSj3;
    //三级水费
    public BigDecimal AmountSj3;
    //备注
    public String Comment;
    //操作员
    public String AdministratorName;
    //服务器端数据版本
    public long ServerVersion;
    //电脑版售水软件数据版本
    public long ClientVersion;

//    public String TimeSpan;
//    public String Version;
//    public Long id;
//    public String BureauNo;
}
