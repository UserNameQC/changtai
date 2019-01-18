package com.changtai.Utils;

public class SpUtils {

    public SpUtils(){

    }

    public void setString(String key, String value){
        Entity.spres.edit().putString(key, value).apply();
    }

    public String getString(String key){
        return Entity.spres.getString(key, "");
    }

    public void setBoolean(String key, boolean value){
        Entity.spres.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key){
        return Entity.spres.getBoolean(key, false);
    }

    public void setInt(String key, int value){
        Entity.spres.edit().putInt(key, value).apply();
    }

    public int getInt(String key){
        return Entity.spres.getInt(key, 0xff);
    }
}
