package ghostvr.unityandroidplugin;

import android.content.Context;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Divyanshu on 6/19/17.
 */

public class LaunchAppPlugin {

    private Context context;

    private static Map<String, String> indexAppNameHashMap;

    static {
        indexAppNameHashMap = new HashMap<>();
        indexAppNameHashMap.put("0", "com.android.chrome");
        indexAppNameHashMap.put("1", "com.google.android.gm");
        indexAppNameHashMap.put("2", "com.google.android.apps.maps");
        indexAppNameHashMap.put("3", "com.google.android.youtube");
        indexAppNameHashMap.put("4", "com.google.android.apps.docs");
        indexAppNameHashMap.put("5", "com.google.android.music");
        indexAppNameHashMap.put("6", "com.google.android.videos");
        indexAppNameHashMap.put("7", "com.google.android.talk");
        indexAppNameHashMap.put("8", "com.google.android.apps.photos");
    }

    public LaunchAppPlugin(Context context){
        this.context = context;
    }

    public void launchApp(String appIndex){
        Intent launchAppIntent = context.getPackageManager().getLaunchIntentForPackage(indexAppNameHashMap.get(appIndex));
        context.startActivity(launchAppIntent);
    }
}
