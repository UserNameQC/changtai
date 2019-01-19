package com.changtai.ItemsList;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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


public class UserPurchaseActivity extends Activity implements View.OnClickListener{

    /**
     * 用户购水明细表
     */
    public Map<Integer, EditText> etMap = new HashMap<Integer, EditText>();
    public LinkedList<EditText> linkedList = new LinkedList<>();
    public ArrayAdapter adapter;
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
