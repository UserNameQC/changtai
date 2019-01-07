package com.changtai.adapter;

import android.content.Context;
import android.databinding.adapters.AdapterViewBindingAdapter;
import android.view.View;

import com.changtai.sqlModel.DeviceModel;

import java.util.List;

/**
 * Created by qjcjob on 2019/1/7.
 */

public class DeviceSpinnerAdapter extends SpinnerAdapter<DeviceModel> {

    public onItemSelected onItemSelected;

    public DeviceSpinnerAdapter(Context context, List<DeviceModel> models){
        super(context, models);
    }
    @Override
    public void initData(View view, ViewHolder viewHolder, final DeviceModel deviceModel, final int position) {
        viewHolder.textView.setText(deviceModel.getDeviceNo());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemSelected != null){
                    onItemSelected.setOnSelected(deviceModel, position);
                }
            }
        });
    }

    public interface onItemSelected{
        void setOnSelected(DeviceModel model, int position);
    }

    public void setOnSelecteLinstener(onItemSelected onItemSelected){
        this.onItemSelected = onItemSelected;
    }
}
