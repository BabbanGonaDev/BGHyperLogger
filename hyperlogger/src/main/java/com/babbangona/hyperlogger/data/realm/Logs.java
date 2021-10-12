package com.babbangona.hyperlogger.data.realm;

import com.babbangona.hyperlogger.LogType;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Logs extends RealmObject {

    @PrimaryKey
    private String unique_id;
    private String session_id;
    private LogType log_type;
    private String log_name;
    private String log_message;
    private String build_type;
    private String app_version;
    private String timestamp;

    public Logs(String session_id, LogType log_type, String log_name, String log_message, String build_type, String app_version, String timestamp) {
        this.unique_id = UUID.randomUUID().toString();
        this.session_id = session_id;
        this.log_type = log_type;
        this.log_name = log_name;
        this.log_message = log_message;
        this.build_type = build_type;
        this.app_version = app_version;
        this.timestamp = timestamp;
    }

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

    public LogType getLog_type() {
        return log_type;
    }

    public void setLog_type(LogType log_type) {
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
}
