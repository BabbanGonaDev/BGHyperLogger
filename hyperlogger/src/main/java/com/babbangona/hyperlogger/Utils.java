package com.babbangona.hyperlogger;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.UUID;

public class Utils {
    Context mCtx;

    //Empty constructor
    public Utils(Context context) {
        this.mCtx = context;
    }

    //1. Generate session-id using the device's uuid and datetime
    public String generateSessionId() {
        return UUID.randomUUID().toString();
    }


    //2. Get the device's IMEI
    public String getDeviceImei() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //Device IMEI can't be gotten from Android 9 devices and above
            return "N/A";
        } else {
            try {
                TelephonyManager manager = (TelephonyManager) mCtx.getSystemService(Context.TELEPHONY_SERVICE);
                String imei = manager.getDeviceId();
                if (!TextUtils.isEmpty(imei)) {
                    return imei;
                }
                return "N/A";
            } catch (SecurityException e) {
                e.printStackTrace();
                return "N/A";
            }
        }
    }

}
