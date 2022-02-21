package com.cas.veritasapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by funmiayinde on 2019-10-10.
 */
public class PrefUtil {

    private static final String MEAL_APP = "meal_app";


    private PrefUtil() {
        throw new UnsupportedOperationException("Should not create Instance of Util class, please use static...");
    }

    /**
     * Get boolean data
     *
     * @param context the context
     * @param key     the key
     * @return boolean
     */
    static public boolean getBooleanData(Context context, String key) {
        return context.getSharedPreferences(MEAL_APP, Context.MODE_PRIVATE).getBoolean(key, false);
    }

    /**
     * Get int data
     *
     * @param context the context
     * @param key     the key
     * @return int
     */
    static public int getIntData(Context context, String key) {
        return context.getSharedPreferences(MEAL_APP, Context.MODE_PRIVATE).getInt(key, 0);
    }

    /**
     * Get int data
     *
     * @param context the context
     * @param key     the key
     * @return String
     */
    static public String getStringData(Context context, String key) {
        return context.getSharedPreferences(MEAL_APP, Context.MODE_PRIVATE).getString(key, null);
    }

    /**
     * Get Object data
     *
     * @param context the context
     * @param clazz the class
     * @return String
     */
    static public Object getObjectData(Context context, Class clazz) {
        Gson gson = new Gson();
        String json = context.getSharedPreferences(MEAL_APP, Context.MODE_PRIVATE).getString(clazz.getSimpleName(), null);
        return gson.fromJson(json, clazz);
    }


    /**
     * Save data
     *
     * @param context the context
     * @param object  the object
     */
    static public void saveData(Context context, Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        context.getSharedPreferences(MEAL_APP, Context.MODE_PRIVATE).edit().putString(object.getClass().getSimpleName(), json).apply();
    }

    /**
     * Save data
     *
     * @param context the context
     * @param key     the key
     * @param val     the value to save
     */
    static public void saveData(Context context, String key, boolean val) {
        context.getSharedPreferences(MEAL_APP, Context.MODE_PRIVATE).edit().putBoolean(key, val).apply();
    }

    /**
     * Save data
     *
     * @param context the context
     * @param key     the key
     * @param val     the value to save
     */
    static public void saveData(Context context, String key, float val) {
        context.getSharedPreferences(MEAL_APP, Context.MODE_PRIVATE).edit().putFloat(key, val).apply();
    }

    /**
     * Save data
     *
     * @param context the context
     * @param key     the key
     * @param val     the value to save
     */
    static public void saveData(Context context, String key, Object val) {
        Gson gson = new Gson();
        String json = gson.toJson(val);
        context.getSharedPreferences(MEAL_APP, Context.MODE_PRIVATE).edit().putString(key, json).apply();
    }

    /**
     * Save data
     *
     * @param context the context
     * @param key     the key
     * @param val     the value to save
     */
    static public void saveData(Context context, String key, String val) {
        context.getSharedPreferences(MEAL_APP, Context.MODE_PRIVATE).edit().putString(key, val).apply();
    }

    static public SharedPreferences.Editor getSharedPrefEditor(Context context, String pref) {
        return context.getSharedPreferences(pref, Context.MODE_PRIVATE).edit();
    }

    static public void saveData(SharedPreferences.Editor editor) {
        editor.apply();
    }


}
