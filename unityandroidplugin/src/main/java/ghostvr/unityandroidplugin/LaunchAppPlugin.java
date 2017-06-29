package ghostvr.unityandroidplugin;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divyanshu on 6/19/17.
 */

public class LaunchAppPlugin {

    private Context context;
    private List<AppDetails> appList;

    public LaunchAppPlugin(Context context){
        this.context = context;
        makeAppList();
    }

    private void makeAppList() {
        appList = new ArrayList<>();

        PackageManager manager = context.getPackageManager();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(intent, 0);

        for (ResolveInfo ri : availableActivities) {
            AppDetails app = new AppDetails(
                    (String) ri.loadLabel(manager),
                    ri.activityInfo.packageName,
                    ri.loadIcon(manager)
            );

            appList.add(app);

            if (DebugHelper.isLoadAppLogEnabled)
                Log.d("LaunchAppPlugin", "Loading Apps");
            getClass();
        }
    }

    public Object[] getAppArray(){
        return appList.toArray();
    }

    public void launchApp(String appName){
        Intent launchAppIntent = context.getPackageManager().getLaunchIntentForPackage(appName);
        context.startActivity(launchAppIntent);
    }
}
