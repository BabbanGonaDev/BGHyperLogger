package com.babbangona.hyperlogger.data.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import com.google.gson.Gson;

public class SharedPrefs {
    private static final String TAG = "LoggerSharedPrefs";
    private final SharedPreferences mSharedPreferences;
    private final SharedPreferences.Editor editor;
    private final Gson gson;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = Context.MODE_PRIVATE;

    private static final String PREF_NAME = "Hyperlogger Preferences";
    private final int PUT_FAIL = -1;
    private final int PUT_SUCCESS = 0;

    public static final String STAFF_ID = "staff_id";
    public static final String APP_VERSION = "app_version";
    public static final String SESSION_ID = "session_id";
    public static final String IMEI = "imei";
    public static final String OS_VERSION = "os_version";
    public static final String DEVICE_MANUFACTURER = "device_manufacturer";
    public static final String DEVICE_NAME = "device_name";
    public static final String BUILD_TYPE = "build_type";

    // Constructor
    public SharedPrefs(Context context) {
        this._context = context;
        mSharedPreferences = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = mSharedPreferences.edit();
        gson = new Gson();
    }

    /**
     * GENERIC FUNCTION TO STORE ANYTHING TO SHARED PREFS AS LONG AS IT IS SERIALIZABLE BY THE GSON CONVERTER
     *
     * @param key   -  THE STRING KEY VALUE OF THE OBJECT TO BE STORED
     * @param value - THE VALUE TO BE STORED IN THE SHARED PREFS
     * @param <T>   - THE TYPE BEING STORED
     * @return- RESULT, 0- SUCCESSFUL, 1-UNSUCCESSFUL
     */
    public <T> int putValue(String key, T value) {
        if (value instanceof String) {
            editor.putString(key, (String) value);

        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);

        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);

        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);

        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else {
            try {
                String val = gson.toJson(value);
                editor.putString(key, val);
            } catch (Exception e) {
                return PUT_FAIL;
            }
        }
        editor.apply();
        return PUT_SUCCESS;
    }


    public <T> int putValues(Pair<String, T>... keyValuePairs) {
        int res = 0;
        for (Pair<String, T> keyValue : keyValuePairs) {
            res += putValue(keyValue.first, keyValue.second);
        }
        return res < 0 ? PUT_FAIL : PUT_SUCCESS;

    }

    public <T> T getValue(Class<T> dataTypeofKey, String key) {
        if (dataTypeofKey == String.class) {
            return (T) mSharedPreferences.getString(key, "");

        } else if (dataTypeofKey == Integer.class) {
            return (T) (Integer) mSharedPreferences.getInt(key, 0);

        } else if (dataTypeofKey == Float.class) {
            return (T) (Float) mSharedPreferences.getFloat(key, 0);

        } else if (dataTypeofKey == Long.class) {
            return (T) (Long) mSharedPreferences.getLong(key, 0);

        } else if (dataTypeofKey == Boolean.class) {
            return (T) (Boolean) mSharedPreferences.getBoolean(key, false);
        } else {
            try {
                String val = mSharedPreferences.getString(key, "");
                if (val.isEmpty()) {
                    return null;
                } else {
                    return gson.fromJson(val, dataTypeofKey);
                }
            } catch (Exception e) {
                Log.i(TAG, "getValue: Exception occurred in POJO Xform " + e.getMessage());
                return null;
            }
        }
    }

    public <T> T getValue(Class<T> dataTypeofKey, String key, T defaultValue) {
        if (dataTypeofKey == String.class) {
            return (T) mSharedPreferences.getString(key, (String) defaultValue);
        } else if (dataTypeofKey == Integer.class) {
            return (T) (Integer) mSharedPreferences.getInt(key, (Integer) defaultValue);
        } else if (dataTypeofKey == Float.class) {
            return (T) (Float) mSharedPreferences.getFloat(key, (Float) defaultValue);
        } else if (dataTypeofKey == Long.class) {
            return (T) (Long) mSharedPreferences.getLong(key, (Long) defaultValue);
        } else if (dataTypeofKey == Boolean.class) {
            return (T) (Boolean) mSharedPreferences.getBoolean(key, false);
        } else {
            try {
                String val = mSharedPreferences.getString(key, "");
                if (TextUtils.isEmpty(val)) {
                    return null;
                } else {
                    return gson.fromJson(val, dataTypeofKey);
                }

            } catch (Exception e) {
                Log.i(TAG, "getValue: Exception occurred in POJO Xform " + e.getMessage());
                return null;
            }
        }
    }
}
