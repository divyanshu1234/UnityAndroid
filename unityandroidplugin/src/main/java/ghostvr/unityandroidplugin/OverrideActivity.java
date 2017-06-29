package ghostvr.unityandroidplugin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.unity3d.player.UnityPlayerActivity;

public class OverrideActivity extends UnityPlayerActivity {

    RotationSensorPlugin rotationSensorPlugin;
    LaunchAppPlugin launchAppPlugin;
    ROSThreadPlugin rosThreadPlugin;

    boolean isRotationSensorEnabled = false;

    private String LOG_TAG = getClass().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_FULL;
        getWindow().setAttributes(params);

        super.onCreate(savedInstanceState);
    }

    public void enableRotationSensorPlugin(){
        rotationSensorPlugin = new RotationSensorPlugin(this);
        rotationSensorPlugin.registerListener();
        isRotationSensorEnabled = true;
    }

    public void enableRosThreadPlugin(){
        rosThreadPlugin = new ROSThreadPlugin();
        new Thread(rosThreadPlugin).start();
    }

    public void enableLaunchAppPlugin(){
        launchAppPlugin = new LaunchAppPlugin(this);
    }

    public void startDebugActivity(){
        Log.d(LOG_TAG, "Starting Debug Activity");
        Intent startDebugActivityIntent = new Intent(OverrideActivity.this, ghostvr.unityandroidplugin.DebugActivity.class);
        startActivity(startDebugActivityIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isRotationSensorEnabled){
            rotationSensorPlugin.registerListener();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (isRotationSensorEnabled){
            rotationSensorPlugin.unregisterListener();
        }
    }
}
