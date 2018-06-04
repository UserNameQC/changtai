package com.changtai.BaiduMap;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.util.Xml;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.model.LatLng;
import com.changtai.Utils.Entity;

/**
 * Created by qjcjo on 2018/3/10.
 */

public class MyLocationListener extends BDAbstractLocationListener {
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
        //以下只列举部分获取经纬度相关（常用）的结果信息
        //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

        double latitude = bdLocation.getLatitude();    //获取纬度信息
        double longitude = bdLocation.getLongitude();    //获取经度信息
        float radius = bdLocation.getRadius();    //获取定位精度，默认值为0.0f
        Entity.latLng = new LatLng(latitude, longitude);
        String coorType = bdLocation.getCoorType();
        //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准

        int errorCode = bdLocation.getLocType();
        Log.e("BaiduLocation", latitude + " ; " + longitude);
        Entity.broadcast.sendBroadcast(new Intent(Entity.LATLNG_INFOMATION));
    }
}
