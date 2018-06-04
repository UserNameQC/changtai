package com.changtai.ItemsList;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;

import com.changtai.R;
import com.changtai.Utils.Entity;
import com.changtai.Utils.RealmUtils;
import com.changtai.Utils.SaveDataToFile;
import com.changtai.activites.BaseActivity;
import com.changtai.realm.DeviceRealm;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import io.realm.RealmResults;

public class WellList extends Activity implements View.OnClickListener{

    public EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10, et11, et12, et13, et14, et15, et16, et17;
    public Map<Integer, EditText> etMap = new HashMap<Integer, EditText>();
    public Button button;
    public String[] key = {Entity.BureauNo,Entity.StationNo,Entity.DeviceNo,Entity.GprsNo,Entity.DeviceName,Entity.Index,
            Entity.Linkman,Entity.Phone,Entity.Location,Entity.CreateDateTime,Entity.Longitude,
            Entity.Latitude,Entity.Comment,Entity.AdministratorName,Entity.TimeSpan,
            Entity.StopFlag,Entity.Version};

    public LinkedList<EditText> etWellList = new LinkedList<>();
    public ListView deviceListView;
    public ScrollView etDeviceScroll;
    public ArrayAdapter<String> adapter;
    public DeviceRealm deviceRealm;
    public String resultFromServer = "";
    public boolean etIsChanged = true;
    public RealmUtils<DeviceRealm> realmUtils = new RealmUtils<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_well_list);
        initView();
       // this.registerReceiver(receiver, intentFilter);
        //realmUtils.getDataFromService("04", 1, DeviceRealm.class, Entity.Well_list);
    }

    public void initView() {
        et1 = (EditText) findViewById(R.id.well_wcb_nm);
        etWellList.add(et1);
        et2 = (EditText) findViewById(R.id.well_wstation_nm);
        etWellList.add(et2);
        et3 = (EditText) findViewById(R.id.well_list_nm);
        etWellList.add(et3);
        et4 = (EditText) findViewById(R.id.well_gprs_nm);
        etWellList.add(et4);
        et5 = (EditText) findViewById(R.id.well_list_name);
        etWellList.add(et5);
        et6 = (EditText) findViewById(R.id.well_index);
        etWellList.add(et6);
        et7 = (EditText) findViewById(R.id.well_contact);
        etWellList.add(et7);
        et8 = (EditText) findViewById(R.id.well_iphone);
        etWellList.add(et8);
        et9 = (EditText) findViewById(R.id.well_install_loc);
        etWellList.add(et9);
        et10 = (EditText) findViewById(R.id.well_login_time);
        etWellList.add(et10);
        et11 = (EditText) findViewById(R.id.well_la);
        etWellList.add(et11);
        et12 = (EditText) findViewById(R.id.well_lo);
        etWellList.add(et12);
        et13 = (EditText) findViewById(R.id.well_note);
        etWellList.add(et13);
        et14 = (EditText) findViewById(R.id.well_operator);
        etWellList.add(et14);
        et15 = (EditText) findViewById(R.id.well_data_update);
        etWellList.add(et15);
        et16 = (EditText) findViewById(R.id.well_stop);
        etWellList.add(et16);
        et17 = (EditText) findViewById(R.id.well_version);
        etWellList.add(et17);
        etMap = Entity.saveInMap(etWellList);
        RealmUtils.setEditEnable(etMap, false);
        RealmUtils.setEditWatch(etMap, textWatcher);
        deviceListView = findViewById(R.id.well_list_view);
        etDeviceScroll = findViewById(R.id.well_scrollview);
        adapter = new ArrayAdapter<>(this,R.layout.list_view_layout, R.id.text_list_item, getDeviceName());
        deviceListView.setAdapter(adapter);
        button = (Button) findViewById(R.id.well_ok);
        button.setOnClickListener(this);

        deviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deviceRealm = Entity.realm.where(DeviceRealm.class).findAll().get(position);
                if (deviceRealm != null) {
                    deviceRealm = Entity.realm.copyFromRealm(deviceRealm);
                    etDeviceScroll.setVisibility(View.VISIBLE);
                    deviceListView.setVisibility(View.GONE);
                    String dRealmJson = new Gson().toJson(deviceRealm);
                    try{
                        JSONObject jsonObject = new JSONObject(dRealmJson);
                        for (int i = 0; i < etMap.size(); i++)
                        {
                            EditText et = etMap.get(i);
                            et.setText(String.valueOf(jsonObject.get(et.getTag().toString())));
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            etIsChanged = false;
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.well_ok:
                if (etIsChanged) {
                    if (!Entity.editTextIsNull(etMap)) {
                        RealmUtils.createData(etMap, deviceRealm.getClass(), 0);
                        etDeviceScroll.setVisibility(View.GONE);
                        deviceListView.setVisibility(View.VISIBLE);
                        RealmUtils.setEditEnable(etMap, false);
                        RealmUtils.setTimeUpdateToServer(deviceRealm.getClass(), "04");
                    } else {
                        Entity.toastMsg(WellList.this, "输入不能空");
                    }
                }else{
                    button.setText("保存");
                    etIsChanged = true;
                    RealmUtils.setEditEnable(etMap, true);
                }

                break;
        }
    }

    public LinkedList<String> getDeviceName(){
        RealmResults<DeviceRealm> realms = Entity.realm.where(DeviceRealm.class).findAll();
        LinkedList<String> list = new LinkedList<>();
        for (DeviceRealm deviceRealm : realms)
        {
            list.add(deviceRealm.getDeviceno() + "");
            Log.e("devicename", deviceRealm.getDevicename());
        }
        return list;
    }
}
