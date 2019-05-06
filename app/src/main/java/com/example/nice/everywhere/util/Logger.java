package com.example.nice.everywhere.util;
import android.util.Log;

import com.example.nice.everywhere.base.Constants;


/**
 * Created by ws on 2019/4/30.
 */

public class Logger {
    public static void logD(String tag,String msg){
        if (Constants.isDebug){
            Log.d(tag, "logD: "+msg);
        }
    }
}
