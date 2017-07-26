package ghostvr.unityandroidplugin;

import android.os.Bundle;
import android.view.WindowManager;

import com.unity3d.player.UnityPlayerActivity;

public class OverrideActivity extends UnityPlayerActivity {

    RotationSensorPlugin rotationSensorPlugin;
    LaunchAppPlugin launchAppPlugin;
    RosThreadPlugin rosThreadPlugin;

    private boolean isRotationSensorEnabled = false;

    private static final String LOG_TAG = OverrideActivity.class.getSimpleName();

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
        rosThreadPlugin = new RosThreadPlugin();
        new Thread(rosThreadPlugin).start();
    }

    public void enableLaunchAppPlugin(){
        launchAppPlugin = new LaunchAppPlugin(this);
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