package com.wiproassignment.common.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface ApplicationComponent {

    Application getApplication();

    Retrofit getRetrofit();

}

