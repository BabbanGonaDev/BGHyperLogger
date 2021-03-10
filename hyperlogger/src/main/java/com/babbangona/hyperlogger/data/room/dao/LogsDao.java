package com.babbangona.hyperlogger.data.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.babbangona.hyperlogger.data.room.entities.LogsTable;

import java.util.List;

@Dao
public interface LogsDao {

    @Query(" SELECT * FROM logs_table WHERE sync_flag != '1' ")
    List<LogsTable> getUnsyncedLogs();

    @Query(" SELECT * FROM logs_table")
    List<LogsTable> getAll();

    @Query("UPDATE logs_table SET sync_flag = '1' WHERE log_id = :log_id ")
    void updateSyncFlag(String log_id);


    /**
     * Insert the object in database
     *
     * @param activityList, object to be inserted
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LogsTable activityList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<LogsTable> activityList);

    /**
     * update the object in database
     *
     * @param activityList, object to be updated
     */
    @Update
    void update(LogsTable activityList);

    /**
     * delete the object from database
     *
     * @param activityList, object to be deleted
     */
    @Delete
    void delete(LogsTable activityList);

}
