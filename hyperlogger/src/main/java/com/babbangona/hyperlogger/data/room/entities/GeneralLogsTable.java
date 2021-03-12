package com.babbangona.hyperlogger.data.room.entities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "general_logs_table")
public class GeneralLogsTable {

    @NonNull
    @PrimaryKey
    private String log_id;
    private String log_type;
    private String log_message;
    private String package_name;
    private String time_stamp;
    private String sync_flag;

    public GeneralLogsTable(@NonNull String log_id, String log_type, String log_message, String package_name, String time_stamp, String sync_flag) {
        this.log_id = log_id;
        this.log_type = log_type;
        this.log_message = log_message;
        this.package_name = package_name;
        this.time_stamp = time_stamp;
        this.sync_flag = sync_flag;
    }

    @NonNull
    public String getLog_id() {
        return log_id;
    }

    public String getLog_type() {
        return log_type;
    }

    public String getLog_message() {
        return log_message;
    }

    public String getPackage_name() {
        return package_name;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public String getSync_flag() {
        return sync_flag;
    }

    public void setLog_id(@NonNull String log_id) {
        this.log_id = log_id;
    }

    public void setLog_type(String log_type) {
        this.log_type = log_type;
    }

    public void setLog_message(String log_message) {
        this.log_message = log_message;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public void setSync_flag(String sync_flag) {
        this.sync_flag = sync_flag;
    }
}
