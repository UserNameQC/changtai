package com.changtai.ItemsList;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.changtai.R;
import com.changtai.RFID.RfidUtils;
import com.changtai.Utils.Entity;
import com.changtai.activites.BaseActivity;
import com.changtai.application.MyApplication;
import com.changtai.databinding.WaterSettingLayoutBinding;
import com.changtai.sqlModel.CardReplacementModel;
import com.changtai.sqlModel.DeviceModel;
import com.changtai.sqlModel.PriceModel;
import com.changtai.sqlModel.UserModel;
import com.changtai.sqlModelDao.CardReplacementModelDao;
import com.changtai.sqlModelDao.DeviceModelDao;
import com.changtai.sqlModelDao.PriceModelDao;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.query.QueryBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by qjcjob on 2018/12/25.
 */

public class UserActionActivity extends BaseActivity  {

    public WaterSettingLayoutBinding binding;
    public UserModel userModel;
    public DeviceModel deviceModel;
    public PriceModel priceModel;
    public String TAG = "UserActionActivity";
    public RfidUtils rfidUtils;
    public String type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.water_setting_layout);
        type = getIntent().getExtras().getString("type");
        binding.title.setText(type.equals("setting") ? "水量设置卡" : "用户补卡");
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
                if (type.equals("setting")) {
                    if (TextUtils.isEmpty(binding.userSettingUse.getText().toString())) {
                        com.changtai.Utils.Entity.toastMsg(UserActionActivity.this, "累计用水量不能为空");
                        return;
                    }
                }
                if (rfidUtils.isnNewCard()){
                    com.changtai.Utils.Entity.toastMsg(UserActionActivity.this, "此卡为新卡，请初始化以后再操作");
                    return;
                }
                int result = rfidUtils.isEmptyCard();
                if (result == 1){
                    com.changtai.Utils.Entity.toastMsg(UserActionActivity.this, "此卡已被使用，请更换空卡或者格式化后在操作");
                    return;
                }else if (result == -1){
                    com.changtai.Utils.Entity.toastMsg(UserActionActivity.this, "此卡非本系统卡，不允许使用");
                    return;
                }

                StringBuffer stringBuffer = new StringBuffer("");
                stringBuffer.append(userModel.getUserNo());
                stringBuffer.append((int) Double.parseDouble(userModel.purchaseTotal));
                stringBuffer.append((int) (Double.parseDouble(userModel.getOverdraft())/100));
                stringBuffer.append((int) (Double.parseDouble(userModel.getAlarmValue())/100));
                if (type.equals("setting")) {
                    stringBuffer.append(Integer.parseInt(binding.userSettingUse.getText().toString()));
                }

                if (rfidUtils.writeToCard(stringBuffer.toString())){
                    com.changtai.Utils.Entity.toastMsg(UserActionActivity.this, "写卡成功");
                    if (type.equals("user")){
                        int replaceTimes = userModel.getCreditcardTimes();
                        replaceTimes++;
                        userModel.setCreditcardTimes(replaceTimes);
                        userModel.setClientVersion(Long.parseLong(Entity.GetNowTime()));
                        MyApplication.getInstance().getDaoSession().getUserModelDao().insertOrReplace(userModel);
                        insertCardReplaceModel();
                    }
                }
            }
        });
    }


    @SuppressLint("SetTextI18n")
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

            if (type.equals("setting")) {
                binding.userLimitLayout.setVisibility(View.VISIBLE);
                binding.userSyLayout.setVisibility(View.VISIBLE);
                //累计上用水量的上限 不能超过累计购水量 + 透支限量的总和 最多八位数字（最大为99999999）
                int topLimit = (int) Double.parseDouble(userModel.getPurchaseTotal()) + ((int) Double.parseDouble(userModel.getOverdraft()));
                int bottomLimit = com.changtai.Utils.Entity.loginModel.getQxString().equals("管理员") ? 0 : (int) Double.parseDouble(userModel.getUsedTotal());
                binding.userSettingsLimit.setText("(" + bottomLimit + "~" + topLimit + ")");
                //剩余水量 = 年度购水量 - 累计用水量的上限
                binding.userSettingSy.setText(String.valueOf((int) Double.parseDouble(userModel.getPurchaseTotal()) - topLimit));
                binding.settingDetailsTitle.setText("水量设置卡说明：");
                binding.settingDetails.setText(
                        "(1) 更换控制器时，首选通过转移卡到原控制器插卡导出用户水量数据，再到新控制器插卡导入数据。如控制器主板损坏，则可通过本功能写卡设置用户水量数据。\n" +
                        "\n" +
                        "(2) 写卡前需输入累计用水量。累计用水量可通过网络（如近期有数据上传）或者从控制器显示屏获取，也可结合实际情况估算。\n" +
                        "\n" +
                        "(3) 水量设置卡不允许直接购水，必须先到控制器插卡，转变为用户卡后，才允许购水。未插卡前购水，读卡时会提示。");
            } else {
                binding.userSyLayout.setVisibility(View.GONE);
                binding.userLimitLayout.setVisibility(View.GONE);
                binding.settingDetailsTitle.setText("补卡说明：");
                binding.settingDetails.setText(
                        "(1) 用户卡丢失后，可使用本功能给用户补卡。补写的用户卡与该用户最后一次购水后(尚未到控制器插卡时)的状态完全一致。\n" +
                        "\n" +
                        "(2) 补卡后可以直接到控制器上插卡使用，也可以继续购水再插卡使用。此时购水读卡会提示上次购水未插卡，点”是“继续购水即可。\n" +
                        "\n" +
                        "(3) 补写的用户卡再次购水(≥1m³)，并到控制器插卡后，原用户卡即使找回也无法插卡使用，并且在购水读卡时会提示。"
                );
            }
            /**
             * 查询机井位置
             */
            String deviceNum = userModel.getDeviceNo();
            DeviceModelDao deviceModelDao = MyApplication.getInstance().getDaoSession().getDeviceModelDao();
            QueryBuilder queryBuilder = deviceModelDao.queryBuilder();
            List<DeviceModel> deviceModels = queryBuilder.where(DeviceModelDao.Properties.DeviceNo.eq(deviceNum)).list();
            if (deviceModels != null && deviceModels.size() > 0){
                deviceModel = deviceModels.get(0);
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
                priceModel = priceModels.get(0);
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

    public void insertCardReplaceModel(){
        CardReplacementModel cardReplacementModel = new CardReplacementModel();
        cardReplacementModel.setAdministratorName(Entity.loginModel.getUserName());
        cardReplacementModel.setCardReplacementId(Entity.getUUID());
        cardReplacementModel.setCreateDateTime(new Date());
        cardReplacementModel.setDeviceNo(userModel.getDeviceNo());
        cardReplacementModel.setLastDateTime(userModel.getLastDatetime());
        cardReplacementModel.setUserName(userModel.getUserName());
        cardReplacementModel.setUserNo(userModel.getUserNo());
        cardReplacementModel.setUsedTotal(userModel.getUsedTotal());
        cardReplacementModel.setPhone(deviceModel.getPhone());
        cardReplacementModel.setStationNo(deviceModel.getStationNo());
        cardReplacementModel.setLinkman(deviceModel.getLinkman());
        cardReplacementModel.setLocation(deviceModel.getLocation());
        cardReplacementModel.setPurchaseTotal(userModel.getPurchaseTotal());
        cardReplacementModel.setLastTotal(String.valueOf(Integer.parseInt(userModel.getPurchaseTotal()) - Integer.parseInt(userModel.getUsedTotal())));
        cardReplacementModel.setClientVersion(Long.parseLong(Entity.GetNowTime()));
        cardReplacementModel.setServerVersion(0L);

        CardReplacementModelDao cardReplacementModelDao = MyApplication.getInstance().getDaoSession().getCardReplacementModelDao();
        cardReplacementModelDao.insertOrReplace(cardReplacementModel);
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
