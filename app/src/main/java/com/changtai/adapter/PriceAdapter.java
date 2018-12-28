package com.changtai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.changtai.R;
import com.changtai.sqlModel.PriceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qjcjob on 2018/12/23.
 */

public class PriceAdapter extends BaseAdapter {

    public List<PriceModel> priceModels = new ArrayList<>();
    public Context context;
    public int layoutId;

    public PriceAdapter(Context context, List<PriceModel> priceModels, int layoutId){
        this.priceModels = priceModels;
        this.context =context;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return priceModels.size();
    }

    @Override
    public Object getItem(int position) {
        return priceModels.get(position);
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
            //viewHolder.stationNo = convertView.findViewById(R.id.user_station_no);
            viewHolder.userName = convertView.findViewById(R.id.user_name);
            viewHolder.userNo = convertView.findViewById(R.id.user_no);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PriceModel priceModel = priceModels.get(position);
        //viewHolder.stationNo.setText(priceModel.getStationNo());
        viewHolder.userNo.setText(priceModel.getMc());
        viewHolder.userName.setText(priceModel.getSj1());

        return convertView;
    }

    public class ViewHolder{
        //TextView stationNo;
        TextView userNo;
        TextView userName;
    }
}
