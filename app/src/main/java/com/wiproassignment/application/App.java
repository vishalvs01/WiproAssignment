package com.wiproassignment.application;

import android.app.Application;

import com.wiproassignment.BuildConfig;
import com.wiproassignment.common.di.AppModule;
import com.wiproassignment.common.di.ApplicationComponent;
import com.wiproassignment.common.di.DaggerApplicationComponent;
import com.wiproassignment.common.di.NetworkModule;

public class App extends Application {

    private static ApplicationComponent component;

    public static ApplicationComponent getApplicationComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder().networkModule(new NetworkModule(BuildConfig.HOST))
                .appModule(new AppModule(this)).build();
    }
}
