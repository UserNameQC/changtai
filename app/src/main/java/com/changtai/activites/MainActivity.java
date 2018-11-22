package com.changtai.activites;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.changtai.BaiduMap.BaiduMapActivity;
import com.changtai.ItemsList.DeviceList;
import com.changtai.ItemsList.MainUser;
import com.changtai.ItemsList.User;
import com.changtai.ItemsList.UserBuyWater;
import com.changtai.ItemsList.WaterPice;
import com.changtai.R;
import com.changtai.RFID.demo1443A;
import com.changtai.Utils.Entity;
import com.changtai.databinding.ActivityMainBinding;
import com.changtai.fragment.FragmentMain;
import com.changtai.fragment.FragmentMine;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton home, mine;
    private TextView bt_rfid, bt_map, bt_well, item_user, item_wPrice, item_operator, item_admin, item_bWater;
    private TextView t_username, tt_rePword;
    private LinearLayout sync_ll;
    public ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        initView();
    }

    public void initView(){
        home = (RadioButton) findViewById(R.id.main_home);
        mine = (RadioButton) findViewById(R.id.main_my);

        final View layoutMain = findViewById(R.id.main_layout);
        final View layoutMine = findViewById(R.id.main_index_my);
        /**
         * itmes 控件初始化
         */
        bt_rfid = (TextView) layoutMain.findViewById(R.id.bt_rfid);
        bt_map = (TextView) layoutMain.findViewById(R.id.bt_bd_map);
        bt_well = (TextView) layoutMain.findViewById(R.id.item_well_list);
        item_user = (TextView) layoutMain.findViewById(R.id.item_user);
        item_wPrice = (TextView) layoutMain.findViewById(R.id.item_water_price);
        item_operator = (TextView) layoutMain.findViewById(R.id.item_operator);
        item_admin = (TextView) layoutMain.findViewById(R.id.item_admin);
        item_bWater = (TextView) layoutMain.findViewById(R.id.item_user_buy_water);

        t_username = (TextView) layoutMine.findViewById(R.id.index_my_username);
        tt_rePword = (TextView) layoutMine.findViewById(R.id.index_reset_pw);
        sync_ll = layoutMine.findViewById(R.id.index_layout_sync);
        t_username.setText(Entity.spres.getString("USERNAME", " "));

        bt_rfid.setOnClickListener(this);
        bt_map.setOnClickListener(this);
        bt_well.setOnClickListener(this);
        item_user.setOnClickListener(this);
        item_wPrice.setOnClickListener(this);
        item_operator.setOnClickListener(this);
        item_admin.setOnClickListener(this);
        item_bWater.setOnClickListener(this);

        t_username.setOnClickListener(this);
        tt_rePword.setOnClickListener(this);
        sync_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSyncData(false);
            }
        });

        final FragmentMain fragmentMain = new FragmentMain();
        final FragmentMine  fragmentMine = new FragmentMine();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (layoutMine.getVisibility() == View.VISIBLE) {
                    layoutMine.setVisibility(View.GONE);
                    layoutMain.setVisibility(View.VISIBLE);
                }
            }
        });

        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (layoutMain.getVisibility() == View.VISIBLE) {
                    layoutMain.setVisibility(View.GONE);
                    layoutMine.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.bt_rfid:
                startActivity(new Intent(MainActivity.this, demo1443A.class));
                break;
            case R.id.bt_bd_map:
                startActivity(new Intent(MainActivity.this, BaiduMapActivity.class));
                break;
            case R.id.item_well_list:
                startActivity(new Intent(MainActivity.this, DeviceList.class));
                break;
            case R.id.item_user:
                startActivity(new Intent(MainActivity.this, User.class));
                break;
            case R.id.item_water_price:
                startActivity(new Intent(MainActivity.this, WaterPice.class));
                break;
            case R.id.item_operator:
                startActivity(new Intent(MainActivity.this, MainUser.class));
                break;
            case R.id.item_admin:
                startActivity(new Intent(MainActivity.this, MainUser.class));
                break;
            case R.id.item_user_buy_water:
                startActivity(new Intent(MainActivity.this, UserBuyWater.class));
                break;
            case R.id.index_reset_pw:
                startActivity(new Intent(MainActivity.this, ResetPassWord.class));
                break;
        }
    }
}
