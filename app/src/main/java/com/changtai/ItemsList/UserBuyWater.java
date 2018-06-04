package com.changtai.ItemsList;

import android.app.Activity;
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
import com.changtai.activites.BaseActivity;
import com.changtai.realm.PurchaseRecordRealm;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.realm.RealmResults;

public class UserBuyWater extends Activity implements View.OnClickListener{

    /**
     * 用户购水明细表
     */
    private Button button;
    public EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10, et11, et12, et13, et14, et15, et16, et17,et18, et19, et20, et21, et22,et23, et24, et25, et26;
    public Map<Integer, EditText> etMap = new HashMap<Integer, EditText>();
    public String[]key = {Entity.PurchaseRecordId,Entity.BureauNo,Entity.DeviceNo,Entity.StationNo,Entity.UserName,
            Entity.UserNo,Entity.PurchaseTotalThisTime,Entity.PurchaseAmountThisTime,Entity.PurchaseDateTimeThisTime,
            Entity.PurchaseYear,Entity.PurchaseTotalThisYear,Entity.PurchaseTotal,Entity.PriceSj1,Entity.TotalSj1,
            Entity.AmountSj1,Entity.PriceSj2,Entity.TotalSj2,Entity.AmountSj2,Entity.PriceSj3,Entity.TotalSj3,Entity.AmountSj3,
            Entity.Comment,Entity.AdministratorName,Entity.TimeSpan,Entity.Version};

    public LinkedList<EditText> linkedList = new LinkedList<>();
    public ArrayAdapter adapter;
    public ListView etListView;
    public ScrollView etScroll;
    public String resultFromServer;
    public PurchaseRecordRealm purchaseRecordRealm;
    public boolean etIsChangedBuy = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_buy_water);
        initView();
    }

    public void initView(){

        et1 = (EditText) findViewById(R.id.bw_details_index);
        linkedList.add(et1);
        et2 = (EditText) findViewById(R.id.bw_wcb_nm);
        linkedList.add(et2);
        et3 = (EditText) findViewById(R.id.bw_wstation_nm);
        linkedList.add(et3);
        et4 = (EditText) findViewById(R.id.bw_list_nm);
        linkedList.add(et4);
        et5 = (EditText) findViewById(R.id.bw_nm);
        linkedList.add(et5);
        et6 = (EditText) findViewById(R.id.bw_name);
        linkedList.add(et6);
        et7 = (EditText) findViewById(R.id.bw_this_buy_water);
        linkedList.add(et7);
        et8 = (EditText) findViewById(R.id.bw_buy_water_amount);
        linkedList.add(et8);
        et9 = (EditText) findViewById(R.id.bw_time);
        linkedList.add(et9);
        et10 = (EditText) findViewById(R.id.bw_year);
        linkedList.add(et10);
        et11 = (EditText) findViewById(R.id.bw_tyear_num);
        linkedList.add(et11);
        et12 = (EditText) findViewById(R.id.bw_purchase_water);
        linkedList.add(et12);
        et13 = (EditText) findViewById(R.id.bw_first_wprice);
        linkedList.add(et13);
        et14 = (EditText) findViewById(R.id.bw_first_water_num);
        linkedList.add(et14);
        et15 = (EditText) findViewById(R.id.bw_first_water_fee);
        linkedList.add(et15);
        et16 = (EditText) findViewById(R.id.bw_second_wprice);
        linkedList.add(et16);
        et17 = (EditText) findViewById(R.id.bw_second_water_num);
        linkedList.add(et17);
        et18 = (EditText) findViewById(R.id.bw_second_water_fee);
        linkedList.add(et18);
        et19 = (EditText) findViewById(R.id.bw_third_wprice);
        linkedList.add(et19);
        et20 = (EditText) findViewById(R.id.bw_third_water_num);
        linkedList.add(et20);
        et21 = (EditText) findViewById(R.id.bw_third_water_fee);
        linkedList.add(et21);
        et22 = (EditText) findViewById(R.id.bw_note);
        linkedList.add(et22);
        et23 = (EditText) findViewById(R.id.bw_operator);
        linkedList.add(et23);
        et24 = (EditText) findViewById(R.id.bw_data_update);
        linkedList.add(et24);
        et25 = (EditText) findViewById(R.id.bw_version);
        linkedList.add(et25);
        etMap = Entity.saveInMap(linkedList);
        RealmUtils.setEditEnable(etMap, false);
        RealmUtils.setEditWatch(etMap, textWatcher);
        button = (Button) findViewById(R.id.bw_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etIsChangedBuy) {
                    if (!Entity.editTextIsNull(etMap)) {
                        RealmUtils.createData(etMap, purchaseRecordRealm.getClass(), 0);
                        etListView.setVisibility(View.VISIBLE);
                        etScroll.setVisibility(View.GONE);
                        RealmUtils.setTimeUpdateToServer(purchaseRecordRealm.getClass(), "01");
                    } else {
                        Entity.toastMsg(UserBuyWater.this, "输入不能空");
                    }
                } else {
                    button.setText("保存");
                    etIsChangedBuy = true;
                    RealmUtils.setEditEnable(etMap, true);
                }
            }
        });

        etListView = findViewById(R.id.buy_listview);
        etScroll = findViewById(R.id.buy_scroll);
        adapter = new ArrayAdapter(this, R.layout.list_view_layout, R.id.text_list_item, getUserNum());
        etListView.setAdapter(adapter);
        etListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                etListView.setVisibility(View.GONE);
                etScroll.setVisibility(View.VISIBLE);
                purchaseRecordRealm = Entity.realm.where(PurchaseRecordRealm.class).findAll().get(position);
                if (purchaseRecordRealm != null)
                {
                    purchaseRecordRealm = Entity.realm.copyFromRealm(purchaseRecordRealm);
                    String json = new Gson().toJson(purchaseRecordRealm);
                    try{
                        JSONObject object = new JSONObject(json);
                        for (int i = 0; i < etMap.size(); i++)
                        {
                            EditText et = etMap.get(i);
                            et.setText(String.valueOf(object.get(et.getTag().toString())));
                        }
                    }catch (JSONException e){e.printStackTrace();}
                }
            }
        });
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
            etIsChangedBuy = false;
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bw_ok:
            if (!etIsChangedBuy) {
                if (!Entity.editTextIsNull(etMap)) {
                    RealmUtils.createData(etMap, purchaseRecordRealm.getClass(), 0);
                    etListView.setVisibility(View.VISIBLE);
                    etScroll.setVisibility(View.GONE);
                    RealmUtils.setEditEnable(etMap, false);
                    RealmUtils.setTimeUpdateToServer(purchaseRecordRealm.getClass(), "01");
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

    public List<String> getUserNum(){

        RealmResults<PurchaseRecordRealm> realms = Entity.realm.where(PurchaseRecordRealm.class).findAll();
        LinkedList<String> list = new LinkedList<>();
        for (int i =0; i < realms.size(); i++)
        {
            list.add(realms.get(i).getPurchaserecordid());
        }
        return list;
    }
}
