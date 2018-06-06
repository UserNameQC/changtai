package com.changtai.activites;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.changtai.ItemsList.User;
import com.changtai.R;
import com.changtai.Utils.AESOperator;
import com.changtai.Utils.AESUtil;
import com.changtai.Utils.Entity;
import com.changtai.Utils.HttpBaseTest;
import com.changtai.application.MyApplication;
import com.changtai.db.Config;
import com.changtai.realm.ConfigRealm;
import com.changtai.realm.LoginMesRealm;
import com.example.john.greendaodemo.gen.ConfigDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.Map;
import java.util.logging.Handler;

import io.realm.RealmResults;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends Activity implements View.OnClickListener{

    private EditText userName;
    private EditText passWord;
    private TextView login_bt;
    private String User_Name, Pass_Word;
    private OkHttpClient okHttpClient;
    private MyApplication application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        checkToken();
        //sendPost();
    }
    public void initView(){
        application =(MyApplication) getApplication();
        userName = (EditText) findViewById(R.id.et_login_username);
        passWord = (EditText) findViewById(R.id.et_login_password);
        login_bt = (TextView) findViewById(R.id.login_btn);
        login_bt.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (application.getIsFirst())
//        {
//            Entity.toastMsg(this, "检测到您是第一次登陆，请设置水站号！");
//        }
        ConfigRealm configRealm = Entity.realm.where(ConfigRealm.class).findFirst();
        if (configRealm == null)
        {
            Entity.toastMsg(this, "请设置水站号！");
        }
    }

    @SuppressLint("HandlerLeak")
    public android.os.Handler  mHandler = new android.os.Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
                case 0:
                    if (dialog != null)
                    {
                        dialog.dismiss();
                        dialog = null;
                    }
                    boolean isHave = false;
                    RealmResults<LoginMesRealm> mesList = Entity.realm.where(LoginMesRealm.class).findAll();
                    if (mesList.size() > 0)
                    {
                        for (int i = 0; i < mesList.size(); i++){
                            LoginMesRealm loginMesRealm = mesList.get(i);
                            if (userName.getText().toString().equals(loginMesRealm.getUserName()))
                            {
                                isHave = true;
                                break;
                            }
                            Log.e("name", loginMesRealm.getUserName());
                            Log.e("pass", loginMesRealm.getUserPwd());
                            Log.e("token", loginMesRealm.getToke());
                        }
                    }
                    else
                    {
                        Entity.realm.beginTransaction();
                        LoginMesRealm mesRealm = Entity.realm.createObject(LoginMesRealm.class, System.currentTimeMillis());
                        //mesRealm.setLoginId(System.currentTimeMillis());
                        mesRealm.setToke(Entity.token);
                        mesRealm.setUserName(userName.getText().toString());
                        mesRealm.setUserPwd(passWord.getText().toString());
                        Entity.realm.commitTransaction();
                    }
                    if (isHave)
                    {
                        Entity.realm.beginTransaction();
                        LoginMesRealm mesRealm = Entity.realm.createObject(LoginMesRealm.class, System.currentTimeMillis());
                        //mesRealm.setLoginId(System.currentTimeMillis());
                        mesRealm.setToke(Entity.token);
                        mesRealm.setUserName(userName.getText().toString());
                        mesRealm.setUserPwd(passWord.getText().toString());
                        Entity.realm.commitTransaction();
                    }
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    break;
                case 1:
                    if (dialog!=null){
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

    public void checkToken(){
        String UserName = Entity.spres.getString("USERNAME", "");
        if (!TextUtils.isEmpty(UserName)) {
            LoginMesRealm loginMesRealm = Entity.realm.where(LoginMesRealm.class)
                    .equalTo("UserName", UserName).findFirst();
            if (loginMesRealm != null) {
                String token = loginMesRealm.getToke();
                if (!TextUtils.isEmpty(token)) {
                    Entity.token = token;
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    this.finish();
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.login_btn:

                if(userName.length()<=0){
                    Entity.toastMsg(this, "账户名不能为空");
                    return;
                }
                if(passWord.length()<=0){
                    Entity.toastMsg(this, "密码不能为空");
                    return;
                }

                final ConfigRealm configRealm = Entity.realm.where(ConfigRealm.class).equalTo("Id", 10010).findFirst();
                //if (configRealm == null) return;

                if (userName.getText().toString().equals("admin") && passWord.getText().toString().equals("admin"))
                {
                    startActivity(new Intent(this, SettingActivity.class));
                }
                else if (configRealm == null)
                {
                    Entity.toastMsg(LoginActivity.this, "请设置水站号");
                }
                else
                {


                    try {
                        progress();
                        final String baseId = configRealm.getValue();
                        final String name = userName.getText().toString();
                        final String pass = passWord.getText().toString();
                        Entity.spres.edit().putString("USERNAME", name).apply();
                        Entity.spres.edit().putString("PASSWORD", pass).apply();
                        final String base64Pass = AESOperator.getInstance().encrypt(pass);

                        System.out.println(pass + "   加密后：" + base64Pass);

                        Entity.executorService.execute(new Runnable() {
                            @Override
                            public void run() {
                                Map<String, Object> map = new HashMap<String, Object>();
                                //JSONObject obj = new JSONObject();

                                map.put("AREA_CODE",baseId);
                                map.put("USER_ACCOUNT", name);
                                map.put("USER_PWD", base64Pass);
                                map.put("USE_TYPE", "1");
//                                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//                                    RequestBody body = RequestBody.create(JSON, obj.toString());
                                System.out.println("请求参数" + map.toString());
                                String result = HttpBaseTest.sendPost(null, map, Entity.loginUrl);
                                if (!TextUtils.isEmpty(result)) {
                                    try {
                                        JSONObject object = new JSONObject(result);
                                        JSONObject array = object.getJSONObject("head");
                                        String code = array.getString("code");

                                        if (code.equals("0000")) {
                                            Entity.token = object.getJSONObject("data").getString("token");
                                            mHandler.sendEmptyMessage(0);
                                        }else if (code.equals("9999"))
                                        {
                                            mHandler.sendEmptyMessage(1);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                Log.e("LoginActivity", Entity.loginUrl + "; " + result);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    public ProgressDialog dialog;
    public void progress(){
        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("正在登录...");
        dialog.setMessage("请稍后...");
        dialog.show();
    }

    /*public int index = -1;
    public int index_a = 0;
    public void sendPost(*//*String url, RequestBody body*//*){
        Entity.executorService.execute(new Runnable() {
            @Override
            public void run() {
                String[] items = {"59000001", "59000002", "59000003", "59000004", "59000005", "59000006", "59000007", "59000008", "59000009", "59000010",
                        "59000011", "59000012", "59000013", "59000014","59000015","59000016","59000017","59000018","59000019","59000020","59000021",
                        "59000022","59000023","59000024","59000025","59000026","59000027","59000028","59000029","59000030","59000031","59000031",
                        "59000032","59000033","59000034","59000035","59000036","59000037","59000038","59000039","59000040","59000041","59000042",
                        "59000042","59000043","59000044","59000045","59000046","59000047","59000048","59000049","59000050","59000051","59000052",
                        "59000053","59000055","59000056","59000057","59000058","59000059","59000060","59000061","59000062", "59000063", "59000064",
                        "59000065", "59000066", "59000067", "59000068", "59000069", "59000070", "59000071", "59000072", "59000073", "59000114", "59000115",
                        "59000116", "59000117", "59000118", "59000119", "59000120", "59000121", "59000122", "59000123", "59000124", "59000125", "59000126",
                        "59000127", "59000128", "59000129", "59000130", "59000131", "59000132", "59000133", "59000134", "59000135", "59000140", "59000141",
                        "59000142", "59000143", "59000144", "59000145", "59000146", "59000147", "59000148", "59000149", "59000155", "59000156", "59000157",
                        "59000158", "59000159", "59000160", "59000161", "59000162", "59000163", "59000164", "59000165", "59000166", "59000167", "59000168",
                        "59000169", "59000185", "59000186", "59000187", "59000188", "59000189", "59000190", "59000191", "59000192", "59000193", "59000194",
                        "59000195", "59000196", "59000197", "59000198", "59000199", "59000200", "59000201", "59000202", "59000215", "59000216", "59000217",
                        "59000218", "59000219", "59000220", "59000221", "59000222", "59000223", "59000224", "59000225", "59000226", "59000227", "59000228",
                        "59001114", "59001115", "59001116", "59001117", "59001118", "59001119", "59001120", "59001121", "59001122", "59001123", "59001124"};
                String url = "http://114.67.71.150:81/sqht/admin/gmquery.php";
                do {
                    index_a++;
                    RequestBody body = new FormBody.Builder()
                            .add("type", "mail")
                            .add("uid", "37371905")
                            .add("checknum", "c3")
                            .add("qu", "1")
                            .add("num", "1")
                            .add("item", items[index_a])
                            .build();
                    okHttpClient = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .post(body)
                            .build();
                    Call call = okHttpClient.newCall(request);
                    String result = "";
                    try {
                        Response response = call.execute();
                        result = response.body().string();
                        JSONObject object = new JSONObject(result);
                        index = object.getInt("errcode");

                        if (index == 1)
                        {
                            index = 0;
                        }
                        if (index_a >= items.length - 1)
                        {
                            index = -1;
                        }
                        //System.out.println(response.body().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.e("Result", result);// result;
                }
                while (index == 0);
            }
        });
    }*/
}
