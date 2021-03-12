package com.babbangona.hyperlogger.data.room.entities;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "logs_table")
public class LogsTable {

    @PrimaryKey
    @NonNull
    private String log_id;
    private String log_type;
    private String log_message;
    private String tag;
    private String phone_name;
    private String imei;
    private String staff_id;
    private String application_name;
    private String application_version;
    private String package_name;
    private String ram_utilization;
    private String memory_usage;
    private String time_stamp;
    private String sync_flag;

    public LogsTable(@NonNull String log_id, String log_type, String log_message, String tag, String phone_name, String imei, String staff_id, String application_name, String application_version, String package_name, String ram_utilization, String memory_usage, String time_stamp, String sync_flag) {
        this.log_id = log_id;
        this.log_type = log_type;
        this.log_message = log_message;
        this.tag = tag;
        this.phone_name = phone_name;
        this.imei = imei;
        this.staff_id = staff_id;
        this.application_name = application_name;
        this.application_version = application_version;
        this.package_name = package_name;
        this.ram_utilization = ram_utilization;
        this.memory_usage = memory_usage;
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

    public String getTag() {
        return tag;
    }

    public String getPhone_name() {
        return phone_name;
    }

    public String getImei() {
        return imei;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public String getApplication_name() {
        return application_name;
    }

    public String getApplication_version() {
        return application_version;
    }

    public String getPackage_name() {
        return package_name;
    }

    public String getRam_utilization() {
        return ram_utilization;
    }

    public String getMemory_usage() {
        return memory_usage;
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

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setPhone_name(String phone_name) {
        this.phone_name = phone_name;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public void setApplication_name(String application_name) {
        this.application_name = application_name;
    }

    public void setApplication_version(String application_version) {
        this.application_version = application_version;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public void setRam_utilization(String ram_utilization) {
        this.ram_utilization = ram_utilization;
    }

    public void setMemory_usage(String memory_usage) {
        this.memory_usage = memory_usage;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public void setSync_flag(String sync_flag) {
        this.sync_flag = sync_flag;
    }
}
