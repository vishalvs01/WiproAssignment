package com.wiproassignment.common.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.wiproassignment.common.db.entity.InfoEntity;

import java.util.List;

@Dao
public interface InfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInfoList(List<InfoEntity> infoList);

    @Query("SELECT * FROM infoTable ORDER BY id ASC")
    LiveData<List<InfoEntity>> getInfoList();

    @Query("DELETE FROM infoTable")
    void clearInfoList();

}
