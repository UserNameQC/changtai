package com.changtai.ItemsList;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.changtai.R;
import com.changtai.activites.BaseActivity;
import com.changtai.databinding.WaterSettingLayoutBinding;

/**
 * Created by qjcjob on 2018/12/25.
 */

public class WaterSettings extends BaseActivity  {

    public WaterSettingLayoutBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.water_setting_layout);
        initClick();
    }

    public void initClick(){
        binding.clickMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.clickMore.setVisibility(View.GONE);
                binding.moreLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    public void initViews(){

    }

    @Override
    public void onBackPressed() {
        if (binding.moreLayout.getVisibility() == View.VISIBLE){
            binding.moreLayout.setVisibility(View.GONE);
            binding.clickMore.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }
}
