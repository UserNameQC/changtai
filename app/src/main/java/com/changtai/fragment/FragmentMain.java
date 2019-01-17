package com.changtai.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.changtai.BaiduMap.BaiduMapActivity;
import com.changtai.ItemsList.DeviceModelActivity;
import com.changtai.ItemsList.AdministratorActivity;
import com.changtai.ItemsList.UserModelActivity;
import com.changtai.ItemsList.UserBuyWater;
import com.changtai.ItemsList.WaterPriceActivity;
import com.changtai.R;
import com.changtai.RFID.demo1443A;
import com.changtai.databinding.ActivityItemsBinding;

/**
 * Created by qjcjo on 2018/3/13.
 */

public class FragmentMain extends Fragment {

    private View mView;
    public ActivityItemsBinding binding;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_items, container,false);
        initEvent();
        return binding.getRoot();
    }

    public void initView(){

    }

    public void initEvent(){
        
        binding.btRfid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), demo1443A.class));
            }
        });
        
        binding.btBdMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BaiduMapActivity.class));
            }
        });

        binding.itemWellList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DeviceModelActivity.class));
            }
        });

        binding.itemUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserModelActivity.class));
            }
        });
        
        binding.itemWaterPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WaterPriceActivity.class));
            }
        });
        
        binding.itemOperator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AdministratorActivity.class));
            }
        });
        
        binding.itemAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AdministratorActivity.class));
            }
        });
        
        binding.itemUserBuyWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UserBuyWater.class));
            }
        });
    }
}
