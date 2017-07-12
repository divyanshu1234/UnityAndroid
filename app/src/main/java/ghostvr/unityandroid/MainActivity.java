package ghostvr.unityandroid;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import ghostvr.unityandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private static final int REQUEST_CODE_DEBUG = 1001;
    private static final String PACKAGE_INTENT_DEBUG = "com.divyanshu.debugapp1.debugintent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PACKAGE_INTENT_DEBUG);
                intent.putExtra("callingKey1", "Data1");
                intent.putExtra("callingKey2", "Data2");
                startActivityForResult(intent, REQUEST_CODE_DEBUG);
            }
        });

        binding.button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d(getClass().toString(), "Motion Event");
                binding.button.callOnClick();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_DEBUG && resultCode == RESULT_OK){
            binding.textView1.setText(data.getStringExtra("resultKey1"));
            binding.textView2.setText(data.getStringExtra("resultKey2"));
        }
    }

    @Override
    public void onBackPressed() {
        long downTime = SystemClock.uptimeMillis();
        long eventTime = downTime + 100;
        float x = binding.button.getX() + binding.button.getWidth() / 2;
        float y = binding.button.getX() + binding.button.getWidth() / 2;
        MotionEvent event =  MotionEvent.obtain(downTime, eventTime, MotionEvent.ACTION_UP, x, y, 0);

        binding.button.dispatchTouchEvent(event);
    }
}