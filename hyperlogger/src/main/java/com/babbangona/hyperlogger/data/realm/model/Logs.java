package com.babbangona.hyperlogger.data.realm.model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass(name = "logs")
public class Logs extends RealmObject {

    @PrimaryKey
    private String unique_id;
    private String session_id;
    private String log_type;
    private String log_name;
    private String log_message;
    private String build_type;
    private String app_version;
    private String timestamp;
    private int sync_flag;

    public Logs(String session_id, String log_type, String log_name, String log_message, String build_type, String app_version, String timestamp, int sync_flag) {
        this.unique_id = UUID.randomUUID().toString();
        this.session_id = session_id;
        this.log_type = log_type;
        this.log_name = log_name;
        this.log_message = log_message;
        this.build_type = build_type;
        this.app_version = app_version;
        this.timestamp = timestamp;
        this.sync_flag = sync_flag;
    }

    public Logs() {}

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getLog_type() {
        return log_type;
    }

    public void setLog_type(String log_type) {
        this.log_type = log_type;
    }

    public String getLog_name() {
        return log_name;
    }

    public void setLog_name(String log_name) {
        this.log_name = log_name;
    }

    public String getLog_message() {
        return log_message;
    }

    public void setLog_message(String log_message) {
        this.log_message = log_message;
    }

    public String getBuild_type() {
        return build_type;
    }

    public void setBuild_type(String build_type) {
        this.build_type = build_type;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getSync_flag() {
        return sync_flag;
    }

    public void setSync_flag(int sync_flag) {
        this.sync_flag = sync_flag;
    }
}
