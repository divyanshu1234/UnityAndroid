package ghostvr.unityandroidplugin;

import android.os.Bundle;
import com.unity3d.player.UnityPlayerActivity;

public class OverrideActivity extends UnityPlayerActivity {

    RotationSensorPlugin rotationSensorPlugin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        rotationSensorPlugin = new RotationSensorPlugin(this);

        ROSThreadPlugin rosThreadPlugin = new ROSThreadPlugin();
        new Thread(rosThreadPlugin).start();
    }

    /*
    @Override
    protected void onResume() {
        super.onResume();

        if (rotationSensorPlugin.isRotationSensorEnabled() && rotationSensorPlugin.getRotationSensor() != null){
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
    */
}
