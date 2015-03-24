package org.faclab.android.jeudepiste.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Tatiana Grange on 22/01/2015.
 */
public class Tools {

    /**
     * This is the tag for log in the application
     */
    public static final String TAG = "FacLab - Jeu de Piste";

    /**
     * This method Log, always on "i", always with TAG
     * @param s - The String to log
     */
    public static void log(String s){
        Log.i(TAG, s);
    }

    /**
     *
     * @param context - The Context where the Toast have to show
     * @param text - The String show in the Toast
     */
    public static void toast(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
