package com.wiproassignment.common.di;

import android.app.Application;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.wiproassignment.common.ApiService;
import com.wiproassignment.common.schedulerprovider.BaseSchedulerProvider;
import com.wiproassignment.common.schedulerprovider.SchedulerProvider;
import com.wiproassignment.utils.NetworkUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private String baseUrl;

    public NetworkModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        return client.build();
    }

    @Provides
    @Singleton
    public NetworkUtil provideNetworkUtil(Application context) {
        return new NetworkUtil(context);
    }

    @Singleton
    @Provides
    public RxJava2CallAdapterFactory provideClient() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    public GsonConverterFactory provideConverter() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    public Retrofit providerRetroFit(GsonConverterFactory converter, RxJava2CallAdapterFactory rxAdapter, OkHttpClient client) {
        return new Retrofit.Builder().
                baseUrl(baseUrl).
                client(client).
                addConverterFactory(converter).
                addCallAdapterFactory(rxAdapter).
                build();
    }

    @Singleton
    @Provides
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Singleton
    @Provides
    public BaseSchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider();
    }


}
