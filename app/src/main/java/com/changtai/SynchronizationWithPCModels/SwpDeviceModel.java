package com.changtai.SynchronizationWithPCModels;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 机井表(确定完毕)
 * 用于gson序列化与反序列化
 * 当数据下载到PDA时，把json数据反序列化到该实体，通过程序映射到数据库实体再保存到PDA
 * 当数据上传时到PC时,把数据库实体通过程序映射为该实体,然后序列化为json数据,再上传到PC
 */
public class SwpDeviceModel {
    //机井编号,该字段可以做为主键
    public String DeviceNo;
    //售水站号
    public String StationNo;
    //GPRS模块编号
    public String GprsNo;
    //机井名称,不维护,没用
    public String DeviceName;
    //联系人
    public String Linkman;
    //电话
    public String Phone;
    //本地区机井索引（1=>999）
    public int Index;
    //安装位置
    public String Location;
    //登记时间
    public Date CreateDateTime;
    //经度
    public String Longitude;
    //纬度
    public String Latitude;
    //备注
    public String Comment;
    //操作员
    public String AdministratorName;
    //停用标志
    public boolean StopFlag;
    //服务器端数据版本
    public Long ServerVersion;//新增加
    //电脑版售水软件数据版本
    public long ClientVersion;//新增加
    //删除标志
    public boolean DelFlag;

//    public Long id;
//    public Long BureauNo;
//    public String TimeSpan;
//    public long Version;
}
