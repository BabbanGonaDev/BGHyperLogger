package com.babbangona.hyperlogger;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.babbangona.hyperlogger.Database.AppDatabase;
import com.babbangona.hyperlogger.Database.Entities.AppLogs;
import com.babbangona.hyperlogger.Database.Entities.HyperLoggerTable;
import com.babbangona.hyperlogger.Database.sharedprefs.SharedPrefs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;


//this model class implements the functions in the interface class
public class LogRecords implements LogRecordsHelper {


    @Override
    public String captureLogs(Context context, String log_type, String log_message) {

        //insertion of logs for the general log tracking
        AppLogs appLogs = new AppLogs(random(),log_type,log_message,getDate("spread"),"0");
        AppDatabase appDatabase =  AppDatabase.getInstance(context);
        appDatabase.appLogsDao().insert(appLogs);


        //TODO please update this to the desired return, and also publish different return types in the interface class comment
        return "1";
    }

    @Override
    public String captureAuditLogs(Context context,String log_type, String log_message, String tag, String phone_name, String imei, String staff_id, String application_name, String application_version, String time_stamp) {

        //insertion of logs for the audit log tracking
        HyperLoggerTable hyperLoggerTable = new HyperLoggerTable(random(),log_type,log_message,tag,phone_name,imei,staff_id,application_name,application_version,getDate("spread"),"0");
        AppDatabase appDatabase =  AppDatabase.getInstance(context);
        appDatabase.hyperLoggerDao().insert(hyperLoggerTable);

        //TODO please update this to the desired return, and also publish different return types in the interface class comment
        return "1";
    }


    @Override
    public String getLogs(Context context) {

        //get log count from the table on the general log count
        AppDatabase appDatabase =  AppDatabase.getInstance(context);
        return appDatabase.appLogsDao().countActivities();

    }


    @Override
    public void triggerSync(Context context, int flag) {

        //hold the sync trigger flag in shared preference
        SharedPrefs sharedPrefs = new SharedPrefs(context);
        sharedPrefs.setSyncTrigger(flag);

    }

    @Override
    public void writeToFile(Context context, int flag) {

        //this block of code handles writing the logs to files
        //TODO enable permission for storage so the library does not crash the application
        List<AppLogs> appLogs = AppDatabase.getInstance(context).appLogsDao().getAllRecords();
        ArrayList<String> logs = new ArrayList<>();


        for (AppLogs mLogs: appLogs){
            logs.add("Log entry: "+mLogs.getActivity_id()+" "+mLogs.getLog_type()+" "+mLogs.getLog_message()+" "+mLogs.getSync_flag()+" "+mLogs.getTime_stamp()+"\n");
        }

        //function writing to external storage
        generateNoteOnSD(context,"AuditLogs.txt", logs.toString());
    }



    //TODO use this as one of the functions to generate the log ids
    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(20);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }

    //generate date
    private String getDate(String module){
        SimpleDateFormat dateFormat1;
        if (module.matches("concat")) {
            dateFormat1 = new SimpleDateFormat("yyMMddHHmmss", Locale.getDefault());
        }else if (module.matches("spread")) {
            dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        }else{
            dateFormat1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        }
        Date date1 = new Date();
        return dateFormat1.format(date1);
    }


    public void generateNoteOnSD(Context context, String sFileName, String sBody) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
