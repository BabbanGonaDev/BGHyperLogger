package com.bgenterprise.hyperloggersample;

import android.app.Application;
import android.util.Log;

import com.babbangona.hyperlogger.Logger;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d("CHECK", "We are creating the builder here");

        new Logger.Builder(this)
                .setStaffId("JAB")
                .setAppVersion("1.0.0")
                .setBuildType(BuildConfig.BUILD_TYPE)
                .startSession();
    }
}
