package com.babbangona.hyperlogger.Network;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class NotificationService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
        //String message = generateMessage();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //String message = generateMessage();

        new SyncController(getApplicationContext()).uploadLogs();
        stopSelf();

        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
