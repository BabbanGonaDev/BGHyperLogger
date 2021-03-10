package com.babbangona.hyperlogger.data.room.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.babbangona.hyperlogger.data.room.entities.GeneralLogsTable;

import java.util.List;

@Dao

public interface GeneralLogsDao {


    @Query("SELECT COUNT(*) FROM general_logs_table")
    String countActivities();

    @Query("SELECT * FROM general_logs_table WHERE sync_flag != '1'")
    List<GeneralLogsTable> getUnSyncedLogs();

    @Query("SELECT * FROM general_logs_table")
    List<GeneralLogsTable> getAllRecords();

    @Query("UPDATE general_logs_table SET sync_flag = '1' WHERE log_id = :log_id ")
    void updateSyncFlag(String log_id);

    /**
     * Insert the object in database
     *
     * @param activityList, object to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(GeneralLogsTable activityList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<GeneralLogsTable> activityList);

    /**
     * update the object in database
     *
     * @param activityList, object to be updated
     */
    @Update
    void update(GeneralLogsTable activityList);

    /**
     * delete the object from database
     *
     * @param activityList, object to be deleted
     */
    @Delete
    void delete(GeneralLogsTable activityList);


}
