package com.changtai.activites;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.changtai.R;

public class ItemsActivity extends BaseActivity implements View.OnClickListener{

    public Button item_well, item_take_water, item_buy_water, item_address_wellType, item_water_price, item_operator;
    public Button item_rfid, item_map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
//        item_well = (Button) findViewById(R.id.item_well_list);
//        item_take_water = (Button) findViewById(R.id.item_user_take_water);
//        item_buy_water = (Button) findViewById(R.id.item_user_buy_water);
//        item_address_wellType = (Button) findViewById(R.id.item_address_well_type);
//        item_water_price = (Button) findViewById(R.id.item_water_price);
//        item_operator = (Button) findViewById(R.id.item_operator);
//        item_rfid = (Button) findViewById(R.id.bt_rfid);
//        item_map = (Button) findViewById(R.id.bt_baiduMap);

        item_well.setOnClickListener(this);
        item_take_water.setOnClickListener(this);
        item_buy_water.setOnClickListener(this);
        item_address_wellType.setOnClickListener(this);
        item_water_price.setOnClickListener(this);
        item_operator.setOnClickListener(this);
        item_rfid.setOnClickListener(this);
        item_map.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
//            case R.id.item_well_list:
//                startActivity(new Intent(ItemsActivity.this, DeviceModelActivity.class));
//                break;
//            case R.id.item_user_take_water:
//                startActivity(new Intent(ItemsActivity.this, UserTakeWater.class));
//                break;
//            case R.id.item_user_buy_water:
//                startActivity(new Intent(ItemsActivity.this, UserPurchaseActivity.class));
//                break;
//            case R.id.item_address_well_type:
//                startActivity(new Intent(ItemsActivity.this, UserModelActivity.class));
//                break;
//            case R.id.item_water_price:
//                startActivity(new Intent(ItemsActivity.this, WaterPriceActivity.class));
//                break;
//            case R.id.item_operator:
//                startActivity(new Intent(ItemsActivity.this, AdministratorActivity.class));
//                break;
//            case R.id.bt_rfid:
//                startActivity(new Intent(ItemsActivity.this, demo1443A.class));
//                break;
//            case R.id.bt_baiduMap:
//                startActivity(new Intent(ItemsActivity.this, BaiduMapActivity.class));
//                break;
        }
    }
}
