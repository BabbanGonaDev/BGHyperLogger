package com.babbangona.hyperlogger.data.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Sessions extends RealmObject {

    @PrimaryKey
    private String session_id;
    private String staff_id;
    private String session_start;
    private String session_end;
    private String device_name;
    private String device_imei;
    private String os_version;
    private String manufacturer;

    public Sessions(String session_id, String staff_id, String session_start, String session_end, String device_name, String device_imei, String os_version, String manufacturer) {
        this.session_id = session_id;
        this.staff_id = staff_id;
        this.session_start = session_start;
        this.session_end = session_end;
        this.device_name = device_name;
        this.device_imei = device_imei;
        this.os_version = os_version;
        this.manufacturer = manufacturer;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getSession_start() {
        return session_start;
    }

    public void setSession_start(String session_start) {
        this.session_start = session_start;
    }

    public String getSession_end() {
        return session_end;
    }

    public void setSession_end(String session_end) {
        this.session_end = session_end;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_imei() {
        return device_imei;
    }

    public void setDevice_imei(String device_imei) {
        this.device_imei = device_imei;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
