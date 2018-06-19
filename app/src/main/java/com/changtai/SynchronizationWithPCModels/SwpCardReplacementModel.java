package com.changtai.SynchronizationWithPCModels;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 补卡记录表
 * 用于gson序列化与反序列化
 * 当数据下载到PDA时，把json数据反序列化到该实体，通过程序映射到数据库实体再保存到PDA
 * 当数据上传时到PC时,把数据库实体通过程序映射为该实体,然后序列化为json数据,再上传到PC
 */
public class SwpCardReplacementModel {
    //表示字段,相关表的主键,新建该实体时该属性的位为UUID
    public String CardReplacementId;
    //售水站号
    public String StationNo;
    //机井号
    public String DeviceNo;
    //联系人
    public String Linkman;
    //联系方式
    public String Phone;
    //安装位置
    public String Location;
    //操作员
    public String AdministratorName;
    //服务器端数据版本
    public Long ServerVersion;//新增加
    //电脑版售水软件数据版本
    public long ClientVersion;//新增加
    //用户名
    public String UserName;
    //用户号
    public String UserNo;
    //累计用水量
    public BigDecimal UsedTotal;
    //累计购水量
    public BigDecimal PurchaseTotal;
    //补卡时间
    public Date CreateDateTime;
    //剩余水量=累计购水量-累计用水量
    public BigDecimal LastTotal;
    //上一次购水日期;补卡前最后购水时间
    public Date LastDateTime;
}
