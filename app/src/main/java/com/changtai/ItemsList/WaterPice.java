package com.changtai.ItemsList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.changtai.R;
import com.changtai.Utils.Entity;
import com.changtai.Utils.SaveDataToFile;

import java.util.HashMap;
import java.util.Map;

public class WaterPice extends Activity implements View.OnClickListener{

    public EditText et1, et2, et3, et4, et5, et6, et7, et8, et9, et10, et11,et12;
    public Map<Integer, EditText> etMap = new HashMap<Integer, EditText>();
    public Button button;
    public String[] key = {Entity.StationNo,Entity.SjId,Entity.BureauNo,Entity.TimeSpan,Entity.Version,
            Entity.Mc,
            Entity.Sj1,Entity.Sj2,Entity.Sj3,Entity.AdministratorName,Entity.createTime,Entity.updateTime};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_pice);

        initView();
    }
    public void initView(){

        et1 = (EditText) findViewById(R.id.wp_wcb_nm);
        et2 = (EditText) findViewById(R.id.wp_wstation_nm);
        et3 = (EditText) findViewById(R.id.wp_list_nm);
        et4 = (EditText) findViewById(R.id.wp_type);
        et5 = (EditText) findViewById(R.id.wp_first);
        et6 = (EditText) findViewById(R.id.wp_second);
        et7 = (EditText) findViewById(R.id.wp_third);
        et8 = (EditText) findViewById(R.id.wp_buid_time);
        et9 = (EditText) findViewById(R.id.wp_update_time);
        et10 = (EditText) findViewById(R.id.wp_operator);
        et11 = (EditText) findViewById(R.id.wp_data_update);
        et12 = (EditText) findViewById(R.id.wp_version);

        button = (Button) findViewById(R.id.wp_ok);
        button.setOnClickListener(this);

        int a = 0;
        etMap.put(a++, et1);
        etMap.put(a++, et2);
        etMap.put(a++, et3);
        etMap.put(a++, et4);
        etMap.put(a++, et5);
        etMap.put(a++, et6);
        etMap.put(a++, et7);
        etMap.put(a++, et8);
        etMap.put(a++, et9);
        etMap.put(a++, et10);
        etMap.put(a++, et11);
        etMap.put(a++, et12);
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
