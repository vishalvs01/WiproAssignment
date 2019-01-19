package com.wiproassignment.common.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.wiproassignment.common.db.DatabaseManager;
import com.wiproassignment.utils.ConstantUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DBModule {

    @Provides
    @Singleton
    DatabaseManager provideDatabase(Application context) {
        return Room.databaseBuilder(context, DatabaseManager.class, ConstantUtils.DB_NAME).fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }

}
