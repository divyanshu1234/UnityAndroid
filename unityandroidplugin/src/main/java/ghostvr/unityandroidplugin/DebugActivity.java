package ghostvr.unityandroidplugin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class DebugActivity extends AppCompatActivity {

    private String LOG_TAG = getClass().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);

        Log.d(LOG_TAG, "Started DebugActivity");
    }
}
