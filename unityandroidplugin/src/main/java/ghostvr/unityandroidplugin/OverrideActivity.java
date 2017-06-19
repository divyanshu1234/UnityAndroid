package ghostvr.unityandroidplugin;

import android.os.Bundle;
import com.unity3d.player.UnityPlayerActivity;

public class OverrideActivity extends UnityPlayerActivity {

//    ROSThreadPlugin rosThreadPlugin;

    RotationSensorPlugin rotationSensorPlugin;

    LaunchAppPlugin launchAppPlugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rotationSensorPlugin = new RotationSensorPlugin(this);

//        rosThreadPlugin = new ROSThreadPlugin();
//        new Thread(rosThreadPlugin).start();

        launchAppPlugin = new LaunchAppPlugin(this);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (rotationSensorPlugin.getRotationSensor() != null){
            rotationSensorPlugin.registerListener();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (rotationSensorPlugin.getRotationSensor() != null){
            rotationSensorPlugin.unregisterListener();
        }
    }
}
