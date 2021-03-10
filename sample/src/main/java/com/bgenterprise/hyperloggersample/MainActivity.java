package com.bgenterprise.hyperloggersample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.babbangona.hyperlogger.LogRecords;
import com.babbangona.hyperlogger.LogType;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    LogRecords logger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logger = new LogRecords(this, "T-10000000000000AA", "");

        logger.captureAuditLogs(this, LogType.EXCEPTION, "", "", "", "",
                "", "", "", "");

        logger.captureGeneralLogs(this, LogType.CRASH, "");

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