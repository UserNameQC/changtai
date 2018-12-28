package com.changtai.ItemsList;

import android.app.Activity;
import android.content.Intent;
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

import com.changtai.R;
import com.changtai.Utils.Entity;
import com.changtai.Utils.RealmUtils;
import com.changtai.adapter.UserAdapter;
import com.changtai.application.MyApplication;
import com.changtai.databinding.ActivityUserBinding;
import com.changtai.sqlModel.UserModel;
import com.example.john.greendaodemo.gen.UserModelDao;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class User extends Activity implements View.OnClickListener{
    /**
     * 用户表
     */
    public Map<Integer, EditText> etMap = new HashMap<Integer, EditText>();
    public Button button;

    public UserAdapter adapter;
    public LinkedList<EditText> etLinkList = new LinkedList<>();
    public boolean etIsChangedUser = true;

    public RealmUtils<UserModel> realmUtils;
    public UserModelDao userModelDao;
    public UserModel userModel;
    public List<UserModel> userModels = new ArrayList<>();
    public ActivityUserBinding userBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBinding = DataBindingUtil.setContentView(this, R.layout.activity_user);
        realmUtils = new RealmUtils<>();
        userModelDao = MyApplication.getInstance().getDaoSession().getUserModelDao();
        initView();

    }

    public void initView(){
        //etLinkList.add(userBinding.userWcbNm);
        etLinkList.add(userBinding.userWstationNm);
        etLinkList.add(userBinding.userListNm);
        etLinkList.add(userBinding.userNm);
        etLinkList.add(userBinding.userName);
        etLinkList.add(userBinding.userIndex);
        etLinkList.add(userBinding.userContact);
        etLinkList.add(userBinding.userIphone);
        etLinkList.add(userBinding.userBuildTime);
        etLinkList.add(userBinding.userWpPriceNum);
        etLinkList.add(userBinding.userWaterConsumption);
        etLinkList.add(userBinding.userPurchaseWater);
        etLinkList.add(userBinding.userPurchaseWaterYear);
        etLinkList.add(userBinding.userOverdraftLimit);
        etLinkList.add(userBinding.userAlarmWater);
        etLinkList.add(userBinding.userIdcard);
        etLinkList.add(userBinding.userFirstMax);
        etLinkList.add(userBinding.userSecondMax);
        etLinkList.add(userBinding.userNote);
        etLinkList.add(userBinding.userOperator);
        etLinkList.add(userBinding.userLastWaterTime);
        etLinkList.add(userBinding.userCardNum);
        etLinkList.add(userBinding.userIssuedTimes);
        etLinkList.add(userBinding.userDataUpdate);
        etLinkList.add(userBinding.userStop);
        etLinkList.add(userBinding.userVersion);
        etMap = Entity.saveInMap(etLinkList);
        RealmUtils.setEditEnable(etMap, false);
        //RealmUtils.setEditWatch(etMap, textWatcher);


        final List<UserModel> userModels = userModelDao.loadAll();
        adapter = new UserAdapter(this,userModels, R.layout.user_item_layout);
        userBinding.userList.setAdapter(adapter);

        userBinding.userStationNo.setText(userModels.get(0).getStationNo());

        userBinding.userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userModel =userModels.get(position);
                userBinding.titleLayout.setVisibility(View.GONE);
                userBinding.userScrollview.setVisibility(View.VISIBLE);
                Gson gson = new Gson();
                String json = gson.toJson(userModel);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    for (int i = 0; i < etMap.size(); i++)
                    {
                        EditText et = etMap.get(i);
                        et.setText(jsonObject.getString(et.getTag().toString()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.e("json", json);
            }
        });

        if (Entity.loginModel.getLoginName().equals("admin")){
            userBinding.userWaterSetting.setVisibility(View.VISIBLE);
            userBinding.userWaterSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String data = new Gson().toJson(userModel);
                    startActivity(new Intent(User.this,WaterSettings.class).putExtra("data", data));
                }
            });
        } else{
            userBinding.userWaterSetting.setVisibility(View.GONE);
        }
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

    }

    public List<String> getUserNum(){
        userModels = MyApplication.getInstance().getDaoSession().getUserModelDao().loadAll();
        List<String> userNumList = new ArrayList<>();
        for (int i = 0; i < userModels.size(); i++)
        {
            UserModel userRealm = userModels.get(i);
            userNumList.add(userRealm.getUserNo());
        }
        return userNumList;
    }

    @Override
    public void onBackPressed() {

        if (userBinding.userScrollview.getVisibility() == View.VISIBLE)
        {
            userBinding.userScrollview.setVisibility(View.GONE);
            userBinding.titleLayout.setVisibility(View.VISIBLE);
        }
        else {
            super.onBackPressed();
        }
    }
}
