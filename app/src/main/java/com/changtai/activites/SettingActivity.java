package com.changtai.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.changtai.R;
import com.changtai.Utils.Entity;
import com.changtai.Utils.SaveDataToFile;
import com.changtai.application.MyApplication;
import com.changtai.db.Config;
import com.changtai.realm.ConfigRealm;
import com.example.john.greendaodemo.gen.ConfigDao;

import java.util.List;

import io.realm.RealmResults;

public class SettingActivity extends Activity {

    private String baseId;
    private MyApplication application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settting);
        application = (MyApplication) this.getApplication();
    }

    public void onButtonClick(View view) {
        final EditText editText = (EditText)findViewById(R.id.buy_water_id);
        if(editText.length()<=0){
            Entity.toastMsg(this, "售水站号不能为空");
            return;
        }

        if(editText.length()!=5){
            Entity.toastMsg(this, "售水站号必须是5位数字");
            return;
        }
        baseId = editText.getText().toString();
        RealmResults<ConfigRealm> configList = Entity.realm.where(ConfigRealm.class).findAll();
        if (!configList.isEmpty())
        {
            ConfigRealm config = configList.get(0);
            if (baseId.equals(config.getValue())) {
                Entity.toastMsg(SettingActivity.this, "售水站号已存在");
                return;
            }
            else
            {
                Entity.realm.beginTransaction();
                config.setValue(baseId);
                Entity.realm.commitTransaction();
            }

        }
        else
        {
            Entity.realm.beginTransaction();
            ConfigRealm configRealm =
                    Entity.realm.createObject(ConfigRealm.class, 10010);
            configRealm.setName("APP_BASEID");
            configRealm.setValue(baseId);
            configRealm.setComment("水站号");
            Entity.realm.commitTransaction();
            //Entity.isFirst = false;
        }
        startActivity(new Intent(SettingActivity.this, LoginActivity.class));
    }
}
