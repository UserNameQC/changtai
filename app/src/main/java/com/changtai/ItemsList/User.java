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
import com.changtai.realm.UserRealm;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.realm.RealmResults;

public class User extends Activity implements View.OnClickListener{
    /**
     * 用户表
     */
    public EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10, et11, et12, et13, et14, et15, et16, et17,et18, et19, et20, et21, et22,et23, et24, et25, et26;
    public Map<Integer, EditText> etMap = new HashMap<Integer, EditText>();
    public Button button;
    public String[] key = {Entity.BureauNo,Entity.StationNo,Entity.DeviceNo,Entity.UserNo,Entity.Index,Entity.UserName,Entity.Phone,
            Entity.CreateDateTime,Entity.Linkman,Entity.SjId,Entity.UsedTotal,
            Entity.PurchaseTotal,Entity.PurchaseTotalThisYear,Entity.Overdraft,Entity.AlarmValue,Entity.CredentialNo,Entity.LimitSj1,
            Entity.LimitSj2,Entity.Comment,Entity.AdministratorName,
            Entity.StopFlag,Entity.LastDateTime,Entity.CardNo,Entity.CreditCardTimes,Entity.TimeSpan,Entity.Version};

    public ListView useRListView;
    public ArrayAdapter<String> adapter;
    public UserRealm userRealm;
    public RealmResults<UserRealm> realmResultList;
    public ScrollView scrollView;
    public LinkedList<EditText> etLinkList = new LinkedList<>();
    public boolean etIsChangedUser = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
    }

    public void initView(){
        scrollView = (ScrollView) findViewById(R.id.user_scrollview);
        et1 = (EditText) findViewById(R.id.user_wcb_nm);
        etLinkList.add(et1);
        et2 = (EditText) findViewById(R.id.user_wstation_nm);
        etLinkList.add(et2);
        et3 = (EditText) findViewById(R.id.user_list_nm);
        etLinkList.add(et3);
        et4 = (EditText) findViewById(R.id.user_nm);
        etLinkList.add(et4);
        et5 = (EditText) findViewById(R.id.user_name);
        etLinkList.add(et5);
        et6 = (EditText) findViewById(R.id.user_index);
        etLinkList.add(et6);
        et7 = (EditText) findViewById(R.id.user_contact);
        etLinkList.add(et7);
        et8 = (EditText) findViewById(R.id.user_iphone);
        etLinkList.add(et8);
        et9 = (EditText) findViewById(R.id.user_build_time);
        etLinkList.add(et9);
        et10 = (EditText) findViewById(R.id.user_wp_price_num);
        etLinkList.add(et10);
        et11 = (EditText) findViewById(R.id.user_water_consumption);
        etLinkList.add(et11);
        et12 = (EditText) findViewById(R.id.user_purchase_water);
        etLinkList.add(et12);
        et13 = (EditText) findViewById(R.id.user_purchase_water_year);
        etLinkList.add(et13);
        et14 = (EditText) findViewById(R.id.user_overdraft_limit);
        etLinkList.add(et14);
        et15 = (EditText) findViewById(R.id.user_alarm_water);
        etLinkList.add(et15);
        et16 = (EditText) findViewById(R.id.user_idcard);
        etLinkList.add(et16);
        et17 = (EditText) findViewById(R.id.user_first_max);
        etLinkList.add(et17);
        et18 = (EditText) findViewById(R.id.user_second_max);
        etLinkList.add(et18);
        et19 = (EditText) findViewById(R.id.user_note);
        etLinkList.add(et19);
        et20 = (EditText) findViewById(R.id.user_operator);
        etLinkList.add(et20);
        et21 = (EditText) findViewById(R.id.user_last_water_time);
        etLinkList.add(et21);
        et22 = (EditText) findViewById(R.id.user_card_num);
        etLinkList.add(et22);
        et23 = (EditText) findViewById(R.id.user_issued_times);
        etLinkList.add(et23);
        et24 = (EditText) findViewById(R.id.user_data_update);
        etLinkList.add(et24);
        et25 = (EditText) findViewById(R.id.user_stop);
        etLinkList.add(et25);
        et26 = (EditText) findViewById(R.id.user_version);
        etLinkList.add(et26);
        etMap = Entity.saveInMap(etLinkList);
        RealmUtils.setEditEnable(etMap, false);
        RealmUtils.setEditWatch(etMap, textWatcher);
        button = (Button) findViewById(R.id.user_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etIsChangedUser) {
                    if (!Entity.editTextIsNull(etMap)) {
                        RealmUtils.createData(etMap, userRealm.getClass(), 0);
                        useRListView.setVisibility(View.VISIBLE);
                        scrollView.setVisibility(View.GONE);
                        RealmUtils.setEditEnable(etMap, false);
                        RealmUtils.setTimeUpdateToServer(userRealm.getClass(), "03");
                    }
                    else
                    {
                        Entity.toastMsg(User.this, "输入不能为空");
                    }
                } else {
                    button.setText("保存");
                    etIsChangedUser = true;
                    RealmUtils.setEditEnable(etMap, true);
                }
            }
        });
        useRListView = (ListView)findViewById(R.id.user_list);
        adapter = new ArrayAdapter<>(this,R.layout.list_view_layout, R.id.text_list_item, getUserNum());
        useRListView.setAdapter(adapter);
        useRListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userRealm = Entity.realm.where(UserRealm.class).findAll().get(position);
                userRealm = Entity.realm.copyFromRealm(userRealm);
                //Log.e("AdministratorName", userRealm.getAdministratorName());
                useRListView.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                //TypeAdapter<UserRealm> userTypeAdapter = gson.getAdapter(UserRealm.class);
                String json = gson.toJson(userRealm);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    for (int i = 0; i < etMap.size(); i++)
                    {
                        EditText et = etMap.get(i);
                        et.setText(jsonObject.get(et.getTag().toString()).toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.e("json", json);
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
            etIsChangedUser = false;
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_ok:
                if (!etIsChangedUser) {
                    if (!Entity.editTextIsNull(etMap)) {
                        RealmUtils.createData(etMap, userRealm.getClass(), 0);
                        useRListView.setVisibility(View.VISIBLE);
                        scrollView.setVisibility(View.GONE);
                        RealmUtils.setTimeUpdateToServer(userRealm.getClass(), "03");
                    }
                    else
                    {
                        Entity.toastMsg(User.this, "输入不能为空");
                    }
                } else {
                    button.setText("保存");
                    etIsChangedUser = true;
                    RealmUtils.setEditEnable(etMap, true);
                }
            break;
        }
    }

    public List<String> getUserNum(){
        realmResultList = Entity.realm.where(UserRealm.class).findAll();
        List<String> userNumList = new ArrayList<>();
        for (int i = 0; i < realmResultList.size(); i++)
        {
            UserRealm userRealm = realmResultList.get(i);
            userNumList.add(userRealm.getUserno());
        }
        return userNumList;
    }
}
