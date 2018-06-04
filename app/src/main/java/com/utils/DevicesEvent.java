package com.utils;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.changtai.realm.DeviceRealm;
import com.google.gson.Gson;
/**
 * Created by xuzhen on 2018/3/8.
 */

public class DevicesEvent extends  BaseDbCURDEvent<DeviceRealm> {


//    public DevicesEvent(WebView mWebViewc) {
//        super(mWebViewc);
//    }

    @Override
    public void setCRUD_TABLE() {
        setCRUD_TABLE("JJB",Device.class);
    }

}
