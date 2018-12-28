package com.changtai.ItemsList;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.changtai.R;
import com.changtai.Utils.Entity;
import com.changtai.Utils.RealmUtils;
import com.changtai.Utils.SaveDataToFile;
import com.changtai.adapter.PriceAdapter;
import com.changtai.application.MyApplication;
import com.changtai.databinding.ActivityWaterPiceBinding;
import com.changtai.sqlModel.PriceModel;
import com.example.john.greendaodemo.gen.DeviceModelDao;
import com.example.john.greendaodemo.gen.PriceModelDao;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WaterPice extends Activity implements View.OnClickListener{

    public Map<Integer, EditText> etMap = new HashMap<Integer, EditText>();
    public Button button;
    public String[] key = {Entity.StationNo,Entity.SjId,Entity.BureauNo,Entity.TimeSpan,Entity.Version,
            Entity.Mc,
            Entity.Sj1,Entity.Sj2,Entity.Sj3,Entity.AdministratorName,Entity.createTime,Entity.updateTime};

    public ActivityWaterPiceBinding binding;
    public LinkedList<EditText> list = new LinkedList<>();
    public PriceAdapter priceAdapter;
    public PriceModelDao priceModelDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_water_pice);
        priceModelDao = MyApplication.getInstance().getDaoSession().getPriceModelDao();
        initView();
    }
    public void initView(){

        list.add(binding.wpWstationNm);
        list.add(binding.wpListNm);
        list.add(binding.wpType);
        list.add(binding.wpFirst);
        list.add(binding.wpSecond);
        list.add(binding.wpThird);
        //list.add(binding.wpBuidTime);
        //list.add(binding.wpUpdateTime);
        list.add(binding.wpOperator);
        //list.add(binding.wpDataUpdate);
        //list.add(binding.wpVersion);
        binding.wpOk.setOnClickListener(this);

        etMap = Entity.saveInMap(list);
        RealmUtils.setEditEnable(etMap, false);

        final List<PriceModel> priceModels = priceModelDao.loadAll();

        priceAdapter = new PriceAdapter(this, priceModels, R.layout.user_item_layout);
        binding.priceListView.setAdapter(priceAdapter);

        binding.userStationNo.setText(priceModels.get(0).getStationNo());
        binding.priceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                binding.titleLayout.setVisibility(View.GONE);
                binding.priceScroll.setVisibility(View.VISIBLE);
                PriceModel priceModel = priceModels.get(position);
                String json = new Gson().toJson(priceModel);
                try{
                    JSONObject jsonObject = new JSONObject(json);
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
        });

    }

    @Override
    public void onClick(View view) {

        if (!Entity.editTextIsNull(etMap))
        {
            final StringBuilder builder = new StringBuilder("");
            for (int i =0; i < etMap.size(); i++)
            {
                builder.append(key[i]).append(Entity.MID).append(etMap.get(i).getText().toString()).append(Entity.END);
            }
            Entity.executorService.execute(new Runnable() {
                @Override
                public void run() {
                    SaveDataToFile saveDataToFile = new SaveDataToFile();
                    saveDataToFile.saveFile("WaterPrice", builder.toString(), 1);
                }
            });
        }
        else
        {
            Entity.toastMsg(this, "输入不能空");
        }
    }
}
