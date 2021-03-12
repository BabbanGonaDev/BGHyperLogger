package com.babbangona.hyperlogger;

import android.content.Context;

import org.json.JSONObject;

public interface LogRecordsHelper {


    /**
     * This method handles all the audit logs that are tracked as audited
     * it requires the consumer providing the context and then every other data point in this function
     */
    String captureAuditLogs(Context context, LogType log_type, String log_message, String tag, String phone_name,
                            String imei, String staff_id, String application_name, String application_version,
                            String package_name, String time_stamp);


    /**
     * This method handles all the general logs on the application
     * it requires the consumer providing the context and then every other data point in this function
     */
    String captureGeneralLogs(Context context, LogType log_type, String log_message, String package_name);

    /**
     *this getlogs function is for debugging, it returns the count of all the logs in the table
     * */
    String getLogs(Context context);

    /**
     * this controls whether a log would be synced to the server or not, its a controller for syncing operations in the library
     * */
    void triggerSync(Context context, int flag);

    /**
     *  this control writing a file to the to the storage that holds all audit logs
     * */
    void writeAuditLogsToFile(Context context, int flag);

    /**
     *  this control writing a file to the to the storage that holds all audit logs
     * */
    void writeGeneralLogsToFile(Context context, int flag);

    /**
     * this controls starting the auto sync class
     * @param context pass in context of the activity
     */
    //public void startAutoSyncClass(Context context);

    /**
     * this controls starting the mix_panel
     * @param context pass in context of the activity
     */
    void startMixPanelClass(Context context);

    /**
     * this controls stopping mix_panel
     */
    void stopMixPanelClass(Context context);

    /**
     * this controls stopping mix_panel
     */
    boolean checkMixPanelExitStatus(Context context);

    /**
     * this controls stopping syncing
     */
    //public void forceSync(Context context);

    /**
     * this controls method to get full memory parameters
     */
    String getMemoryParameters();

    /**
     * this controls method to get available memory
     */
    String getAvailableMemory();

    /**
     * this controls method to get ram utilization
     */
    String getRamUtilization();

    /**
     * this controls method to get memory usage
     */
    String getMemoryUsage();

    /**
     * this controls method to track items using json and a title
     */
    void mixPanelTracker(String tracking_title, JSONObject jsonObject, Context context);

    /**
     * this controls method to track items using a tracking title
     */
    void mixPanelTracker(String tracking_title, Context context);

    /**
     * this controls method
     */
    //public String getCPULoads();



}

