package com.changtai.ItemsList;

import android.app.Activity;
import android.databinding.DataBindingUtil;
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

import com.changtai.databinding.ActivityUserTakeWaterBinding;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class UserTakeWater extends Activity implements View.OnClickListener{

    public Map<Integer, EditText> etMap = new HashMap<Integer, EditText>();
    public Button button;
    public LinkedList<EditText> linkedList = new LinkedList<>();
    public LinkedList<String> nameList = new LinkedList<>();
    public ListView etListView;
    public ArrayAdapter adapter;
    public ScrollView waterScroll;
    public boolean etIsChangedTake = true;
    public ActivityUserTakeWaterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_take_water);
        initView();
    }

    public void initView(){

        linkedList.add(binding.utwLogo);
        linkedList.add(binding.utwWcbNm);
        linkedList.add(binding.utwWstationNm);
        linkedList.add(binding.utwListNm);
        linkedList.add(binding.utwUserNum);
        linkedList.add(binding.utwStart);
        linkedList.add(binding.utwEnds);
        linkedList.add(binding.utwAnd);
        linkedList.add(binding.utwBusinessLabels);
        linkedList.add(binding.utwInstallLoc);
        linkedList.add(binding.utwInstallTime);
        linkedList.add(binding.utwCreateTime);

        etMap = Entity.saveInMap(linkedList);
        RealmUtils.setEditEnable(etMap, false);
        RealmUtils.setEditWatch(etMap, textWatcher);

        binding.utwOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etIsChangedTake) {
                    if (!Entity.editTextIsNull(etMap)) {
//                        RealmUtils.createData(etMap, waterBeUsedDateRealm.getClass(), 0);
                        binding.takeWaterListview.setVisibility(View.VISIBLE);
                        binding.takeWaterScroll.setVisibility(View.GONE);
                        RealmUtils.setEditEnable(etMap, false);
//                        RealmUtils.setTimeUpdateToServer(waterBeUsedDateRealm.getClass(), "02");
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
//                waterBeUsedDateRealm = Entity.realm.where(WaterBeUsedDateRealm.class).findAll().get(position);
//                if (waterBeUsedDateRealm != null)
//                {
//                    waterBeUsedDateRealm = Entity.realm.copyFromRealm(waterBeUsedDateRealm);
//                    String json = new Gson().toJson(waterBeUsedDateRealm);
//                    try{
//                        JSONObject jsonObject = new JSONObject(json);
//                        for (int i = 0; i < etMap.size(); i++)
//                        {
//                            EditText et = etMap.get(i);
//                            et.setText(jsonObject.get(et.getTag().toString()).toString());
//                        }
//                    }catch (JSONException e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
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
//                    RealmUtils.createData(etMap, waterBeUsedDateRealm.getClass(), 0);
//                    etListView.setVisibility(View.VISIBLE);
//                    waterScroll.setVisibility(View.GONE);
//                    RealmUtils.setTimeUpdateToServer(waterBeUsedDateRealm.getClass(), "02");
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
//        RealmResults<WaterBeUsedDateRealm> realmList = Entity.realm.where(WaterBeUsedDateRealm.class).findAll();
//        LinkedList<String> list = new LinkedList<>();
//        for (int i = 0; i < realmList.size(); i++)
//        {
//            list.add(realmList.get(i).getId());
//        }
//        return list;
        return null;
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
