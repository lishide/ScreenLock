package com.demo.screenlock;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class LockActivity extends AppCompatActivity {
    private Context context;
    private GestureLock lock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        context = this;
        SharedPreferences sp = getSharedPreferences("password", this.MODE_PRIVATE);
        final String password = sp.getString("password", "");
        lock = (GestureLock) findViewById(R.id.lockView);
        lock.setOnDrawFinishedListener(new GestureLock.OnDrawFinishedListener() {
            @Override
            public boolean OnDrawFinished(List<Integer> passList) {
                StringBuilder sb = new StringBuilder();
                for (Integer i : passList) {
                    sb.append(i);
                }
                if (sb.toString().equals(password)) {
                    Toast.makeText(context, "密码正确", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    Toast.makeText(context, "密码错误", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        });
    }
}
