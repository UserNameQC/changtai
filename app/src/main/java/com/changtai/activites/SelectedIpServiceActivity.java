package com.changtai.activites;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.changtai.R;
import com.changtai.Utils.Entity;
import com.changtai.Utils.SpUtils;
import com.changtai.databinding.ActivitySelectedIpServiceBinding;

@SuppressWarnings("all")
public class SelectedIpServiceActivity extends BaseActivity {

    public ActivitySelectedIpServiceBinding binding;
    public SpUtils spUtils;
    public String TAG = "SelectedIpServiceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(SelectedIpServiceActivity.this, R.layout.activity_selected_ip_service);
        spUtils = new SpUtils();
        //checkedRadio(true);
        binding.editIp.addTextChangedListener(textWatcher);
        binding.editServer.addTextChangedListener(serverWatch);
        /*boolean server_check = spUtils.getBoolean(Entity.SERVER_CHECK);
        boolean pcip_check = spUtils.getBoolean(Entity.IP_CHECK);*/
        /*if (server_check){
            binding.server.setChecked(true);
        } else if (pcip_check){
            binding.pcIp.setChecked(true);
        } else{
            binding.server.setChecked(true);
        }*/
        initEvent();
        initView();
    }

    public void initEvent() {


    }



    /*public void checkedRadio(boolean flag){
        binding.server.setChecked(flag);
        binding.pcIp.setChecked(!flag);

        spUtils.setBoolean(Entity.SERVER_CHECK, flag);
        spUtils.setBoolean(Entity.IP_CHECK, !flag);
    }*/

    public void initView() {

        binding.editIp.setText(spUtils.getString(Entity.IP));
        binding.editServer.setText(spUtils.getString(Entity.SERVER));
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
            spUtils.setString(Entity.IP, s.toString());
        }
    };

    public TextWatcher serverWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            spUtils.setString(Entity.SERVER, s.toString());
        }
    };
}
