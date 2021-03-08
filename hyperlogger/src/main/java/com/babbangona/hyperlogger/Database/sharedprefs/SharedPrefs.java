package com.babbangona.hyperlogger.Database.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;



public class SharedPrefs {
    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    //TO-DO: CHANGE THIS TO YOUR SHARED PREFS NEED
    private static final String PREF_NAME = "BG_H_Logger_Prefs";


    //TODO: CHANGE THESE TO THE SPECIFIED STRINGS YOU NEED
    public static final String BASE_URL = "base_url";
    public static final String BASE_SCRIPT = "base_script";
    public static final String SYNC_FLAG = "sync_flag";


    public static final String MIX_PANEL_STAFF_ID = "mix_panel_staff_id";
    public static final String MIX_PANEL_TOKEN = "mix_panel_staff_token";




    // Constructor
    public SharedPrefs(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }



    public void setURLDetails(String url){
        editor.putString(BASE_URL, url);
        editor.commit();
    }

    public void setMixPanelToken(String token){
        editor.putString(MIX_PANEL_TOKEN, token);
        editor.commit();
    }

    public void setMixPanelStaffId(String staff_id){
        editor.putString(MIX_PANEL_STAFF_ID, staff_id);
        editor.commit();
    }

    public void setSyncTrigger(int flag){
        editor.putInt(SYNC_FLAG, flag);
        editor.commit();
    }

    public int getSyncTrigger() {
        return pref.getInt(SYNC_FLAG, 1);
    }

    public String getMixPanelStaffId(){
        return pref.getString(MIX_PANEL_STAFF_ID, "T-1000000000000LOG");
    }

    public String getBaseUrl(){
        return pref.getString(BASE_URL, "https://mobileapps.testenvironmentbg.com/hyperlogger_api/public/api/v1/");
    }

    public String getMixPanelToken(){
        return pref.getString(MIX_PANEL_TOKEN, "de8d61ea252990ca1b6aef60b3220afc");
    }



}
