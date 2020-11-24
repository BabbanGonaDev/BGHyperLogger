package com.example.hyperlogger.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;


import com.example.hyperlogger.Database.Entities.HyperLoggerTable;

import java.util.List;

@Dao
public interface HyperLoggerDao {


    /**
     * Insert the object in database
     * @param activityList, object to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(HyperLoggerTable activityList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<HyperLoggerTable> activityList);

    /**
     * update the object in database
     * @param activityList, object to be updated
     */
    @Update
    void update(HyperLoggerTable activityList);

    /**
     * delete the object from database
     * @param activityList, object to be deleted
     */
    @Delete
    void delete(HyperLoggerTable activityList);

}
