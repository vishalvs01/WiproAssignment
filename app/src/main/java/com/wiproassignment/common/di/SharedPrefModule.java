package com.wiproassignment.common.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.wiproassignment.utils.ConstantUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefModule {

    @Singleton
    @Provides
    public SharedPreferences provideSharePef(Application application) {
        return application.getSharedPreferences(ConstantUtils.PREF_NAME, Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    public SharedPreferences.Editor provideEditor(SharedPreferences sharedPreferences) {
        return sharedPreferences.edit();
    }


    @Singleton
    @Provides
    public SharedPrefHelper provideSharedPrefHelper(SharedPreferences sharedPreferences, SharedPreferences.Editor editor) {
        return new SharedPrefHelper(sharedPreferences, editor);
    }

}
