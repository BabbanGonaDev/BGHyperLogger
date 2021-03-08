package com.babbangona.hyperlogger;

import android.content.Context;

import com.babbangona.hyperlogger.Database.sharedprefs.SharedPrefs;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONObject;

public class MixPanelActivator {

    Context context;

    MixpanelAPI mix_panel;
    SharedPrefs sharedPrefs;

    public MixPanelActivator(Context context, boolean activate) {
        this.context = context;
        sharedPrefs = new SharedPrefs(context);
        if(activate){
            mix_panel = MixpanelAPI.getInstance(context, sharedPrefs.getMixPanelToken());
            mix_panel.alias(sharedPrefs.getMixPanelStaffId(),null);
            mix_panel.identify(sharedPrefs.getMixPanelStaffId());
            setMixPanelProperties();
        }
    }

    public MixPanelActivator(Context context) {
        this.context = context;
        sharedPrefs = new SharedPrefs(context);
        mix_panel = MixpanelAPI.getInstance(context, sharedPrefs.getMixPanelToken());
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

    public void mixPanelTracking(String tracking_title, JSONObject jsonObject){
        mix_panel.track(tracking_title, jsonObject);
    }

    public void mixPanelTracking(String tracking_title){
        mix_panel.track(tracking_title);
    }

    private void setMixPanelProperties(){
        /*JSONObject props = new JSONObject();
        props.put("$staff_id", sharedPrefs.getMixPanelStaffId());*/

        mix_panel.getPeople().identify(sharedPrefs.getMixPanelStaffId());
        mix_panel.getPeople().set("$staff_id", sharedPrefs.getMixPanelStaffId());
    }

}
