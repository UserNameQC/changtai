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
import com.changtai.application.MyApplication;
import com.changtai.databinding.ActivityUserBuyWaterBinding;
import com.changtai.sqlModel.PurchaseRecordModel;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


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
    public PurchaseRecordModel purchaseRecordRealm;
    public boolean etIsChangedBuy = true;

    public RealmUtils<PurchaseRecordModel> realmUtils;

    public ActivityUserBuyWaterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_buy_water);
        realmUtils = new RealmUtils<>();
        initView();
    }

    public void initView(){

        linkedList.add(binding.bwDetailsIndex);
        linkedList.add(binding.bwWcbNm);
        linkedList.add(binding.bwWstationNm);
        linkedList.add(binding.bwListNm);
        linkedList.add(binding.bwNm);
        linkedList.add(binding.bwName);
        linkedList.add(binding.bwThisBuyWater);
        linkedList.add(binding.bwBuyWaterAmount);
        linkedList.add(binding.bwTime);
        linkedList.add(binding.bwYear);
        linkedList.add(binding.bwTyearNum);
        linkedList.add(binding.bwPurchaseWater);
        linkedList.add(binding.bwFirstWprice);
        linkedList.add(binding.bwFirstWaterNum);
        linkedList.add(binding.bwFirstWaterFee);
        linkedList.add(binding.bwSecondWprice);
        linkedList.add(binding.bwSecondWaterNum);
        linkedList.add(binding.bwSecondWaterFee);
        linkedList.add(binding.bwThirdWprice);
        linkedList.add(binding.bwThirdWaterNum);
        linkedList.add(binding.bwThirdWaterFee);
        linkedList.add(binding.bwNote);
        linkedList.add(binding.bwOperator);
        linkedList.add(binding.bwDataUpdate);
        linkedList.add(binding.bwVersion);
        etMap = Entity.saveInMap(linkedList);
        RealmUtils.setEditEnable(etMap, false);
        RealmUtils.setEditWatch(etMap, textWatcher);
        binding.bwOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etIsChangedBuy) {
                    if (!Entity.editTextIsNull(etMap)) {
                        PurchaseRecordModel purModel = realmUtils.createData(etMap, 0);
                        etListView.setVisibility(View.VISIBLE);
                        etScroll.setVisibility(View.GONE);
                        /**
                         * 更新数据
                         */
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
        binding.buyListview.setAdapter(adapter);

        binding.buyListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                binding.buyListview.setVisibility(View.GONE);
                binding.buyScroll.setVisibility(View.VISIBLE);
                purchaseRecordRealm = MyApplication.getInstance().getDaoSession().getPurchaseRecordModelDao().loadAll().get(position);
                if (purchaseRecordRealm != null)
                {
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

            break;
        }
    }

    public List<String> getUserNum(){

        List<PurchaseRecordModel> realms = MyApplication.getInstance().getDaoSession().getPurchaseRecordModelDao().loadAll();
        LinkedList<String> list = new LinkedList<>();
        for (int i =0; i < realms.size(); i++)
        {
            list.add(realms.get(i).getPurchaseRecordId());
        }
        return list;
    }

    @Override
    public void onBackPressed() {

        if (binding.buyScroll.getVisibility() == View.VISIBLE)
        {
            binding.buyScroll.setVisibility(View.GONE);
            binding.buyListview.setVisibility(View.VISIBLE);
        }
        else {
            super.onBackPressed();
        }
    }
}
