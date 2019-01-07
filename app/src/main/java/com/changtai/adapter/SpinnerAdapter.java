package com.changtai.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.changtai.R;
import com.changtai.activites.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qjcjob on 2019/1/3.
 */

public abstract class SpinnerAdapter<T> extends BaseAdapter {

    public List<T> data = new ArrayList<>();
    public Context context;
    public SpinnerAdapter(Context context, List<T> data){
        this.context = context;
        this.data = data;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null){
            convertView = inflater.inflate(R.layout.list_view_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.text_list_item);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        initData(convertView, viewHolder, data.get(position), position);

        return convertView;
    }

    class ViewHolder{
        TextView textView;
    }

    public abstract void initData(View view, ViewHolder holder, T t, int position);
}
