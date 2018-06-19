package com.changtai.SynchronizationWithPCModels;

import android.provider.ContactsContract;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 购水用户表(已经确定)
 * 用于gson序列化与反序列化
 * 当数据下载到PDA时，把json数据反序列化到该实体，通过程序映射到数据库实体再保存到PDA
 * 当数据上传时到PC时,把数据库实体通过程序映射为该实体,然后序列化为json数据,再上传到PC
 */
public class SwpUserModel {
    //售水站号
    public String StationNo;
    //机井号
    public String DeviceNo;
    //用户号,改字段可以做为主键
    public String UserNo;
    //用户名称
    public String UserName;
    //联系人
    public String Linkman;
    //联系方式
    public String Phone;
    //登记时间
    public Date CreateDateTime;
    //某售水站内的水价序号，设置多个售水站号时，该字段值可能重复,一个PDA只有一个售水站号,该编号不可能重复,
    public Integer SjId;
    //累计用水量
    public BigDecimal UsedTotal;
    //累计购水量
    public BigDecimal PurchaseTotal;
    //年度累计购水量
    public BigDecimal PurchaseTotalThisYear;
    //透支限量
    public BigDecimal Overdraft;
    //报警水量
    public BigDecimal AlarmValue;
    //证件号
    public String CredentialNo;
    //一级水价上限
    public BigDecimal LimitSj1;
    //二级水价上限
    public BigDecimal LimitSj2;
    //备注
    public String Comment;
    //操作员
    public String AdministratorName;
    //停用标志
    public boolean Stopflag;
    //上一次购水日期
    public Date LastDateTime;
    //同一个机井下的用户所引
    public Integer Index;
    //卡号
    public String CardNo;
    //发卡次数
    public Integer CreditCardTimes;
    //服务器端数据版本
    public long ServerVersion;
    //电脑版售水软件数据版本
    public long ClientVersion;
    //删除标志
    public boolean DelFlag;//新增加
//    public String bureauno;
//    public String timespan;
//    public String version;
//    public int key;
}
