package com.example.hyperlogger;

import android.content.Context;

public interface LogRecordsHelper {


    /**
     * This method handles all the audit logs that are  tracked as audited
     * it requires the consumer providing the context and then every other data point in this function
     * */
    public String captureAuditLogs(Context context, String log_type, String log_message, String tag, String phone_name,
                                   String imei, String staff_id, String application_name, String application_version,
                                   String time_stamp);


    /**
     * This method handles all the general logs on the application
     * it requires the consumer providing the context and then every other data point in this function
     * */
    public String captureLogs(Context context,String log_type, String log_message);

    /**
     *this getlogs function is for debugging, it returns the count of all the logs in the table
     * */
    public String getLogs(Context context);

    /**
     * this controls whether a log would be synced to the server or not, its a controller for syncing operations in the library
     * */
    public void triggerSync(Context context, int flag);

    /**
     *  this control writing a file to the to the storage that holds all audit logs
     * */
    public void writeToFile(Context context, int flag);



}

