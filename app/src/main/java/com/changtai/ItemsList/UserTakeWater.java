package com.changtai.ItemsList;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.changtai.activites.BaseActivity;
import com.changtai.realm.WaterBeUsedDateRealm;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.realm.RealmResults;

public class UserTakeWater extends Activity implements View.OnClickListener{

    public EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10, et11, et12;
    public Map<Integer, EditText> etMap = new HashMap<Integer, EditText>();
    public String[] key = {Entity.Id,Entity.BureauNo,Entity.StationNo,Entity.DeviceNo,Entity.UserNo,
            Entity.UsedAmount1,Entity.UsedAmount2,
            Entity.UsedAmount3,Entity.TimeSpan,Entity.Location};
    public Button button;
    public LinkedList<EditText> linkedList = new LinkedList<>();
    public LinkedList<String> nameList = new LinkedList<>();
    public ListView etListView;
    public ArrayAdapter adapter;
    public ScrollView waterScroll;
    public String resultFromServer;
    public WaterBeUsedDateRealm waterBeUsedDateRealm;
    public boolean etIsChangedTake = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_take_water);
        initView();
    }

    public void initView(){

        et1 = (EditText) findViewById(R.id.utw_logo);
        linkedList.add(et1);
        et2 = (EditText) findViewById(R.id.utw_wcb_nm);
        linkedList.add(et2);
        et3 = (EditText) findViewById(R.id.utw_wstation_nm);
        linkedList.add(et3);
        et4 = (EditText) findViewById(R.id.utw_list_nm);
        linkedList.add(et4);
        et5 = (EditText) findViewById(R.id.utw_user_num);
        linkedList.add(et5);
        et6 = (EditText) findViewById(R.id.utw_start);
        linkedList.add(et6);
        et7 = (EditText) findViewById(R.id.utw_ends);
        linkedList.add(et7);
        et8 = (EditText) findViewById(R.id.utw_and);
        linkedList.add(et8);
        et9 = (EditText) findViewById(R.id.utw_business_labels);
        linkedList.add(et9);
        et10 = (EditText) findViewById(R.id.utw_install_loc);
        linkedList.add(et10);
        et11 = findViewById(R.id.utw_install_time);
        linkedList.add(et11);
        et12 = findViewById(R.id.utw_create_time);
        linkedList.add(et12);
        etMap = Entity.saveInMap(linkedList);
        RealmUtils.setEditEnable(etMap, false);
        RealmUtils.setEditWatch(etMap, textWatcher);
        button = (Button) findViewById(R.id.utw_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etIsChangedTake) {
                    if (!Entity.editTextIsNull(etMap)) {
                        RealmUtils.createData(etMap, waterBeUsedDateRealm.getClass(), 0);
                        etListView.setVisibility(View.VISIBLE);
                        waterScroll.setVisibility(View.GONE);
                        RealmUtils.setEditEnable(etMap, false);
                        RealmUtils.setTimeUpdateToServer(waterBeUsedDateRealm.getClass(), "02");
                    } else {
                        Entity.toastMsg(UserTakeWater.this, "输入不能空");
                    }
                } else {
                    button.setText("保存");
                    etIsChangedTake = true;
                    RealmUtils.setEditEnable(etMap, true);
                }
            }
        });

        etListView = findViewById(R.id.take_water_listview);
        waterScroll = findViewById(R.id.take_water_scroll);
        adapter = new ArrayAdapter(this, R.layout.list_view_layout, R.id.text_list_item, getDeviceNum());
        etListView.setAdapter(adapter);
        etListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                etListView.setVisibility(View.GONE);
                waterScroll.setVisibility(View.VISIBLE);
                waterBeUsedDateRealm = Entity.realm.where(WaterBeUsedDateRealm.class).findAll().get(position);
                if (waterBeUsedDateRealm != null)
                {
                    waterBeUsedDateRealm = Entity.realm.copyFromRealm(waterBeUsedDateRealm);
                    String json = new Gson().toJson(waterBeUsedDateRealm);
                    try{
                        JSONObject jsonObject = new JSONObject(json);
                        for (int i = 0; i < etMap.size(); i++)
                        {
                            EditText et = etMap.get(i);
                            et.setText(jsonObject.get(et.getTag().toString()).toString());
                        }
                    }catch (JSONException e)
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
            etIsChangedTake = false;
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.utw_ok:
            if (!etIsChangedTake) {
                if (!Entity.editTextIsNull(etMap)) {
                    RealmUtils.createData(etMap, waterBeUsedDateRealm.getClass(), 0);
                    etListView.setVisibility(View.VISIBLE);
                    waterScroll.setVisibility(View.GONE);
                    RealmUtils.setTimeUpdateToServer(waterBeUsedDateRealm.getClass(), "02");
                } else {
                    Entity.toastMsg(this, "输入不能空");
                }
            } else {
                button.setText("保存");
                RealmUtils.setEditEnable(etMap, true);
            }
            break;
        }
    }

    public LinkedList<String> getDeviceNum(){
        RealmResults<WaterBeUsedDateRealm> realmList = Entity.realm.where(WaterBeUsedDateRealm.class).findAll();
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < realmList.size(); i++)
        {
            list.add(realmList.get(i).getId());
        }
        return list;
    }

    @Override
    public void onBackPressed() {

        if (waterScroll.getVisibility() == View.VISIBLE)
        {
            waterScroll.setVisibility(View.GONE);
            etListView.setVisibility(View.VISIBLE);
        }
        else {
            super.onBackPressed();
        }
    }
}
