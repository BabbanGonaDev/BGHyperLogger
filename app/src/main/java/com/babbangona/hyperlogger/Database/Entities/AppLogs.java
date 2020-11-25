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
    private String activity_id;

    @ColumnInfo(name = DatabaseStringConstants.COL_LOG_TYPE)
    @NonNull
    private String log_type;

    @ColumnInfo(name = DatabaseStringConstants.COL_LOG_MESSAGE)
    private String log_message;

    @ColumnInfo(name = DatabaseStringConstants.COL_TIME_STAMP)
    private String time_stamp;


    @ColumnInfo(name = DatabaseStringConstants.COL_SYNC_FLAG)
    private String sync_flag;

    public AppLogs(@NonNull String activity_id, @NonNull String log_type, String log_message, String time_stamp, String sync_flag) {
        this.activity_id = activity_id;
        this.log_type = log_type;
        this.log_message = log_message;
        this.time_stamp = time_stamp;
        this.sync_flag = sync_flag;
    }

    @NonNull
    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(@NonNull String activity_id) {
        this.activity_id = activity_id;
    }

    @NonNull
    public String getLog_type() {
        return log_type;
    }

    public void setLog_type(@NonNull String log_type) {
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
