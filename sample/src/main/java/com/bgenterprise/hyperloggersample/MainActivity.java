package com.bgenterprise.hyperloggersample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.babbangona.hyperlogger.Logger;
import com.bgenterprise.hyperloggersample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    Logger logger;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Logger x = new Logger.Builder(getApplication())
                .setStaffId("JAB")
                .setAppVersion("afe")
                .setBuildType(BuildConfig.BUILD_TYPE)
                .startSession();

        logger = Logger.getInstance(MainActivity.this);
        logger.debug("This is a debug message");

    }
}