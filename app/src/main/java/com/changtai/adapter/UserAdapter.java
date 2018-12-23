package com.changtai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.changtai.R;
import com.changtai.sqlModel.UserModel;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qjcjob on 2018/12/23.
 */

public class UserAdapter extends BaseAdapter {

    public List<UserModel> userModels = new ArrayList<>();
    public Context context;
    public int layoutId;
    public UserAdapter(Context context, List<UserModel> list, int layoutId){
        this.userModels = list;
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return userModels.size();
    }

    @Override
    public Object getItem(int position) {
        return userModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = layoutInflater.inflate(layoutId, null);
            viewHolder = new ViewHolder();
            viewHolder.stationNo = convertView.findViewById(R.id.user_station_no);
            viewHolder.stationName = convertView.findViewById(R.id.user_station_name);
            viewHolder.userName = convertView.findViewById(R.id.user_name);
            viewHolder.userNo = convertView.findViewById(R.id.user_no);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        UserModel userModel = userModels.get(position);
        viewHolder.userNo.setText(userModel.getUserNo());
        viewHolder.userName.setText(userModel.getUserName());
        viewHolder.stationNo.setText(userModel.getStationNo());
        return convertView;
    }

    public class ViewHolder{
        TextView stationNo;
        TextView stationName;
        TextView userNo;
        TextView userName;
    }
}
