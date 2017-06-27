package ghostvr.unityandroidplugin;

import android.os.SystemClock;
import android.util.Log;

/**
 * Created by Divyanshu on 6/27/17.
 */

public class DebugHelper {

    public static void setMessage(String message){
        Log.d("DebugHelper", message);
    }

    public static String getMessage(){
        return "DebugHelper: Time Elapsed = " + SystemClock.elapsedRealtimeNanos();
    }
}
