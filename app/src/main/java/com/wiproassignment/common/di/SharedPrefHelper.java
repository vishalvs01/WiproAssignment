package com.wiproassignment.common.di;

import android.content.SharedPreferences;

public class SharedPrefHelper {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPrefHelper(SharedPreferences sharedPreferences, SharedPreferences.Editor editor) {
        this.sharedPreferences = sharedPreferences;
        this.editor = editor;
    }

    public void putString(String key, String data) {
        editor.putString(key, data).apply();
    }

    public void putInt(String key, int data) {
        editor.putInt(key, data).apply();
    }

    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

}
