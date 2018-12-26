package com.changtai.ItemsList;

import android.content.Context;
import android.content.Entity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.android.RfidControll;
import com.changtai.R;
import com.changtai.RFID.RfidUtils;
import com.changtai.Utils.HelloWorldController;
import com.changtai.activites.BaseActivity;
import com.changtai.application.MyApplication;
import com.changtai.databinding.WaterSettingLayoutBinding;
import com.changtai.sqlModel.DeviceModel;
import com.changtai.sqlModel.PriceModel;
import com.changtai.sqlModel.UserModel;
import com.example.john.greendaodemo.gen.DeviceModelDao;
import com.example.john.greendaodemo.gen.PriceModelDao;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.query.QueryBuilder;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by qjcjob on 2018/12/25.
 */

public class WaterSettings extends BaseActivity  {

    public WaterSettingLayoutBinding binding;
    public UserModel userModel;
    public String TAG = "WaterSettings";
    public RfidUtils rfidUtils;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.water_setting_layout);
        rfidUtils = new RfidUtils();
        initViews();
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

        binding.writeToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(binding.userSettingUse.getText().toString())){
                    com.changtai.Utils.Entity.toastMsg(WaterSettings.this, "累计用水量不能为空");
                    return;
                }
                if (rfidUtils.isnNewCard()){
                    com.changtai.Utils.Entity.toastMsg(WaterSettings.this, "此卡为新卡，请初始化以后再操作");
                    return;
                }
                int result = rfidUtils.isEmptyCard();
                if (result == 1){
                    com.changtai.Utils.Entity.toastMsg(WaterSettings.this, "此卡已被使用，请更换空卡或者格式化后在操作");
                }else if (result == -1){
                    com.changtai.Utils.Entity.toastMsg(WaterSettings.this, "此卡非本系统卡，不允许使用");
                }

                StringBuffer stringBuffer = new StringBuffer("");
                stringBuffer.append(userModel.getUserNo());
                stringBuffer.append((int) Double.parseDouble(userModel.purchaseTotal));
                stringBuffer.append((int) (Double.parseDouble(userModel.getOverdraft())/100));
                stringBuffer.append((int) (Double.parseDouble(userModel.getAlarmValue())/100));
                stringBuffer.append(Integer.parseInt(binding.userSettingUse.getText().toString()));

                if (rfidUtils.writeToCard(stringBuffer.toString())){
                    com.changtai.Utils.Entity.toastMsg(WaterSettings.this, "写卡成功");
                }
            }
        });
    }


    public void initViews(){
        try {
            String data = getIntent().getExtras().getString("data", null);
            userModel = new Gson().fromJson(data, new TypeToken<UserModel>() {
            }.getType());
            binding.userSettingStation.setText(userModel.getStationNo());
            binding.userSettingDevice.setText(userModel.getDeviceNo());
            //binding.userSettingLocation.setText(userModel.get);//机井安装位置
            binding.userSettingUserNo.setText(userModel.getUserNo());
            binding.userSettingStatus.setText(userModel.getStopFlag() ? "暂停使用" : "正常使用");//用户状态
            binding.userSettingName.setText(userModel.getUserName());
            binding.userSettingCard.setText(userModel.getCredentialNo());
            binding.userSettingContactName.setText(userModel.getLinkman());
            binding.userSettingContact.setText(userModel.getPhone());
            binding.userSettingNotes.setText(userModel.getComment());
            binding.userSettingTz.setText(userModel.getOverdraft());
            binding.userSettingBj.setText(userModel.getAlarmValue());
            binding.userSettingTop1.setText(userModel.getLimitSj1());
            binding.userSettingTop2.setText(userModel.getLimitSj2());
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(userModel.getLastDatetime());
            binding.userSettingTime.setText(date);
            binding.userSettingTotal.setText(userModel.getPurchaseTotal());
            binding.userSettingYear.setText(userModel.getPurchaseTotalThisYear());

            //累计上用水量的上限 不能超过累计购水量 + 透支限量的总和 最多八位数字（最大为99999999）
            int topLimit = (int) Double.parseDouble(userModel.getPurchaseTotal()) + ((int) Double.parseDouble(userModel.getOverdraft()));
            int bottomLimit = com.changtai.Utils.Entity.loginModel.getQxString().equals("管理员") ? 0 : (int) Double.parseDouble(userModel.getUsedTotal());
            binding.userSettingsLimit.setText("(" + bottomLimit + "~" + topLimit + ")");
            //剩余水量 = 年度购水量 - 累计用水量的上限
            binding.userSettingSy.setText(String.valueOf((int) Double.parseDouble(userModel.getPurchaseTotal()) - topLimit));

            /**
             * 查询机井位置
             */
            String deviceNum = userModel.getDeviceNo();
            DeviceModelDao deviceModelDao = MyApplication.getInstance().getDaoSession().getDeviceModelDao();
            QueryBuilder queryBuilder = deviceModelDao.queryBuilder();
            List<DeviceModel> deviceModels = queryBuilder.where(DeviceModelDao.Properties.DeviceNo.eq(deviceNum)).list();
            if (deviceModels != null && deviceModels.size() > 0){
                DeviceModel deviceModel = deviceModels.get(0);
                binding.userSettingDevice.setText(deviceModel.getLocation());
            }
            /**
             * 查询水价
             */
            PriceModelDao priceModelDao = MyApplication.getInstance().getDaoSession().getPriceModelDao();
            QueryBuilder<PriceModel> queryBuilderPrice = priceModelDao.queryBuilder();
            List<PriceModel> priceModels = queryBuilderPrice.where(PriceModelDao.Properties.SjId.eq(userModel.getSjId()))
                    .where(PriceModelDao.Properties.StationNo.eq(userModel.getStationNo())).list();
            if (priceModels != null && priceModels.size() > 0){
                PriceModel priceModel = priceModels.get(0);
                binding.userSettingType.setText(priceModel.getMc());
                binding.userSettingSj1.setText(priceModel.getSj1());
                binding.userSettingSj2.setText(priceModel.getSj2());
                binding.userSettingSj3.setText(priceModel.getSj3());
            }
        }catch (JsonSyntaxException e){
            Log.e(TAG, "initViews: ", e);
        } catch (IllegalStateException e){
            Log.e(TAG, "initViews: ", e);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
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
