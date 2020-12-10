package com.babbangona.hyperlogger.Database.Entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.babbangona.hyperlogger.Database.DatabaseStringConstants;


@Entity(primaryKeys = {DatabaseStringConstants.COL_LOG_ID},
        tableName = DatabaseStringConstants.LOG_TABLE)
public class HyperLoggerTable {

    @ColumnInfo(name = DatabaseStringConstants.COL_LOG_ID)
    @NonNull
    private String log_id;

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

    @ColumnInfo(name = DatabaseStringConstants.COL_SYNC_FLAG)
    private String sync_flag;

    @ColumnInfo(name = DatabaseStringConstants.COL_RAM_UTILIZATION)
    private String ram_utilization;

    @ColumnInfo(name = DatabaseStringConstants.COL_MEMORY_USAGE)
    private String memory_usage;

    public HyperLoggerTable(@NonNull String log_id, @NonNull String log_type, String log_message,
                            String tag, String phone_name, String imei, String staff_id, String application_name,
                            String application_version, String time_stamp, String sync_flag, String ram_utilization,
                            String memory_usage) {
        this.log_id = log_id;
        this.log_type = log_type;
        this.log_message = log_message;
        this.tag = tag;
        this.phone_name = phone_name;
        this.imei = imei;
        this.staff_id = staff_id;
        this.application_name = application_name;
        this.application_version = application_version;
        this.time_stamp = time_stamp;
        this.sync_flag = sync_flag;
        this.ram_utilization = ram_utilization;
        this.memory_usage = memory_usage;
    }


    @NonNull
    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(@NonNull String log_id) {
        this.log_id = log_id;
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

    public String getSync_flag() {
        return sync_flag;
    }

    public void setSync_flag(String sync_flag) {
        this.sync_flag = sync_flag;
    }

    public String getRam_utilization() {
        return ram_utilization;
    }

    public void setRam_utilization(String ram_utilization) {
        this.ram_utilization = ram_utilization;
    }

    public String getMemory_usage() {
        return memory_usage;
    }

    public void setMemory_usage(String memory_usage) {
        this.memory_usage = memory_usage;
    }
}
