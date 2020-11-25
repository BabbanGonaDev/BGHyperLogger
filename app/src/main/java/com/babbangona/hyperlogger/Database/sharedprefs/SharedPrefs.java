package com.babbangona.hyperlogger.Database.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;



public class SharedPrefs {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    //TO-DO: CHANGE THIS TO YOUR SHARED PREFS NEED
    private static final String PREF_NAME = "AndroidHivePref";


    //TODO: CHANGE THESE TO THE SPECIFIED STRINGS YOU NEED
    public static final String BASE_URL = "base_url";
    public static final String BASE_SCRIPT = "base_script";
    public static final String SYNC_FLAG = "sync_flag";




    // Constructor
    public SharedPrefs(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }



    public void setURLDetails(String url, String script){
        editor.putString(BASE_URL, url);
        editor.putString(BASE_SCRIPT, script);
        editor.commit();
    }

    public void setSyncTrigger(int flag){
        editor.putInt(SYNC_FLAG, flag);
        editor.commit();
    }



}
