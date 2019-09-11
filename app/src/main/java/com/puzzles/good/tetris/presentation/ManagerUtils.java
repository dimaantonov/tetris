package com.puzzles.good.tetris.presentation;

import android.content.Context;
import android.content.SharedPreferences;

public class ManagerUtils {
    private static String data = "man";
    private SharedPreferences preferences;

    public ManagerUtils(Context context){
        String NAME = "manager";
        preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public void setData(String data){
        preferences.edit().putString(ManagerUtils.data, data).apply();
    }

    public String getData(){
        return preferences.getString(data, "");
    }
}
