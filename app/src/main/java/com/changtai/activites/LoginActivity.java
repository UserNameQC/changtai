package com.changtai.activites;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Message;
import android.os.Bundle;
import android.view.View;

import com.changtai.R;
import com.changtai.Utils.Entity;
import com.changtai.application.MyApplication;
import com.changtai.databinding.ActivityLoginBinding;
import com.changtai.sqlModel.ConfigModel;
import com.changtai.sqlModel.LoginModel;
import com.changtai.sqlModelDao.LoginModelDao;
import java.util.List;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    public ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);
        initView();
        //checkToken();
        //sendPost();
    }

    public void initView() {
        binding.loginBtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<ConfigModel> configModels = MyApplication.getInstance().getDaoSession().getConfigModelDao().queryBuilder()
                .list();
        if (configModels == null) {
            Entity.toastMsg(this, "请设置水地区号！");
        }
    }

    @SuppressLint("HandlerLeak")
    public android.os.Handler mHandler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if (dialog != null) {
                        dialog.dismiss();
                        dialog = null;
                    }

                    String userName = binding.etLoginUsername.getText().toString();
                    LoginModelDao loginModelDao = MyApplication.getInstance().getDaoSession().getLoginModelDao();
                    List<LoginModel> list = loginModelDao.queryBuilder().where(LoginModelDao.Properties.LoginName.eq(userName)).list();
                    if (list == null || list.size() <= 0) {
                        LoginModel entityModel = new LoginModel();
                        entityModel.setLoginName(binding.etLoginUsername.getText().toString());
                        entityModel.setPassword(binding.etLoginPassword.getText().toString());
                        entityModel.setQxString("操作员");
                        MyApplication.getInstance().getDaoSession().getLoginModelDao().insertOrReplace(entityModel);
                    }
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    break;
                case 1:
                    if (dialog != null) {
                        dialog.dismiss();
                        dialog = null;
                    }
                    Entity.toastMsg(LoginActivity.this, "服务器异常");
                    break;
            }
        }
    };

    /**
     * 自动查询用户名、密码判断是否登录过
     * 默认为缓存中的用户名密码为目前操作人员的账号密码
     */

    public void checkToken() {
//        String UserName = Entity.spres.getString("USERNAME", "");
//        if (!TextUtils.isEmpty(UserName)) {
//            LoginMesRealm loginMesRealm = Entity.realm.where(LoginMesRealm.class)
//                    .equalTo("UserName", UserName).findFirst();
//            if (loginMesRealm != null) {
//                String token = loginMesRealm.getToke();
//                if (!TextUtils.isEmpty(token)) {
//                    Entity.token = token;
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                    this.finish();
//                }
//            }
//        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                final String name = binding.etLoginUsername.getText().toString();
                final String pass = binding.etLoginPassword.getText().toString();

                if (name.length() <= 0) {
                    Entity.toastMsg(this, "账户名不能为空");
                    return;
                }
                if (pass.length() <= 0) {
                    Entity.toastMsg(this, "密码不能为空");
                    return;
                }

                //final ConfigModel configModel = MyApplication.getInstance().getDaoSession().load(ConfigModel.class, 0);
                final List<ConfigModel> configModels = MyApplication.getInstance().getDaoSession().getConfigModelDao()
                        .queryBuilder().list();
                if (name.equals("admin") && pass.equals("admin")) {
                    Entity.loginModel.setQxString("管理员");
                    Entity.loginModel.setLoginName("admin");
                    Entity.loginModel.setUserName("admin");
                    Entity.loginModel.setPassword("admin");
                    startActivity(new Intent(this, MainActivity.class));
                } else {
                    try {
                        progress();
                        String baseId;
                        if (configModels != null && !configModels.isEmpty()){
                            baseId = configModels.get(0).getValue();
                        }

                        LoginModelDao loginModelDao = MyApplication.getInstance().getDaoSession().getLoginModelDao();
                        List<LoginModel> loginModels = loginModelDao.queryBuilder()
                                .where(LoginModelDao.Properties.LoginName.eq(name)).list();
                        if (loginModels != null && loginModels.size() > 0) {
                            LoginModel model = loginModels.get(0);
                            String passWord = model.getPassword();
                            disMissDialog();
                            if (pass.equals(passWord)) {
                                Entity.spres.edit().putString(Entity.LOGIN_NAME, model.getLoginName()).apply();
                                Entity.spres.edit().putString(Entity.QX_STRING, model.getQxString()).apply();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            } else {
                                Entity.toastMsg(LoginActivity.this, "用户名或密码错误");
                            }
                        } else {
                            disMissDialog();
                            Entity.toastMsg(LoginActivity.this, "账号不存在，请联系管理员");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    public ProgressDialog dialog;

    public void progress() {
        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("正在登录...");
        dialog.setMessage("请稍后...");
        dialog.show();
    }

    public void disMissDialog(){
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void onBackPressed() {

        MyApplication.getInstance().exit();
        super.onBackPressed();
    }
}
