package ghostvr.unityandroidplugin;

/**
 * Created by Divyanshu on 6/7/17.
 */

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.util.Log;

import com.unity3d.player.UnityPlayerActivity;

public class RotationSensorPlugin implements SensorEventListener {
    private UnityPlayerActivity upa;
    private SensorManager sensorManager;
    private Sensor rotationSensor;
    private float[] rotationVector;
    private float[] rotationMatrix;
    private float[] orientationMatrix;
    private float[] quatMatrix;


    public RotationSensorPlugin(UnityPlayerActivity unityPlayerActivity) {
        upa = unityPlayerActivity;
        sensorManager = (SensorManager) upa.getSystemService(Context.SENSOR_SERVICE);
        rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        rotationVector = new float[3];
        rotationMatrix = new  float[16];
        orientationMatrix = new float[3];
        quatMatrix = new float[4];
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR){
            System.arraycopy(event.values, 0, rotationVector, 0, rotationVector.length);
        }

        Log.d("Unity Sensor Plugin", String.valueOf(SystemClock.currentThreadTimeMillis()));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void registerListener() {
        sensorManager.registerListener(this, rotationSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    public void unregisterListener() {
        sensorManager.unregisterListener(this);
    }

    public Sensor getRotationSensor() {
        return rotationSensor;
    }

    public float[] getRotationVector(){
        return rotationVector;
    }

    public float[] getRotationMatrix(){
        SensorManager.getRotationMatrixFromVector(rotationMatrix, rotationVector);
        return rotationMatrix;
    }

    public float[] getOrientationMatrix(){
        getRotationMatrix();
        SensorManager.getOrientation(rotationMatrix, orientationMatrix);
        return orientationMatrix;
    }

    public float[] getQuatMatrix(){
        SensorManager.getQuaternionFromVector(quatMatrix, rotationVector);
        return quatMatrix;
    }
}
