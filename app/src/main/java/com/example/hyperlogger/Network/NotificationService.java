package com.example.hyperlogger.Network;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.List;

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
