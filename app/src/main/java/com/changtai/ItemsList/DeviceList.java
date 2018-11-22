package com.changtai.ItemsList;

import android.app.Activity;
import android.databinding.DataBindingUtil;
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
import com.changtai.application.MyApplication;
import com.changtai.databinding.ActivityWellListBinding;
import com.changtai.sqlModel.DeviceModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class DeviceList extends Activity implements View.OnClickListener{

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
    public String resultFromServer = "";
    public boolean etIsChanged = true;
    public MyApplication application;
    public DeviceModel deviceModel;
    public ActivityWellListBinding binding;
    public RealmUtils<DeviceModel> realmUtils;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_well_list);
        realmUtils = new RealmUtils<>();
        initView();
    }

    public void initView() {

        application = (MyApplication) getApplication();

        etWellList.add(binding.wellWcbNm);
        etWellList.add(binding.wellWstationNm);
        etWellList.add(binding.wellListNm);
        etWellList.add(binding.wellGprsNm);
        etWellList.add(binding.wellListName);
        etWellList.add(binding.wellIndex);
        etWellList.add(binding.wellContact);
        etWellList.add(binding.wellIphone);
        etWellList.add(binding.wellInstallLoc);
        etWellList.add(binding.wellLoginTime);
        etWellList.add(binding.wellLa);
        etWellList.add(binding.wellLo);
        etWellList.add(binding.wellNote);
        etWellList.add(binding.wellOperator);
        etWellList.add(binding.wellDataUpdate);
        etWellList.add(binding.wellStop);
        etWellList.add(binding.wellVersion);
        etMap = Entity.saveInMap(etWellList);
        RealmUtils.setEditEnable(etMap, false);
        RealmUtils.setEditWatch(etMap, textWatcher);
        adapter = new ArrayAdapter<>(this,R.layout.list_view_layout, R.id.text_list_item, getDeviceName());
        binding.wellListView.setAdapter(adapter);
        binding.wellOk.setOnClickListener(this);

        binding.wellListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deviceModel = MyApplication.getInstance().getDaoSession().getDeviceModelDao().loadAll().get(position);

                if (deviceModel != null) {
                    binding.wellScrollview.setVisibility(View.VISIBLE);
                    binding.wellListView.setVisibility(View.GONE);
                    String dRealmJson = new Gson().toJson(deviceModel);
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
                        DeviceModel deviceModel = realmUtils.createData(etMap, 0);
                        if (deviceModel != null){
                            MyApplication.getInstance().getDaoSession().getDeviceModelDao().insert(deviceModel);
                        }
                        etDeviceScroll.setVisibility(View.GONE);
                        deviceListView.setVisibility(View.VISIBLE);
                        RealmUtils.setEditEnable(etMap, false);
                        /**
                         * 更新数据接口调试（遗留）
                         */
                    } else {
                        Entity.toastMsg(DeviceList.this, "输入不能空");
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
        List<DeviceModel> deviceModelList = MyApplication.getInstance().getDaoSession().getDeviceModelDao().loadAll();
        LinkedList<String> deviceName = new LinkedList<>();
        for (DeviceModel deviceRealm : deviceModelList)
        {
            deviceName.add(deviceRealm.getDeviceName() + ":" + deviceRealm.getDeviceNo());
            Log.e("deviceName", deviceRealm.getDeviceName());
        }
        return deviceName;
    }

    @Override
    public void onBackPressed() {

        if (binding.wellScrollview.getVisibility() == View.VISIBLE)
        {
            binding.wellScrollview.setVisibility(View.GONE);
            binding.wellListView.setVisibility(View.VISIBLE);
        }
        else {
            super.onBackPressed();
        }
    }


}
