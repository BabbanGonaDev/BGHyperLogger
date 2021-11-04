package com.babbangona.hyperlogger;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.util.Log;

import com.babbangona.hyperlogger.data.realm.HyperloggerDb;
import com.babbangona.hyperlogger.data.realm.model.Logs;
import com.babbangona.hyperlogger.data.sharedprefs.SharedPrefs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
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
    private static HyperloggerDb db;

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

            //Confirm variables
            Log.d("CHECK", "StaffID: " + staffId);
            Log.d("CHECK", "Session ID: " + sessionId);
            Log.d("CHECK", "Build Type: " + buildType);

            //Save variables to sharedPrefs
            prefs = new SharedPrefs(application.getApplicationContext());
            prefs.putValue(SharedPrefs.STAFF_ID, staffId);
            prefs.putValue(SharedPrefs.APP_VERSION, appVersion);
            prefs.putValue(SharedPrefs.IMEI, deviceImei);
            prefs.putValue(SharedPrefs.SESSION_ID, sessionId);
            prefs.putValue(SharedPrefs.OS_VERSION, deviceOsVersion);
            prefs.putValue(SharedPrefs.DEVICE_MANUFACTURER, deviceManufacturer);
            prefs.putValue(SharedPrefs.DEVICE_NAME, deviceName);
            prefs.putValue(SharedPrefs.BUILD_TYPE, buildType);

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

        db = new HyperloggerDb(activity);
        db.open();

        return new Logger(activity);
    }

    /**
     * Used to log debug messages
     *
     * @param message
     */
    public void debug(String message) {
        if (buildType == "debug")
            Log.d(currentActivity.getLocalClassName(), message);

        insertLog(message, LogType.DEBUG);
    }

    /**
     * Used to log error messages
     *
     * @param message
     */
    public void error(String message) {
        if (buildType == "debug")
            Log.d(currentActivity.getLocalClassName(), message);

        insertLog(message, LogType.ERROR);
    }

    /**
     * Used to log warnings
     *
     * @param message
     */
    public void warn(String message) {
        if (buildType == "debug")
            Log.d(currentActivity.getLocalClassName(), message);

        insertLog(message, LogType.WARN);
    }

    /**
     * Used to log actions
     *
     * @param message
     */
    public void action(String message) {
        insertLog(message, LogType.ACTION);
    }

    /**
     * Used to log caught exceptions
     *
     * @param message
     */
    public void exception(String message) {
        insertLog(message, LogType.EXCEPTION);
    }

    /**
     * Used to log network actions.
     *
     * @param message
     */
    public void network(String message) {
        insertLog(message, LogType.NETWORK);
    }

    /**
     * Used to log information
     *
     * @param message
     */
    public void info(String message) {
        if (buildType == "debug")
            Log.d(currentActivity.getLocalClassName(), message);

        insertLog(message, LogType.INFO);
    }

    public void insertLog(String message, LogType logType) {
        prefs = new SharedPrefs(currentActivity);

        Logs _log = new Logs(prefs.getValue(String.class, SharedPrefs.SESSION_ID),
                logType.name(),
                "",
                message,
                prefs.getValue(String.class, SharedPrefs.BUILD_TYPE),
                prefs.getValue(String.class, SharedPrefs.APP_VERSION),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()),
                0);

        db.addLogs(_log);
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
