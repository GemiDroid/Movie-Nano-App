package com.orchtech.edaradotcom.settings;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ahmed.yousef on 1/8/2017.
 */

public class Preferences {

    //_______________________________________________________________________________//

    /**
     * The base URL used for Retrofit ..
     */

    public static String getUrlHeader() {
        return "https://edara.com/restful/api/";
    }
    //____________________________________________________________________________________//

    /**
     * Insert Key to retreive in the future and value to be saved ..
     */
    public static void saveInPreference(Context context,String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences("edara", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    //___________________________________________________________________________________//

    /**
     * Insert Key to retreive value ..
     */
    public static String getFromPreference(Context context,String key) {
        SharedPreferences preferences = context.getSharedPreferences("edara",Context.MODE_PRIVATE);
        return preferences.getString(key,"");
    }

}
