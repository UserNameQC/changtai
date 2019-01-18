package com.changtai.activites;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CompoundButton;

import com.changtai.R;
import com.changtai.Utils.Entity;
import com.changtai.Utils.SpUtils;
import com.changtai.databinding.ActivitySelectedIpServiceBinding;

@SuppressWarnings("all")
public class SelectedIpServiceActivity extends BaseActivity{

    public ActivitySelectedIpServiceBinding binding;
    public SpUtils spUtils;
    public String TAG = "SelectedIpServiceActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(SelectedIpServiceActivity.this, R.layout.activity_selected_ip_service);
        spUtils = new SpUtils();
        binding.editIp.addTextChangedListener(textWatcher);
        boolean server_check = spUtils.getBoolean(Entity.SERVER_CHECK);
        boolean pcip_check = spUtils.getBoolean(Entity.IP_CHECK);
        if (server_check){
            binding.server.setChecked(true);
        } else if (pcip_check){
            binding.pcIp.setChecked(true);
        } else{
            binding.server.setChecked(true);
        }
        initEvent();
        initView();
    }

    public void initEvent(){
        binding.server.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spUtils.setBoolean(Entity.SERVER_CHECK, isChecked);
                Log.e(TAG, "server: " + isChecked );
            }
        });

        binding.pcIp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spUtils.setBoolean(Entity.IP_CHECK, isChecked);
                Log.e(TAG, "ip: " + isChecked );
            }
        });
    }

    public void initView(){
        String url;
        if (binding.server.isChecked()){
            Log.e(TAG, "isSelected" + binding.server.isSelected());
            url = spUtils.getString(Entity.SERVER);
        } else {
            url = spUtils.getString(Entity.IP);
        }
        if (!TextUtils.isEmpty(url)){
            binding.editIp.setText(url);
        }
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
            if (binding.server.isChecked()){
                spUtils.setString(Entity.SERVER, s.toString());
            } else {
                spUtils.setString(Entity.IP, s.toString());
            }
        }
    };
}
