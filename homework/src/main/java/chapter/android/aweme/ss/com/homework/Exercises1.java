package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * 作业1：
 * Logcat在屏幕旋转的时候 #onStop() #onDestroy()会展示出来
 * 但我们的 mLifecycleDisplay 由于生命周期的原因(Tips:执行#onStop()之后，UI界面我们是看不到的)并没有展示
 * 在原有@see Exercises1 基础上如何补全它，让其跟logcat的展示一样?
 * <p>
 * Tips：思考用比Activity的生命周期要长的来存储？  （比如：application、static变量）
 */
public class Exercises1 extends AppCompatActivity {

    private String TAG = "Exercises1";
    private TextView textView;
    private static String logs = "";

    private void addLog(String log) {
        if(textView == null) {
            Log.d(TAG, "Error at add log.");
        } else {
            logs += log + '\n';
            Log.d(TAG, log);
            textView.setText(logs);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        textView = findViewById(R.id.logs);
        addLog("OnCreate");
    }

    @Override
    protected void onStop() {
        addLog("OnStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        addLog("OnDestroy");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        addLog("OnResume");
        super.onResume();
    }
}
