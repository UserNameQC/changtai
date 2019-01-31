package com.changtai.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.changtai.ItemsList.AdministratorActivity;
import com.changtai.R;
import com.changtai.SynchronizationWithPC.DownloadFromPcActivity;
import com.changtai.Utils.Entity;
import com.changtai.Utils.SpUtils;
import com.changtai.activites.AddUserActivity;
import com.changtai.activites.LoginActivity;
import com.changtai.activites.MadeCardActivity;
import com.changtai.activites.MainActivity;
import com.changtai.activites.ResetPassWord;
import com.changtai.activites.SelectedIpServiceActivity;
import com.changtai.activites.SettingActivity;
import com.changtai.databinding.IndexMyBinding;

/**
 * Created by qjcjo on 2018/3/13.
 */

public class FragmentMine extends Fragment {

    private View mView;
    public IndexMyBinding binding;
    public ProgressDialog progressDialog;
    public SpUtils spUtils;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.index_my, container, false);
        spUtils = new SpUtils();
        initView();
        initEvent();
        return binding.getRoot();
    }

    public void initView(){
        if (Entity.loginModel != null){
            try {
                if (Entity.loginModel.getLoginName().equals("admin") && Entity.loginModel.getUserName().equals("admin")) {
                    hideView(true);
                    binding.indexMyUsername.setText(Entity.loginModel.getUserName());
                    binding.indexQx.setText(Entity.loginModel.getQxString());
                } else {
                    hideView(false);
                    binding.indexQx.setText(Entity.spres.getString(Entity.QX_STRING, ""));
                    binding.indexMyUsername.setText(Entity.spres.getString(Entity.LOGIN_NAME, ""));
                }
            }catch (NullPointerException e){
                e.printStackTrace();
                hideView(false);
            }
        }else {
            hideView(false);
            binding.indexQx.setText(Entity.spres.getString(Entity.QX_STRING, ""));
            binding.indexMyUsername.setText(Entity.spres.getString(Entity.LOGIN_NAME, ""));
        }
    }

    public void hideView(boolean flag){
        binding.addUserLayout.setVisibility(flag?View.VISIBLE:View.GONE);
        binding.addBaseLayout.setVisibility(flag?View.VISIBLE:View.GONE);
        binding.mainUserInformation.setVisibility(flag?View.VISIBLE:View.GONE);
    }

    public void initEvent(){
        binding.indexResetPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ResetPassWord.class));
            }
        });

        binding.addBaseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });

        binding.addUserLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddUserActivity.class));
            }
        });

        binding.mainUserInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AdministratorActivity.class));
            }
        });

        binding.mainUserMadeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MadeCardActivity.class));
            }
        });

        binding.mainIpSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SelectedIpServiceActivity.class));
            }
        });

        /**
         * 同步数据
         */
        binding.indexLayoutSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean serverChecked = spUtils.getBoolean(Entity.SERVER_CHECK);
                boolean pcChecked = spUtils.getBoolean(Entity.IP_CHECK);
                if (serverChecked){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("确定从服务器更新数据？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            MainActivity mainActivity = (MainActivity) getActivity();
                            mainActivity.downLoadFromWeb();
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();

                } else if (pcChecked){

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("确定从PC终端更新数据？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            DownloadFromPcActivity pcActivity = new DownloadFromPcActivity(getActivity());
                            pcActivity.onClick();
                        }
                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            }
        });

        binding.personalCenterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("是否退出登录？");
                builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Entity.loginModel != null){
                            Entity.loginModel.setQxString("");
                            Entity.loginModel.setLoginName("");
                            Entity.loginModel.setUserName("");
                            Entity.loginModel.setPassword("");
                        }
                        dialog.dismiss();
                        startActivity(new Intent(getActivity(), LoginActivity.class));
                        getActivity().finish();
                    }
                });

                builder.setNeutralButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });
    }
}
