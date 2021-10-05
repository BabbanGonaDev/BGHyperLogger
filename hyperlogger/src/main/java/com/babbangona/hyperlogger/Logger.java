package com.babbangona.hyperlogger;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.util.Log;

import com.babbangona.hyperlogger.data.sharedprefs.SharedPrefs;

import java.util.Objects;

public class Logger {
    Application application;
    private String staffId;
    private String appVersion;
    private String sessionId;
    private String deviceName;
    private int deviceOsVersion;
    private String deviceImei;
    private String deviceManufacturer;
    private String buildType;
    private static boolean isInitialized = false;

    private SharedPrefs prefs;
    private Activity currentActivity;

    /*
    - Design a system that depends on the context of every activity. That is, they have to call "Hyperlogger.init(this)" inside every activity in order to use it's functions.
     */
    private Logger(Builder builder) {
        if (!Logger.isInitialized) {
            //Set values
            this.staffId = builder.staffId;
            this.appVersion = builder.appVersion;
            this.sessionId = builder.sessionId;
            this.deviceImei = builder.deviceImei;
            this.deviceOsVersion = builder.deviceOsVersion;
            this.deviceName = builder.deviceName;
            this.deviceManufacturer = builder.deviceManufacturer;
            this.buildType = builder.buildType;
            this.application = builder.application;

            //Save variables to sharedPrefs
            prefs = new SharedPrefs(application.getApplicationContext());
            prefs.putValue(SharedPrefs.STAFF_ID, staffId);
            prefs.putValue(SharedPrefs.APP_VERSION, appVersion);
            prefs.putValue(SharedPrefs.IMEI, deviceImei);
            prefs.putValue(SharedPrefs.SESSION_ID, sessionId);
            prefs.putValue(SharedPrefs.OS_VERSION, deviceOsVersion);
            prefs.putValue(SharedPrefs.DEVICE_MANUFACTURER, deviceManufacturer);
            prefs.putValue(SharedPrefs.DEVICE_NAME, deviceName);

            //Set a boolean variable to confirm that the build has been initialized
            Logger.isInitialized = true;

        }
    }

    //Empty constructor
    private Logger(Activity activity) {
        this.currentActivity = activity;
    }

    public static Logger getInstance(Activity activity) {
        if (!Logger.isInitialized) {
            throw new RuntimeException("Hyperlogger is not initialized");
        }

        return new Logger(activity);
    }

    /**
     * Used to log debug messages to the database
     */
    public void debug(String message) {
        if (buildType.equals("debug"))
            Log.d(currentActivity.getLocalClassName(), message);

        //TODO: Save log to database.
    }

    public void error(String message) {

    }

    public void warn(String message) {

    }

    public void info(String message) {

    }

    //Builder class
    public static class Builder {
        Application application;
        private String staffId;
        private String appVersion;
        private String sessionId;
        private String deviceName;
        private int deviceOsVersion;
        private String deviceImei;
        private String deviceManufacturer;
        private String buildType;

        Utils utils;

        public Builder(Application application) {
            this.application = application;
            utils = new Utils(application.getApplicationContext());
        }

        public Builder setAppVersion(String version) {
            this.appVersion = Objects.requireNonNull(version, "Kindly specify the app version");
            return this;
        }

        public Builder setStaffId(String staffId) {
            this.staffId = Objects.requireNonNull(staffId, "Kindly specify the logged in user's staff id");
            return this;
        }

        public Builder setBuildType(String buildType) {
            this.buildType = Objects.requireNonNull(buildType, "Kindly specify your application's build type");
            return this;
        }

        public Logger startSession() {
            this.deviceName = Build.MODEL;
            this.deviceOsVersion = Build.VERSION.SDK_INT;
            this.deviceManufacturer = Build.MANUFACTURER;
            this.sessionId = utils.generateSessionId();
            this.deviceImei = utils.getDeviceImei();

            return new Logger(this);
        }
    }
}
