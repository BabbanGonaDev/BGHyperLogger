package com.example.hyperlogger.Database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.hyperlogger.Database.DatabaseStringConstants;
import com.example.hyperlogger.Database.Entities.HyperLoggerTable;

import java.util.List;

@Dao
public interface HyperLoggerDao {

    @Query(" SELECT * FROM "+ DatabaseStringConstants.LOG_TABLE +" where sync_flag != '1' ")
    List<HyperLoggerTable>  getUnsyncedLogs();

    @Query(" SELECT * FROM "+ DatabaseStringConstants.LOG_TABLE +"  ")
    List<HyperLoggerTable>  getAll();

    @Query(" update "+ DatabaseStringConstants.LOG_TABLE +" set sync_flag = '1'  where log_id = :log_id ")
    String  updateSyncFlag(String log_id);


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
