package com.changtai.ItemsList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.changtai.adapter.MainUserAdapter;
import com.changtai.R;
import com.changtai.activites.AddUserActivity;
import com.changtai.application.MyApplication;
import com.changtai.databinding.ActivityMainUserLvBinding;
import com.changtai.sqlModel.LoginModel;
import com.example.john.greendaodemo.gen.LoginModelDao;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.changtai.Utils.Entity.Accounts;
import static com.changtai.Utils.Entity.AdministratorId;
import static com.changtai.Utils.Entity.Name;
import static com.changtai.Utils.Entity.PassWord;
import static com.changtai.Utils.Entity.Phone;
import static com.changtai.Utils.Entity.ReWritePassWord;
import static com.changtai.Utils.Entity.RoleId;
import static com.changtai.Utils.Entity.SysAdmin;

public class MainUser extends Activity{

    /**
     * 管理员
     */
    private Button ok;
    private Map<String, String> etMap = new HashMap<>();
    public Map<Integer, EditText> editMap = new HashMap<>();
    public String[] key = {AdministratorId, SysAdmin,Name,Accounts,PassWord,ReWritePassWord,Phone,RoleId};
    public ActivityMainUserLvBinding binding;
    public LoginModelDao loginModelDao;
    public LoginModel loginModel;
    public MainUserAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_user_lv);
        initView();
    }

    public void initView()
    {
        //不需要初始化信息
        //直接使用 如下
        loginModelDao = MyApplication.getInstance().getDaoSession().getLoginModelDao();
        List<LoginModel> loginModels = loginModelDao.loadAll();
        if (loginModels != null && loginModels.size() > 0){
            arrayAdapter = new MainUserAdapter(this,loginModels, R.layout.main_user_item_layout);
            binding.activityMainUserLv.setAdapter(arrayAdapter);
            arrayAdapter.setItemClick(new MainUserAdapter.setOnItemClickListenr() {
                @Override
                public void onItemClick(View v, int position, final LoginModel loginModel) {
                    final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainUser.this);
                    alertDialog.setItems(new String[]{"修改信息", "删除操作员"}, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0){
                                Intent intent = new Intent(MainUser.this, AddUserActivity.class);
                                String json = new Gson().toJson(loginModel);
                                intent.putExtra("result", json);
                                startActivity(intent);
                                dialog.dismiss();
                            } else {
                                loginModelDao.delete(loginModel);
                                arrayAdapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        }
                    });
                    alertDialog.show();
                }
            });
        }

    }


    public class addTextW implements TextWatcher{
        public EditText et;
        public String tag;
        public addTextW(EditText et, String tag){
            this.et = et;
            this.tag = tag;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
