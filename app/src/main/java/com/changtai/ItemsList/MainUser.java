package com.changtai.ItemsList;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.changtai.R;
import com.changtai.databinding.ActivityMainUserLvBinding;

import java.util.HashMap;
import java.util.Map;

import static com.changtai.Utils.Entity.Accounts;
import static com.changtai.Utils.Entity.AdministratorId;
import static com.changtai.Utils.Entity.Name;
import static com.changtai.Utils.Entity.PassWord;
import static com.changtai.Utils.Entity.Phone;
import static com.changtai.Utils.Entity.ReWritePassWord;
import static com.changtai.Utils.Entity.RoleId;
import static com.changtai.Utils.Entity.SysAdmin;
import static com.changtai.Utils.Entity.editIsNull;

public class MainUser extends Activity{

    /**
     * 管理员
     */
    private EditText etMain, etIsMain, etContact, etUserName, etPassWord, etResetPword, etPhoneNum, etIdent;
    private Button ok;
    private Map<String, String> etMap = new HashMap<>();
    public Map<Integer, EditText> editMap = new HashMap<>();
    public String[] key = {AdministratorId, SysAdmin,Name,Accounts,PassWord,ReWritePassWord,Phone,RoleId};
    public ActivityMainUserLvBinding binding;
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
        binding.activityCreateAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
