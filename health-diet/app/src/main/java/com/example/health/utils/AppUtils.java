package com.example.health.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class AppUtils {

    private static final String PREF_NAME = "app_pref";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;

    private Application application;

    public AppUtils() {
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            application = (Application) activityThreadClass.getMethod("getApplication").invoke(activityThread);
        } catch (Exception e) {
            e.printStackTrace();
        }
        pref = application.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    /**
     * 创建并获取单例
     */
    private static AppUtils getInstance() {
        return InstanceHolder.instance;
    }

    public static boolean isFirstTimeLaunch() {
        return getInstance().pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public static void setFirstTimeLaunch(boolean isFirstTime) {
        getInstance().editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        getInstance().editor.commit();
    }

    /**
     * 反射获取Application
     */
    public static Application getApplication() {
        return getInstance().application;
    }

    private static final class InstanceHolder {
        /**
         * 单例
         */
        static final AppUtils instance = new AppUtils();
    }
}
