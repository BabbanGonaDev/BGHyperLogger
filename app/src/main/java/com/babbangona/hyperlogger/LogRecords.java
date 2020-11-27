package com.babbangona.hyperlogger;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.babbangona.hyperlogger.Database.AppDatabase;
import com.babbangona.hyperlogger.Database.Entities.AppLogs;
import com.babbangona.hyperlogger.Database.Entities.HyperLoggerTable;
import com.babbangona.hyperlogger.Database.sharedprefs.SharedPrefs;
import com.babbangona.hyperlogger.Network.PeriodicWorker;
import com.babbangona.hyperlogger.Network.SyncController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;


//this model class implements the functions in the interface class
public class LogRecords implements LogRecordsHelper {

    MixPanelActivator mixPanelActivator;

    public LogRecords(Context context, String staff_id, String token, String endpoint_url) {
        startAutoSyncClass(context);
        SharedPrefs sharedPrefs = new SharedPrefs(context);
        sharedPrefs.setMixPanelStaffId(staff_id);
        sharedPrefs.setMixPanelToken(token);
        sharedPrefs.setURLDetails(endpoint_url);
        forceSync(context);
    }

    public LogRecords() {
    }

    @Override
    public String captureLogs(Context context, String log_type, String log_message) {

        //insertion of logs for the general log tracking
        String remark;
        try {
            AppLogs appLogs = new AppLogs(random()+"_"+getDate("concat"),log_type,log_message,getDate("spread"),"0");
            AppDatabase appDatabase =  AppDatabase.getInstance(context);
            appDatabase.appLogsDao().insert(appLogs);
            remark = outputRemark(1,"", context);
        } catch (Exception e) {
            e.printStackTrace();
            remark = outputRemark(0, e.toString()+"", context);
        }


        //TODO please update this to the desired return, and also publish different return types in the interface class comment
        return remark;
    }

    @Override
    public String captureAuditLogs(Context context,String log_type, String log_message, String tag, String phone_name, String imei, String staff_id, String application_name, String application_version, String time_stamp) {

        //insertion of logs for the audit log tracking
        String remark;
        try {
            HyperLoggerTable hyperLoggerTable = new HyperLoggerTable(random()+"_"+getDate("concat"),log_type,log_message,tag,phone_name,imei,staff_id,application_name,application_version,getDate("spread"),"0");
            AppDatabase appDatabase =  AppDatabase.getInstance(context);
            appDatabase.hyperLoggerDao().insert(hyperLoggerTable);
            remark = outputRemark(1,"", context);
        } catch (Exception e) {
            e.printStackTrace();
            remark = outputRemark(0, e.toString()+"", context);
        }

        //TODO please update this to the desired return, and also publish different return types in the interface class comment
        return remark;
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
    public void writeAuditLogsToFile(Context context, int flag) {

        //this block of code handles writing the logs to files
        //TODO enable permission for storage so the library does not crash the application
        List<AppLogs> appLogs = AppDatabase.getInstance(context).appLogsDao().getAllRecords();
        ArrayList<String> logs = new ArrayList<>();


        for (AppLogs mLogs: appLogs){
            logs.add("Log entry: "+mLogs.getLog_id()+" "+mLogs.getLog_type()+" "+mLogs.getLog_message()+" "+mLogs.getSync_flag()+" "+mLogs.getTime_stamp()+"\n");
        }

        //function writing to external storage
        generateNoteOnSD(context,"AuditLogs.txt", logs.toString());
    }

    @Override
    public void writeGeneralLogsToFile(Context context, int flag) {
        //does nothing for now.
    }

    @Override
    public void startMixPanelClass(Context context) {
        mixPanelActivator = new MixPanelActivator(context, true);
    }

    @Override
    public void stopMixPanelClass() {
        mixPanelActivator.deactivateMixPanel();
    }

    @Override
    public boolean checkMixPanelExitStatus() {
        return mixPanelActivator.checkMixPanelExitStatus();
    }

    private void forceSync(Context context) {

        //String message = generateMessage();
        SharedPrefs sharedPrefs = new SharedPrefs(context);
        if(sharedPrefs.getSyncTrigger() == 1){
            new SyncController(context).uploadLogs();
        }
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

    //This method generates logs in a .txt to phone memory
    private void generateNoteOnSD(Context context, String sFileName, String sBody) {
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

    private void startAutoSyncClass(Context context){

        String remark;
        try {
            WorkManager workManager = WorkManager.getInstance(context);

            WorkRequest callDataRequest = new PeriodicWorkRequest.Builder(PeriodicWorker.class,
                    30, TimeUnit.MINUTES, 5, TimeUnit.MINUTES)
                    .build();
            workManager.enqueue(callDataRequest);
            remark = outputRemark(1,"", context);
        } catch (Exception e) {
            e.printStackTrace();
            remark = outputRemark(0, e.toString() + "", context);
        }
        Log.d("--BG LOGGER INIT--",remark);
    }

    private String outputRemark(int input, String cause, Context context){
        if (input == 1){
            return context.getResources().getString(R.string.op_success);
        }else{
            return context.getResources().getString(R.string.op_no_success) + ": " + cause;
        }
    }

}
