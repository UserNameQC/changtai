package com.changtai.activites;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.changtai.R;
import com.changtai.Utils.Entity;
import com.changtai.Utils.HttpBaseTest;
import com.changtai.databinding.ActivityResetPasswordBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by qjcjo on 2018/3/30.
 */

public class ResetPassWord extends BaseActivity{

    public EditText userName, passWord, newPassword;
    public Map<Integer, EditText> editTextMap = new HashMap<>();
    public LinkedList<EditText> editTexts = new LinkedList<>();
    public Button button;

    public ActivityResetPasswordBinding passwordBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        passwordBinding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password);
        initView();
    }

    public void initView()
    {
        userName = findViewById(R.id.reset_username);
        editTexts.add(userName);
        passWord = findViewById(R.id.reset_old_password);
        editTexts.add(passWord);
        newPassword = findViewById(R.id.reset_new_password);
        editTexts.add(newPassword);
        editTextMap = Entity.saveInMap(editTexts);

        button = findViewById(R.id.reset_ok);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Entity.editTextIsNull(editTextMap))
                {
                    if (newPassword.getText().toString().equals(passWord.getText().toString()))
                    {
                        Entity.toastMsg(ResetPassWord.this, "新密码不能与旧密码相同");
                    }
                    else
                    {
                        Entity.executorService.execute(new Runnable() {
                            @Override
                            public void run() {
                                Map<String, String> headMap = new HashMap<>();
                                headMap.put("token", Entity.token);
                                Map<String, Object> dataMap = new HashMap<>();
                                dataMap.put("Y_PWD", passWord.getText().toString());
                                String result = HttpBaseTest.sendPost(headMap, dataMap, Entity.verifyPasswordUrl);
                                try{
                                    JSONObject object = new JSONObject(result);
                                    JSONObject array = object.getJSONObject("head");
                                    String code = array.getString("code");
                                    if (code.equals("0000"))
                                    {
                                        dataMap.put("NEW_PWD", newPassword.getText().toString());
                                        String restResult = HttpBaseTest.sendPost(headMap, dataMap, Entity.resetPasswordUrl);
                                        Log.e("resetResult", restResult);
                                    }
                                    else
                                    {
                                        mHandler.sendEmptyMessage(0);
                                    }
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    @SuppressLint("HandlerLeak")
    public Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 0:
                    Entity.toastMsg(ResetPassWord.this, "服务器异常");
                    break;
            }
        }
    };
}
