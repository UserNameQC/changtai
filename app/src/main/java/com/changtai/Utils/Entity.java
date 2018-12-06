package com.changtai.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.changtai.sqlModel.LoginModel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Qiao on 2018/2/6.
 */

public class Entity {

    //当前登录的用户，admin用户不保存在数据中，如果是admin用户，在用户登录时手动创建一个model赋值到这里，如果是其它用户则到数据库里查找然后赋值到这里
    public static ExecutorService executorService = Executors.newCachedThreadPool();
    public static final String mainUrl = "http://tzctdz.51mypc.cn:8081/changtai";
    //public static final String mainUrl = "http://192.168.1.101/changtai";
    public static String loginUrl;
    public static String getDataUrl;
    public static String updateUrl;
    public static String verifyPasswordUrl;
    public static String resetPasswordUrl;
    public static LatLng latLng = null;
    public static LocalBroadcastManager broadcast;
    public static SharedPreferences spres;
    public static final String ISFirst = "isFirst";
    public static boolean isFirst;
    public static String token = null;
    public static long time;
    public static String isFirstUseApp = "isFirstUseApp";

    public static String Well_list = "WELL_LIST";


    public final static String LATLNG_INFOMATION = "LATLNG_INFOMATION";


    public static void toastMsg(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void paramUrl(String url){
        loginUrl = url + "/user/login";
        getDataUrl = url + "/data/syncData";
        verifyPasswordUrl = url + "/user/vailLoginPwd";
        resetPasswordUrl = url + "/user/restLoginPwd";
        updateUrl = url + "/data/syncDataToService";
    }

    /**
     * 机井信息表
     */
    public static final  String BureauNo = "BureauNo";//水利局编号
    public static final  String StationNo = "stationNo";//水管站编号
    public static final  String DeviceNo = "DeviceNo";//机井编号
    public static final  String GprsNo = "GprsNo";//GPRS模块编号
    public static final  String DeviceName = "DeviceName";//机井名称
    public static final  String Index = "Index";//索引
    public static final  String Linkman = "Linkman";//联系人
    public static final  String Phone = "Phone";//电话
    public static final  String Location = "Location";//安装位置
    public static final  String CreateDateTime = "CreateDateTime";//登记时间
    public static final  String Longitude = "Longitude";//经度
    public static final  String Latitude = "Latitude";//纬度
    public static final  String Comment = "comment";//备注
    public static final  String AdministratorName = "administratorName";//操作员
    public static final  String TimeSpan = "TimeSpan";//数据更新标签
    public static final  String StopFlag = "StopFlag";//停用标志
    public static final  String Version = "Version";//版本

    /**
     *水价表
     */
    public static final  String SjId = "sjId";//水价序号
    public static final  String Mc = "mc";//水价类型
    public static final  String Sj1 = "sj1";//一级水价
    public static final  String Sj2 = "sj2";//二级水价
    public static final  String Sj3 = "sj3";//三级水价
    public static final String createTime = "createTime";
    public static final String updateTime = "updateTime";

    /**
     *用户表
     */
    public final static String UserNo = "UserNo";
    public static final String UserName = "UserName";
    public static final String UsedTotal = "UsedTotal";
    public static final String PurchaseTotal = "PurchaseTotal";
    public static final String PurchaseTotalThisYear = "PurchaseTotalThisYear";
    public static final String Overdraft = "Overdraft";
    public static final String AlarmValue = "AlarmValue";
    public static final String CredentialNo = "CredentialNo";
    public static final String LimitSj1 = "LimitSj1";
    public static final String LimitSj2 = "LimitSj2";
    public static final String CardNo = "CardNo";
    public final static String CreditCardTimes = "CreditCardTimes";
    public final static String LastDateTime = "LastDateTime";

    /**
     * 管理员表
     */
    public static final String AdministratorId = "AdministratorId";
    public static final String SysAdmin = "SysAdmin";
    public static final String Name = "name";
    public static final String Accounts = "Accounts";
    public static final String PassWord = "PassWord";
    public static final String ReWritePassWord = "ReWritePassWord";
    public static final String RoleId = "RoleId";

    /**
     * 购水明细表
     */
    public static final String PurchaseRecordId = "PurchaseRecordId";
    public static final String PurchaseTotalThisTime = "PurchaseTotalThisTime";
    public static final String PurchaseAmountThisTime = "PurchaseAmountThisTime";
    public static final String PurchaseDateTimeThisTime = "PurchaseDateTimeThisTime";
    public static final String PurchaseYear = "PurchaseYear";
    public static final String PriceSj1 = "PriceSj1";
    public static final String TotalSj1 = "TotalSj1";
    public static final String AmountSj1 = "AmountSj1";
    public static final String PriceSj2 = "PriceSj2";
    public static final String TotalSj2 = "TotalSj2";
    public static final String AmountSj2 = "AmountSj2";
    public static final String PriceSj3 = "PriceSj3";
    public static final String TotalSj3 = "TotalSj3";
    public static final String AmountSj3 = "AmountSj3";

    /**
     * 用户取水表
     */
    public static final String Id = "id";
    public static final String Flow = "Flow";
    public static final String PurchaseDate = "PurchaseDate";
    public static final String UsedAmount1 = "UsedAmount1";
    public static final String UsedAmount2 = "UsedAmount2";
    public static final String UsedAmount3 = "UsedAmount3";

    public static final String MID = "=";
    public static final String END = "\n";

    public static LoginModel loginModel = new LoginModel();
    public static String LOGIN_NAME = "LOGIN_NAME";
    public static String QX_STRING = "QX_STRING";


    public static boolean editIsNull(EditText editText){
        if(editText.length() > 0 && TextUtils.isEmpty(editText.getText().toString()))
        {
            return false;
        }
        return true;
    }

    public static boolean editTextIsNull(Map<Integer, EditText> map){
        for (int i = 0; i< map.size(); i++)
        {
            EditText et = map.get(i);
            if (!TextUtils.isEmpty(et.getText().toString()) && et.length() > 0)
            {
                //return false;
            }
            else
            {
                return  true;
            }
        }
        return false;
    }

    public static Map<Integer, EditText> saveInMap(LinkedList<EditText> list){
        Map<Integer, EditText> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++)
        {
            map.put(i, list.get(i));
        }
        return map;
    }
}
