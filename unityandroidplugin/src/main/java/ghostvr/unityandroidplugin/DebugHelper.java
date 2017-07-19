package ghostvr.unityandroidplugin;

import android.os.SystemClock;
import android.util.Log;

/**
 * Created by Divyanshu on 6/27/17.
 */

public class DebugHelper {

    private static boolean isSensorLogEnabled = false;
    private static boolean isRosLogEnabled = false;
    private static boolean isLoadAppLogEnabled = false;

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

    public static boolean sensorLogEnabled() {
        return isSensorLogEnabled;
    }

    public static boolean rosLogEnabled() {
        return isRosLogEnabled;
    }

    public static boolean loadAppLogEnabled() {
        return isLoadAppLogEnabled;
    }

    public static void setMessage(String message){
        Log.d("DebugHelper", message);
    }

    public static String getMessage(){
        return "DebugHelper: Time Elapsed = " + SystemClock.elapsedRealtimeNanos();
    }
}
