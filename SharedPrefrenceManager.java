package com.pibusa.prefrence;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Singleton class responsible and used to save and retrieve data from shared
 * prefrence
 */

public class SharedPrefrenceManager {
    private static SharedPrefrenceManager refrence = null;
    private final String PREFRENCE_NAME = "test_app_prefrence";
    public static final String LIST_PREFRENCE_KEY = "list_key";

    /**
     * Method to get single instance of this class
     *
     * @return SharedPrefrenceManager instance of class
     */
    public static SharedPrefrenceManager getInstance() {
        if (refrence == null) {
            refrence = new SharedPrefrenceManager();
        }
        return refrence;
    }

    /**
     * Method to get saved values from shared prefrence
     *
     * @param context      context of class
     * @param defaultValue default value if there is no data in shared prefrence
     * @param prefKey      prefrence key by which value will be accessed
     * @return String saved value
     */
    public String getSavedPrefrenceValue(Context context, String defaultValue,
                                         String prefKey) {
        String storedLanguage = "";
        SharedPreferences myPrefs = context.getSharedPreferences(
                PREFRENCE_NAME, Context.MODE_WORLD_READABLE);
        storedLanguage = myPrefs.getString(prefKey, defaultValue);
        return storedLanguage;
    }

    /**
     * Method to save value in shared prefrence
     *
     * @param context  context of class
     * @param newValue value to be saved
     * @param prefKey  prefrence key by which value will be saved
     */
    public void setPrefrenceValue(Context context, String newValue,
                                  String prefKey) {
        SharedPreferences myPrefs = context.getSharedPreferences(
                PREFRENCE_NAME, Context.MODE_WORLD_READABLE);
        SharedPreferences.Editor prefsEditor = myPrefs.edit();
        prefsEditor.putString(prefKey, newValue);
        prefsEditor.commit();
    }

}


