package com.wiproassignment.common.db.dao;

import android.app.Application;

import com.wiproassignment.BuildConfig;
import com.wiproassignment.common.di.AppModule;
import com.wiproassignment.common.di.ApplicationComponent;
import com.wiproassignment.common.di.DBModule;
import com.wiproassignment.common.di.DaggerApplicationComponent;
import com.wiproassignment.common.di.NetworkModule;
import com.wiproassignment.common.di.SharedPrefModule;

public class App extends Application {

    private static ApplicationComponent component;

    public static ApplicationComponent getApplicationComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder().networkModule(new NetworkModule(BuildConfig.HOST))
                .appModule(new AppModule(this)).dBModule(new DBModule())
                .sharedPrefModule(new SharedPrefModule()).build();
    }
}
