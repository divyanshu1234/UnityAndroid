package ghostvr.unityandroidplugin;

import android.os.SystemClock;
import android.util.Log;

/**
 * Created by Divyanshu on 6/27/17.
 */

public class DebugHelper {

    public static boolean isSensorLogEnabled = false;
    public static boolean isRosLogEnabled = false;
    public static boolean isLoadAppLogEnabled = false;

    public void enableSensorLog(String isEnabled) {
        if (isEnabled.equals("true"))
            isSensorLogEnabled = true;
        else
            isSensorLogEnabled = false;
    }

    public void enableRosLog(String isEnabled) {
        if (isEnabled.equals("true"))
            isRosLogEnabled = true;
        else
            isRosLogEnabled = false;
    }

    public void enableLoadAppLog(String isEnabled) {
        if (isEnabled.equals("true"))
            isLoadAppLogEnabled = true;
        else
            isLoadAppLogEnabled = false;
    }

    public static void setMessage(String message){
        Log.d("Debug Helper", message);
    }

    public static String getMessage(){
        return "DebugHelper: Time Elapsed = " + SystemClock.elapsedRealtimeNanos();
    }
}
