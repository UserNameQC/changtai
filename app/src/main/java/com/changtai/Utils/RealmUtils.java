package com.changtai.Utils;

import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.Map;
/**
 * Created by qjcjo on 2018/4/12.
 */

public class  RealmUtils<T> {

    public static void setEditEnable(Map<Integer, EditText> map, boolean flag){
        for (int i = 0; i < map.size(); i++)
        {
            EditText et = map.get(i);
            et.setEnabled(flag);
        }
    }

    public static void setEditWatch(Map<Integer, EditText> map, TextWatcher textWatcher){
        for (int i = 0; i < map.size(); i++)
        {
            EditText et = map.get(i);
            et.addTextChangedListener(textWatcher);
        }
    }

    public static boolean isEmpty(String value){
        return TextUtils.isEmpty(value) || String.valueOf(value).equals("null");
    }
}
