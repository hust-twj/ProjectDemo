package com.hust_twj.zademo.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jieli-liliyan on 16/10/27.
 */

public class PreferenceUtil {

    private static final String APP_CONFIG = "app_config";

    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences sp = getSharedPreferences(context, APP_CONFIG);
        return sp.getString(key, defaultValue);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences sp = getSharedPreferences(context, APP_CONFIG);
        return sp.getBoolean(key, defaultValue);
    }

    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences sp = getSharedPreferences(context, APP_CONFIG);
        return sp.getInt(key, defaultValue);
    }

    public static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences sp = getSharedPreferences(context, APP_CONFIG);
        return sp.getFloat(key, defaultValue);
    }

    public static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences sp = getSharedPreferences(context, APP_CONFIG);
        return sp.getLong(key, defaultValue);
    }


    public static void saveValue(Context context, String key, Object value) {
        SharedPreferences sp = getSharedPreferences(context, APP_CONFIG);
        SharedPreferences.Editor editor = sp.edit();
        if (value instanceof Boolean) {
            editor.putBoolean(key, Boolean.parseBoolean(value.toString()));
        } else if (value instanceof String) {
            editor.putString(key, value.toString());
        } else if (value instanceof Long) {
            editor.putLong(key, Long.parseLong(value.toString()));
        } else if (value instanceof Float) {
            editor.putFloat(key, Float.parseFloat(value.toString()));
        } else if (value instanceof Integer) {
            editor.putInt(key, Integer.parseInt(value.toString()));
        }
        editor.apply();
    }

    private static SharedPreferences getSharedPreferences(Context context, String name) {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    private static final String OLD_APP_CONFIG = "account";
    private static final String AUTO_LOGIN = "auto_login";
    private static final String AUTO_LOGIN_VALUE = "auto_login_value";
    private static final String SHOW_UPGRADE_DIALOG = "show_upgrade_dialog";

    /**
     * 为了兼容老珍爱版本
     *
     * @return
     */
    public static String getStringFromOldVersion(Context context, String key, String defaultValue) {
        SharedPreferences sp = getSharedPreferences(context, OLD_APP_CONFIG);
        return sp.getString(key, defaultValue);
    }

    public static void resetStringForOldVersion(Context context, String key) {
        SharedPreferences sp = getSharedPreferences(context, OLD_APP_CONFIG);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, "");
        editor.apply();
    }

    public static void resetAutoLoginForOldVersion(Context context) {
        SharedPreferences sp = getSharedPreferences(context, AUTO_LOGIN);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(AUTO_LOGIN_VALUE, false);
        editor.apply();
    }

    public static boolean getAutoLoginFromOldVersion(Context context, boolean defaultValue) {
        SharedPreferences sp = getSharedPreferences(context, AUTO_LOGIN);
        return sp.getBoolean(AUTO_LOGIN_VALUE, defaultValue);
    }

    public static void setShowUpgradeDialog(Context context) {
        saveValue(context, SHOW_UPGRADE_DIALOG, System.currentTimeMillis());
    }

    public static boolean isShowUpgradeDialog(Context context) {
        long time = getLong(context, SHOW_UPGRADE_DIALOG, 0);
        return !DateUtils.isSameDay(time, System.currentTimeMillis());
    }
}
