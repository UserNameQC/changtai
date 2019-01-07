package com.changtai.adapter;

import android.content.Context;
import android.view.View;

import com.changtai.sqlModel.DeviceModel;
import com.changtai.sqlModel.UserModel;

import java.util.List;

/**
 * Created by qjcjob on 2019/1/7.
 */

public class UserSpinnerAdapter extends SpinnerAdapter<UserModel> {

    public onItemSelected onItemSelected;

    public UserSpinnerAdapter(Context context, List<UserModel> data) {
        super(context, data);
    }

    @Override
    public void initData(View view, ViewHolder holder, final UserModel userModel, final int position) {
        holder.textView.setText(userModel.getUserNo());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemSelected != null){
                    onItemSelected.setOnSelected(userModel, position);
                }
            }
        });
    }

    public interface onItemSelected{
        void setOnSelected(UserModel model, int position);
    }

    public void setOnSelecteLinstener(UserSpinnerAdapter.onItemSelected onItemSelected){
        this.onItemSelected = onItemSelected;
    }
}
