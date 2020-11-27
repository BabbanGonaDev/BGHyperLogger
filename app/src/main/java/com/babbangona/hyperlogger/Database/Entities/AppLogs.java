package com.babbangona.hyperlogger.Database.Entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.babbangona.hyperlogger.Database.DatabaseStringConstants;


@Entity(primaryKeys = {DatabaseStringConstants.COL_LOG_ID},
        tableName = DatabaseStringConstants.GENERAL_LOG_TABLE)
public class AppLogs {

    @ColumnInfo(name = DatabaseStringConstants.COL_LOG_ID)
    @NonNull
    private String log_id;

    @ColumnInfo(name = DatabaseStringConstants.COL_LOG_TYPE)
    private String log_type;

    @ColumnInfo(name = DatabaseStringConstants.COL_LOG_MESSAGE)
    private String log_message;

    @ColumnInfo(name = DatabaseStringConstants.COL_TIME_STAMP)
    private String time_stamp;


    @ColumnInfo(name = DatabaseStringConstants.COL_SYNC_FLAG)
    private String sync_flag;

    public AppLogs(@NonNull String log_id, String log_type, String log_message, String time_stamp, String sync_flag) {
        this.log_id = log_id;
        this.log_type = log_type;
        this.log_message = log_message;
        this.time_stamp = time_stamp;
        this.sync_flag = sync_flag;
    }

    @NonNull
    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(@NonNull String log_id) {
        this.log_id = log_id;
    }

    public String getLog_type() {
        return log_type;
    }

    public void setLog_type(String log_type) {
        this.log_type = log_type;
    }

    public String getLog_message() {
        return log_message;
    }

    public void setLog_message(String log_message) {
        this.log_message = log_message;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getSync_flag() {
        return sync_flag;
    }

    public void setSync_flag(String sync_flag) {
        this.sync_flag = sync_flag;
    }
}
