package com.wiproassignment.common.di;

import android.app.Application;

import com.wiproassignment.common.ApiService;
import com.wiproassignment.common.db.DatabaseManager;
import com.wiproassignment.common.schedulerprovider.BaseSchedulerProvider;
import com.wiproassignment.utils.NetworkUtil;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, DBModule.class, SharedPrefModule.class})
public interface ApplicationComponent {

    Application getApplication();

    ApiService getRetrofit();

    NetworkUtil getNetworkUtil();

    DatabaseManager getDatabaseManager();

    SharedPrefHelper getPrefHelper();

    BaseSchedulerProvider getSchedulerProvider();

}

