package com.babbangona.hyperlogger;

import android.content.Context;

import com.babbangona.hyperlogger.Database.DatabaseStringConstants;
import com.babbangona.hyperlogger.Database.sharedprefs.SharedPrefs;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

public class MixPanelActivator {

    Context context;

    MixpanelAPI mix_panel;

    public MixPanelActivator(Context context, boolean activate) {
        this.context = context;
        SharedPrefs sharedPrefs = new SharedPrefs(context);
        if(activate){
            mix_panel = MixpanelAPI.getInstance(context, sharedPrefs.getMixPanelToken());
            mix_panel.identify(sharedPrefs.getMixPanelStaffId());
        }
    }

    public void deactivateMixPanel(){
        try {
            mix_panel.optOutTracking();
        } catch (Exception e) {
            e.printStackTrace();
            LogRecords logRecords = new LogRecords();
            logRecords.captureLogs(context, "Library failure","Mix Panel Failed "+ e.toString());
        }
    }

    public boolean checkMixPanelExitStatus(){
        return mix_panel.hasOptedOutTracking();
    }

}
