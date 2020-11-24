package com.example.hyperlogger;

import android.content.Context;

public interface LogRecordsHelper {


    public String captureAuditLogs(Context context, String log_type, String log_message, String tag, String phone_name,
                                   String imei, String staff_id, String application_name, String application_version,
                                   String time_stamp);

    public String captureLogs(Context context,String log_type, String log_message);

    public String getLogs(Context context);

    public void setBaseURL(Context context,String url, String script);

    public void triggerSync(Context context, int flag);

    public void writeToFile(Context context, int flag);



}

