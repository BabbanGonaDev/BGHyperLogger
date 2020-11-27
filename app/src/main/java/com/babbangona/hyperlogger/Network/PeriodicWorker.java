package com.babbangona.hyperlogger.Network;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.babbangona.hyperlogger.Database.sharedprefs.SharedPrefs;


public class PeriodicWorker extends Worker {

    public PeriodicWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    @NonNull
    @Override
    public Result doWork() {
        Log.i("Worker in action", "Doing work");
        SharedPrefs sharedPrefs = new SharedPrefs(getApplicationContext());
        Log.d("--BG_LOGGER:-", "sync_activated");
        int sync_trigger = sharedPrefs.getSyncTrigger();
        if (sync_trigger == 1) {
            Log.d("--BG_LOGGER:-", "sync_activated_with_flag");
            new SyncController(getApplicationContext()).uploadLogs();
        }
        return Result.success();
    }

}
