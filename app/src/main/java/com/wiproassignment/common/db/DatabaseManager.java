package com.wiproassignment.common.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.wiproassignment.common.db.dao.InfoDao;
import com.wiproassignment.common.db.entity.InfoEntity;

@Database(entities = InfoEntity.class, version = 1,exportSchema =  false)
public abstract class DatabaseManager extends RoomDatabase {

    public abstract InfoDao getInfoDao();

}
