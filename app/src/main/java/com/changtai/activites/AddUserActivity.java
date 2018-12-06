package com.changtai.activites;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.changtai.R;
import com.changtai.Utils.Entity;
import com.changtai.application.MyApplication;
import com.changtai.databinding.ActivityAddUserBinding;
import com.changtai.sqlModel.LoginModel;
import com.example.john.greendaodemo.gen.LoginModelDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddUserActivity extends BaseActivity {

    public ActivityAddUserBinding binding;
    public LoginModelDao loginModelDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding = DataBindingUtil. setContentView(this, R.layout.activity_add_user);
         loginModelDao = MyApplication.getInstance().getDaoSession().getLoginModelDao();
         initView();
    }

    public void initView(){
       binding.addRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                checkIsEmpty();
           }
       });

       binding.addQx.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final String[] qx = new String[]{"管理员", "操作员"};
               AlertDialog.Builder builder = new AlertDialog.Builder(AddUserActivity.this);
               builder.setTitle("选择权限");
               builder.setItems(qx, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       binding.addQx.setText(qx[which]);
                       dialog.dismiss();
                   }
               });
               //builder.setCancelable(false);
               builder.show();
           }
       });
    }

    public void checkIsEmpty(){
        Map<Integer, EditText> editMap = new HashMap<>();
        editMap.put(0, binding.addLoginName);
        editMap.put(1, binding.addUserName);
        editMap.put(2, binding.addPassWord);
        editMap.put(3, binding.addQx);

        if (!Entity.editTextIsNull(editMap)){
            List<LoginModel> loginModels = loginModelDao.queryBuilder()
                    .where(LoginModelDao.Properties.LoginName.eq(binding.addLoginName.getText().toString()))
                    .list();
            List<LoginModel> userLoginModel = loginModelDao.queryBuilder()
                    .where(LoginModelDao.Properties.UserName.eq(binding.addUserName.getText().toString()))
                    .list();
            if (loginModels != null && loginModels.size() > 0){
                Entity.toastMsg(this, "登录名已经被占用");
            }else if (userLoginModel != null && userLoginModel.size() > 0){
                Entity.toastMsg(this, "用户名已存在");
            }else{
                LoginModel loginModel = new LoginModel();
                loginModel.setLoginName(binding.addLoginName.getText().toString());
                loginModel.setUserName(binding.addUserName.getText().toString());
                loginModel.setPassword(binding.addPassWord.getText().toString());
                loginModel.setQxString(binding.addQx.getText().toString());
                loginModelDao.insert(loginModel);
                createDialog();
            }
        } else{
            Entity.toastMsg(this, "请完善信息");
        }
    }

    public void createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("注册成功，是否继续注册？");
        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                binding.addLoginName.setText("");
                binding.addUserName.setText("");
                binding.addPassWord.setText("");
                binding.addQx.setText("");
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                AddUserActivity.this.finish();
            }
        });
        builder.show();
    }
}
