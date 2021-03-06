package com.changtai.activites;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.changtai.R;
import com.changtai.Utils.Entity;
import com.changtai.application.MyApplication;
import com.changtai.databinding.ActivitySettingBinding;
import com.changtai.sqlModel.ConfigModel;
import com.changtai.sqlModelDao.ConfigModelDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class SettingActivity extends BaseActivity {

    private String baseId;
    public ActivitySettingBinding settingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settingBinding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        application = (MyApplication) this.getApplication();
        initBaseId();
    }

    public void initBaseId(){
        ConfigModelDao configModelDao = MyApplication.getInstance().getDaoSession().getConfigModelDao();
        List<ConfigModel> configModels = configModelDao.loadAll();
        if (configModels != null && configModels.size() > 0){
            ConfigModel configModel = configModels.get(0);
            String baseId = configModel.getValue();
            settingBinding.buyWaterId.setText(baseId);
        }
    }

    public void onButtonClick(View view) {
        if (Entity.loginModel != null){
            if (!Entity.loginModel.getLoginName().equals("admin")){
                Entity.toastMsg(this, "请联系管理员更改");
                return;
            }
        }else{
            Entity.toastMsg(this, "请联系管理员更改");
            return;
        }
        final EditText editText = (EditText) findViewById(R.id.buy_water_id);
        if (editText.length() <= 0) {
            Entity.toastMsg(this, "地区号不能为空");
            return;
        }

        if (editText.length() != 5) {
            Entity.toastMsg(this, "地区号必须是5位数字");
            return;
        }
        baseId = editText.getText().toString();

        QueryBuilder<ConfigModel> queryBuilder = MyApplication.getInstance().getDaoSession().getConfigModelDao().queryBuilder();
        List<ConfigModel> configModelList = queryBuilder.where(ConfigModelDao.Properties.Id.eq(0L)).list();
        if (configModelList != null) {
            ConfigModel model;
            if(configModelList.size() > 0) {
                model = configModelList.get(0);
            }else{
                model = new ConfigModel();
            }
            model.setName("APP_BASEID");
            model.setValue(baseId);
            model.setComment("地区号");
            model.setId(0L);
            MyApplication.getInstance().getDaoSession().getConfigModelDao().insertOrReplace(model);
        }
        //startActivity(new Intent(SettingActivity.this, LoginActivity.class));
        this.finish();
    }
}
