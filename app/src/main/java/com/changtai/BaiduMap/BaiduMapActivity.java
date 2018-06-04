package com.changtai.BaiduMap;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOfflineMap;
import com.baidu.mapapi.model.LatLng;
import com.changtai.R;
import com.changtai.Utils.Entity;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class BaiduMapActivity extends Activity {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private LocationClient locationClient ;
    private LocationClientOption locationClientOption;
    private MyLocationListener listener = new MyLocationListener();
    private Button bt_getPostion;
    private final static IntentFilter intentFilter = new IntentFilter();

    private boolean isFirstLoc = true;
    static{
        intentFilter.addAction(Entity.LATLNG_INFOMATION);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Entity.LATLNG_INFOMATION))
            {
                initPosition();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidu_map);
        mMapView = (MapView) findViewById(R.id.mMapView);
        //bt_getPostion = (Button) findViewById(R.id.bt_get_position);
//        bt_getPostion.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //locationClient.start();
//            }
//        });

        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMyLocationEnabled(true);
//        MKOfflineMap offlineMap = new MKOfflineMap();
//        ArrayList<MKOLSearchRecord> list =  offlineMap.getHotCityList();
//        MKOLSearchRecord record = new MKOLSearchRecord();
        locationClient = new LocationClient(getApplicationContext());
        locationClient.registerLocationListener(listener);

        initLociton();
        registerReceiver(receiver, intentFilter);
    }

    public void initLociton(){
        locationClientOption = new LocationClientOption();
        locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        locationClientOption.setCoorType("bd0911");
        locationClientOption.setScanSpan(1000);
        locationClientOption.setOpenGps(true);
        locationClientOption.setLocationNotify(true);
        locationClientOption.setWifiCacheTimeOut(5*60*1000);
        locationClient.setLocOption(locationClientOption);
        locationClient.start();
    }

    /**
     * 显示当前位置
     */
    public void initPosition(){
        BitmapDescriptor descriptor =  BitmapDescriptorFactory
                .fromResource(R.drawable.login_name);
        OverlayOptions position = new MarkerOptions().position(Entity.latLng).icon(descriptor);
        mBaiduMap.addOverlay(position);
    }

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
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(bdLocation.getLatitude(),
                        bdLocation.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
            initPosition();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
