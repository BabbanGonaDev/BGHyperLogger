package com.babbangona.hyperlogger.Database.Dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.babbangona.hyperlogger.Database.DatabaseStringConstants;
import com.babbangona.hyperlogger.Database.Entities.AppLogs;

import java.util.List;

@Dao

public interface AppLogsDao {


    @Query(" SELECT COUNT(*) FROM "+ DatabaseStringConstants.GENERAL_LOG_TABLE +" ")
    String  countActivities();

    @Query(" SELECT * FROM "+ DatabaseStringConstants.GENERAL_LOG_TABLE +" where sync_flag != '1' ")
    List<AppLogs>  getUnSyncedLogs();

    @Query(" SELECT * FROM "+ DatabaseStringConstants.GENERAL_LOG_TABLE +" ")
    List<AppLogs>  getAllRecords();


    @Query(" update "+ DatabaseStringConstants.GENERAL_LOG_TABLE +" set sync_flag = '1'  where log_id = :log_id ")
    void updateSyncFlag(String log_id);



    /**
     * Insert the object in database
     * @param activityList, object to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AppLogs activityList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<AppLogs> activityList);

    /**
     * update the object in database
     * @param activityList, object to be updated
     */
    @Update
    void update(AppLogs activityList);

    /**
     * delete the object from database
     * @param activityList, object to be deleted
     */
    @Delete
    void delete(AppLogs activityList);


}
