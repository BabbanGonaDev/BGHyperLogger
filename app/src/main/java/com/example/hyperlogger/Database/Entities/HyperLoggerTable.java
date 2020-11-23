package com.example.hyperlogger.Database.Entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.example.hyperlogger.Database.DatabaseStringConstants;

@Entity(primaryKeys = {DatabaseStringConstants.COL_LOG_ID},
        tableName = DatabaseStringConstants.LOG_TABLE)
public class HyperLoggerTable {

    @ColumnInfo(name = DatabaseStringConstants.COL_LOG_ID)
    @NonNull
    private String activity_id;

    @ColumnInfo(name = DatabaseStringConstants.COL_LOG_TYPE)
    @NonNull
    private String log_type;

    @ColumnInfo(name = DatabaseStringConstants.COL_LOG_MESSAGE)
    private String log_message;

    @ColumnInfo(name = DatabaseStringConstants.COL_TAG)
    private String tag;

    @ColumnInfo(name = DatabaseStringConstants.COL_PHONE_NAME)
    private String phone_name;

    @ColumnInfo(name = DatabaseStringConstants.COL_IMEI)
    private String imei;

    @ColumnInfo(name = DatabaseStringConstants.COL_STAFF_ID)
    private String staff_id;

    @ColumnInfo(name = DatabaseStringConstants.COL_APPLICATION_NAME)
    private String application_name;

    @ColumnInfo(name = DatabaseStringConstants.COL_APPLICATION_VERSION)
    private String application_version;

    @ColumnInfo(name = DatabaseStringConstants.COL_TIME_STAMP)
    private String time_stamp;

    public HyperLoggerTable(@NonNull String activity_id, @NonNull String log_type, String log_message,
                            String tag, String phone_name, String imei, String staff_id, String application_name,
                            String application_version, String time_stamp) {
        this.activity_id = activity_id;
        this.log_type = log_type;
        this.log_message = log_message;
        this.tag = tag;
        this.phone_name = phone_name;
        this.imei = imei;
        this.staff_id = staff_id;
        this.application_name = application_name;
        this.application_version = application_version;
        this.time_stamp = time_stamp;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPhone_name() {
        return phone_name;
    }

    public void setPhone_name(String phone_name) {
        this.phone_name = phone_name;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getApplication_name() {
        return application_name;
    }

    public void setApplication_name(String application_name) {
        this.application_name = application_name;
    }

    public String getApplication_version() {
        return application_version;
    }

    public void setApplication_version(String application_version) {
        this.application_version = application_version;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }
}
