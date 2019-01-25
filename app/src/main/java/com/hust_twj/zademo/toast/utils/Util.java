package com.hust_twj.zademo.toast.utils;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by Wenjing.Tang
 * on 2019/1/25
 */
public class Util {

    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code"; //小米
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static Boolean sIsMIUI;

    /**
     * 小米系统
     *
     * @return
     */
    public static boolean isMIUI() {
        if (sIsMIUI != null) {
            return sIsMIUI;
        }
        boolean isMIUI = false;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            try {
                if (!TextUtils.isEmpty(getSystemProperty(KEY_MIUI_VERSION_CODE, ""))
                        || !TextUtils.isEmpty(getSystemProperty(KEY_MIUI_VERSION_NAME, ""))
                        || !TextUtils.isEmpty(getSystemProperty(KEY_MIUI_INTERNAL_STORAGE, ""))) {
                    isMIUI = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Properties prop = new Properties();
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
                prop.load(fileInputStream);
                isMIUI = prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                        || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                        || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try{
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        }
        sIsMIUI = isMIUI;
        return isMIUI;
    }

    private static String getSystemProperty(String key, String defaultValue) {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class, String.class);
            return (String) get.invoke(clz, key, defaultValue);
        } catch (ClassNotFoundException e) {
        } catch (NoSuchMethodException e) {
        } catch (IllegalAccessException e) {
        } catch (IllegalArgumentException e) {
        } catch (InvocationTargetException e) {
        }
        return defaultValue;
    }

}
