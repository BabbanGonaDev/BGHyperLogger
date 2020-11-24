package com.example.hyperlogger;

import android.content.Context;

public interface LogRecordsHelper {


    public String captureAuditLogs(Context context, String log_type, String log_message, String tag, String phone_name,
                                   String imei, String staff_id, String application_name, String application_version,
                                   String time_stamp);

    public String captureLogs(Context context,String log_type, String log_message, String date);

    public String getLogs(Context context);




}

