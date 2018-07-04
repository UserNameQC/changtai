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
import com.changtai.Utils.Entity;
import com.changtai.Utils.SaveDataToFile;
import com.changtai.databinding.ActivityMainUserBinding;

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

public class MainUser extends Activity implements View.OnClickListener{

    /**
     * 管理员
     */
    private EditText etMain, etIsMain, etContact, etUserName, etPassWord, etResetPword, etPhoneNum, etIdent;
    private Button ok;
    private Map<String, String> etMap = new HashMap<>();
    public Map<Integer, EditText> editMap = new HashMap<>();
    public String[] key = {AdministratorId, SysAdmin,Name,Accounts,PassWord,ReWritePassWord,Phone,RoleId};
    public ActivityMainUserBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_user);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_user_lv);
        initView();
    }

    public void initView()
    {
        //etMain = (EditText) findViewById(R.id.mu_logo);
        etIsMain = (EditText) findViewById(R.id.mu_system_admin);
        etContact = (EditText) findViewById(R.id.mu_contact);//联系人
        etUserName = (EditText) findViewById(R.id.mu_user_name);
        etPassWord = (EditText) findViewById(R.id.mu_password);
        etResetPword = (EditText) findViewById(R.id.mu_reset_pw);
        etPhoneNum = (EditText) findViewById(R.id.mu_phone);
        etIdent = (EditText) findViewById(R.id.mu_role_identification);//角色标识
        ok = (Button) findViewById(R.id.mu_ok);
        ok.setOnClickListener(this);

        editMap.put(0, etMain);
        editMap.put(1, etIsMain);
        editMap.put(2, etContact);
        editMap.put(3, etUserName);
        editMap.put(4, etPassWord);
        editMap.put(5, etResetPword);
        editMap.put(6, etPhoneNum);
        editMap.put(7, etIdent);
    }

    @Override
    public void onClick(View view) {
        if (!Entity.editTextIsNull(editMap))
        {
            final StringBuilder builder = new StringBuilder("");
            for (int i = 0; i < editMap.size(); i++) {
                builder.append(key[i]).append(Entity.MID).append(editMap.get(i).getText().toString()).append(Entity.END);
            }
            Entity.executorService.execute(new Runnable() {
                @Override
                public void run() {
                    SaveDataToFile saveFile = new SaveDataToFile();
                    saveFile.saveFile("administrator", builder.toString(), 1);
                }
            });
        }
        else
        {
            Entity.toastMsg(MainUser.this, "输入不能为空");
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
