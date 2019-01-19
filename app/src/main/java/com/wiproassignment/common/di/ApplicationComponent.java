package com.wiproassignment.common.di;

import android.app.Application;
import android.content.SharedPreferences;

import com.wiproassignment.common.ApiService;
import com.wiproassignment.common.db.DatabaseManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, DBModule.class, SharedPrefModule.class})
public interface ApplicationComponent {

    Application getApplication();

    ApiService getRetrofit();

    DatabaseManager getDatabaseManager();

    SharedPreferences getSharedPref();

    SharedPreferences.Editor getPrefEditor();

}

