package com.bgenterprise.hyperloggersample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.babbangona.hyperlogger.LogRecords;
import com.babbangona.hyperlogger.LogType;
import com.bgenterprise.hyperloggersample.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    LogRecords logger;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        logger = new LogRecords(this, "T-10000000000000AA");

        binding.btnAuditLog.setOnClickListener(v -> {
            //Capture audit logs
            logger.captureAuditLogs(MainActivity.this, LogType.EXCEPTION, "", "", "", "",
                    "", getResources().getString(R.string.app_name), BuildConfig.VERSION_NAME, BuildConfig.APPLICATION_ID, new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()));
        });

        binding.btnGeneralLog.setOnClickListener(v -> {
            //Capture general logs.
            logger.captureGeneralLogs(MainActivity.this, LogType.CRASH, "", BuildConfig.APPLICATION_ID);
        });


        logger.startMixPanelClass(this);

        logger.mixPanelTracker("", this);

        JSONObject obj = new JSONObject();
        try {
            obj.put("name", "key");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        logger.mixPanelTracker("", obj, this);

        logger.triggerSync(this, 1);
    }
}