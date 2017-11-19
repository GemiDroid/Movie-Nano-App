package com.orchtech.baking_app.models;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ahmed.yousef on 1/8/2017.
 */

public class Preferences {

    /**
     * Insert Key to retrieve in the future and value to be saved ..
     */
    public static void saveInPreference(Context context,String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences("baking", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    //___________________________________________________________________________________//

    /**
     * Insert Key to retrieve value ..
     */
    public static String getFromPreference(Context context,String key) {
        SharedPreferences preferences = context.getSharedPreferences("baking",Context.MODE_PRIVATE);
        return preferences.getString(key,"");
    }

}
