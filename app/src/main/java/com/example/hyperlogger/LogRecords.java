package com.example.hyperlogger;

import android.content.Context;

import com.example.hyperlogger.Database.AppDatabase;
import com.example.hyperlogger.Database.Entities.AppLogs;


public class LogRecords implements LogRecordsHelper {


    @Override
    public String captureLogs(Context context, String log_type, String log_message, String date) {

        AppLogs appLogs = new AppLogs("id",log_type,log_message,date,"0");
        AppDatabase appDatabase =  AppDatabase.getInstance(context);
        appDatabase.appLogsDao().insert(appLogs);
        return "1";
    }

    @Override
    public String getLogs(Context context) {

        AppDatabase appDatabase =  AppDatabase.getInstance(context);
        return appDatabase.appLogsDao().countActivities();

    }

    @Override
    public String captureAuditLogs(Context context,String log_type, String log_message, String tag, String phone_name, String imei, String staff_id, String application_name, String application_version, String time_stamp) {
        return null;
    }

}
