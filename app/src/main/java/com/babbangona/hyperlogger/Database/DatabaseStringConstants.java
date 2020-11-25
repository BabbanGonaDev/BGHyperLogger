package com.babbangona.hyperlogger.Database;

public class DatabaseStringConstants {

    /**
     * database particulars
     */
    public static final String MS_PLAYBOOK_DATABASE_NAME        = "hyper_logger.db";
    public static final int MS_PLAYBOOK_DATABASE_VERSION        = 1;


    public static final String LOG_TABLE                        = "log_table";
    public static final String GENERAL_LOG_TABLE                        = "general_log_table";

    /**
     * log_table particulars
     */
    public static final String COL_LOG_ID                       = "log_id";
    public static final String COL_LOG_TYPE                     = "log_type";
    public static final String COL_LOG_MESSAGE                  = "log_message";
    public static final String COL_TAG                          = "tag";
    public static final String COL_PHONE_NAME                   = "phone_name";
    public static final String COL_IMEI                         = "imei";
    public static final String COL_STAFF_ID                     = "staff_id";
    public static final String COL_APPLICATION_NAME             = "application_name";
    public static final String COL_APPLICATION_VERSION          = "application_version";
    public static final String COL_TIME_STAMP                   = "time_stamp";
    public static final String COL_SYNC_FLAG                    = "sync_flag";
}
