package com.changtai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.changtai.R;
import com.changtai.activites.MainActivity;
import com.changtai.sqlModel.LoginModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qjcjob on 2018/12/23.
 */

public class MainUserAdapter extends BaseAdapter {

    public List<LoginModel> loginModels = new ArrayList<>();
    public int layoutId;
    public Context context;
    public setOnItemClickListenr listenr;

    public MainUserAdapter(Context context, List<LoginModel> list, int layoutId){
        this.loginModels = list;
        this.layoutId = layoutId;
        this.context = context;
    }

    @Override
    public int getCount() {
        return loginModels.size();
    }

    @Override
    public Object getItem(int position) {
        return loginModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.main_user_item_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.loginName = convertView.findViewById(R.id.main_user_login_name);
            viewHolder.name = convertView.findViewById(R.id.main_user_name);
            viewHolder.qx = convertView.findViewById(R.id.main_user_qx);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final LoginModel loginModel = loginModels.get(position);
        viewHolder.loginName.setText(loginModel.getLoginName());
        viewHolder.name.setText(loginModel.getUserName());
        viewHolder.qx.setText(loginModel.getQxString());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listenr != null){
                    listenr.onItemClick(v, position, loginModel);
                }
            }
        });
        return convertView;
    }

    public class ViewHolder{
        TextView loginName;
        TextView name;
        TextView qx;
    }

    public interface setOnItemClickListenr{
        void onItemClick(View v, int position, LoginModel loginModel);
    }

    public void setItemClick(setOnItemClickListenr listenr){
        this.listenr = listenr;
    }
}
