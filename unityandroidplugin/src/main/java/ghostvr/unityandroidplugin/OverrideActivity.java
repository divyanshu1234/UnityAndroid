package ghostvr.unityandroidplugin;

import android.os.Bundle;
import com.unity3d.player.UnityPlayerActivity;

public class OverrideActivity extends UnityPlayerActivity {

    RotationSensorPlugin rotationSensorPlugin;
    LaunchAppPlugin launchAppPlugin;
    ROSThreadPlugin rosThreadPlugin;

    boolean isRotationSensorEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void enableRotationSensorPlugin(){
        rotationSensorPlugin = new RotationSensorPlugin(this);
        isRotationSensorEnabled = true;
    }

    public void enableRosThreadPlugin(){
        rosThreadPlugin = new ROSThreadPlugin();
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
