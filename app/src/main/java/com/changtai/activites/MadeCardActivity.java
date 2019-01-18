package com.changtai.activites;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.changtai.R;
import com.changtai.RFID.RfidUtils;
import com.changtai.Utils.Entity;
import com.changtai.application.MyApplication;
import com.changtai.databinding.ActivityMadeCardBinding;
import com.changtai.sqlModel.ConfigModel;
import com.changtai.sqlModel.DeviceModel;
import com.changtai.sqlModel.UserModel;
import com.example.john.greendaodemo.gen.ConfigModelDao;
import com.example.john.greendaodemo.gen.DeviceModelDao;
import com.example.john.greendaodemo.gen.UserModelDao;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 工具卡制作
 */

public class MadeCardActivity extends BaseActivity {

    public ActivityMadeCardBinding binding;
    public List<RadioButton> list = new ArrayList<>();
    public List<String> baseId = new ArrayList<>();
    public List<DeviceModel> deviceNo = new ArrayList<>();
    public List<UserModel> userNo = new ArrayList<>();
    public RfidUtils rfidUtils;
    public String station;
    public List<String> deviceNos = new LinkedList<>();
    public List<String> userNos = new LinkedList<>();

    public List<LinearLayout> layoutList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_made_card);
        rfidUtils = new RfidUtils();
        initView();
    }

    public void initView(){
        list.add(binding.madeDeviceCheckCard);
        list.add(binding.madeDeviceClearCard);
        list.add(binding.madeDeviceTestCard);
        list.add(binding.meterFactorSetCard);
        list.add(binding.channelSetCard);
        list.add(binding.criticalSetCard);
        list.add(binding.userCheckCard);
        list.add(binding.userClearCard);
        list.add(binding.userRemoveCard);

        layoutList.add(binding.deviceCheckLinearLayout);
        layoutList.add(binding.deviceClearLinearLayout);
        layoutList.add(binding.deviceTestLinearLayout);
        layoutList.add(binding.meterFactorLinearLayout);
        layoutList.add(binding.channelSetLinearLayout);
        layoutList.add(binding.criticalSetLinearLayout);
        layoutList.add(binding.userCheckLinearLayout);
        layoutList.add(binding.userClearLinearLayout);
        layoutList.add(binding.userRemoveLinerLayout);

        for (RadioButton radioButton : list) {
            radioButton.setOnClickListener(onClickListener);
        }

        initData();

        binding.writeToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String databuff32 = "";
                if (TextUtils.isEmpty(station)) return;
                if (binding.madeDeviceTestCard.isChecked())
                    databuff32 = "F55592" + "0000000000000000" + station + "00000";    //机井测试卡
                if (binding.madeDeviceCheckCard.isChecked())
                    databuff32 = "F55594" + "0000000000000000" + station + "00000";    //机井检查卡
                if (binding.madeDeviceClearCard.isChecked())
                    databuff32 = "F55593" + "0000000000000000" + station + "00000";    //机井清零卡
                if (binding.channelSetCard.isChecked()){
                    if (binding.channelSetEdit.length() > 0){
                        databuff32 = "F55596" + String.format("{0:D4}", binding.channelSetEdit.getText().toString()) + "000000000000" + station + "00000";    //流量设定卡
                    }
                }
                if (binding.criticalSetCard.isChecked()){
                    if (binding.criticalSetEdit.length() > 0) {
                        databuff32 = "F55595" + String.format("{0:D2}", binding.criticalSetEdit.getText().toString()) + "00000000000000" + station + "00000";  //临界频率卡
                    }
                }
                if (userNos.isEmpty() || deviceNos.isEmpty()) return;
                if (binding.userCheckCard.isChecked())
                    databuff32 = "F55594" + "0000000000000000" + userNos.get(binding.madeUserNo.getSelectedIndex());  //用户检查卡
                if (binding.userClearCard.isChecked())
                    databuff32 = "F55593" + "0000000000000000" + userNos.get(binding.madeUserNo.getSelectedIndex());  //用户清零卡
                if (binding.userRemoveCard.isChecked())
                    databuff32 = "F55590" + "0000000000000000" + userNos.get(binding.madeUserNo.getSelectedIndex());  //用户转移卡
                if (binding.meterFactorSetCard.isChecked())
                    if (binding.meterFactorSetEdit.length() > 0)
                    databuff32 = "F55597" + String.format("{0:D5}", binding.meterFactorSetEdit.getText().toString()) + "0000000000" + station + "00000";      //仪表系数设定卡
                if (rfidUtils.writeToCard(databuff32)){
                    Entity.toastMsg(MadeCardActivity.this, "写卡成功！");
                }
            }
        });
    }

    /**
     * 下拉控件的数据准备
     */
    public void initData(){

        ConfigModelDao configModelDao = MyApplication.getInstance().getDaoSession().getConfigModelDao();
        List<ConfigModel> configModels = configModelDao.queryBuilder().where(ConfigModelDao.Properties.Id.eq(0L)).list();
        if (!configModels.isEmpty()){
            ConfigModel configModel = configModels.get(0);
            List<String> configNo = new LinkedList<>();
            station = configModel.getValue();
            configNo.add(configModel.getValue());
            binding.madeBaseId.attachDataSource(configNo);
        }

        DeviceModelDao deviceModelDao = MyApplication.getInstance().getDaoSession().getDeviceModelDao();
        final List<DeviceModel> deviceModels = deviceModelDao.loadAll();
        //DeviceSpinnerAdapter deviceNoAdapter = new DeviceSpinnerAdapter(this, deviceModels);
        for (DeviceModel deviceModel : deviceModels){
            deviceNos.add(deviceModel.getDeviceNo());
        }
        binding.madeDeviceNo.attachDataSource(deviceNos);
        binding.madeDeviceNo.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                binding.madeDeviceLocation.setText(deviceModels.get(position).getLocation());
            }
        });

        UserModelDao userModelDao = MyApplication.getInstance().getDaoSession().getUserModelDao();
        final List<UserModel> userModels = userModelDao.loadAll();
        for (UserModel userModel : userModels) {
            userNos.add(userModel.getUserNo());
        }
        //UserSpinnerAdapter userNoAdapter = new UserSpinnerAdapter(this, userModels);
        binding.madeUserNo.attachDataSource(userNos);
        binding.madeUserNo.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                binding.madeUserName.setText(userModels.get(position).getUserName());
            }
        });
    }

    public void hidCheckStatus(RadioButton radioButton, LinearLayout linearLayout){
        for (RadioButton radioButton1 : list) {
            radioButton1.setSelected(false);
            radioButton1.setChecked(false);
        }

        for (LinearLayout linearLayout1 : layoutList){
            linearLayout1.setVisibility(View.GONE);
        }
        radioButton.setSelected(true);
        radioButton.setChecked(true);
        linearLayout.setVisibility(View.VISIBLE);

        if (radioButton == binding.userCheckCard || radioButton == binding.userClearCard || radioButton == binding.userRemoveCard){
            binding.madeUserNo.setEnabled(true);
            binding.madeDeviceNo.setEnabled(true);
        }else{
            binding.madeUserNo.setEnabled(false);
            binding.madeDeviceNo.setEnabled(false);
        }
    }

    public View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LinearLayout linearLayout = layoutList.get(list.indexOf(v));
            hidCheckStatus((RadioButton) v, linearLayout);
        }
    };


}
