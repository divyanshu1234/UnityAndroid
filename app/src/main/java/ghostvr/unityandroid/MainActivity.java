package ghostvr.unityandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ROSThread rosThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rosThread = new ROSThread();
        new Thread(rosThread).start();
    }

    public void getPoint(View view) {
        rosThread.getPointArray();
    }

    public void getQuat(View view) {
        rosThread.getQuatArray();
    }

}
