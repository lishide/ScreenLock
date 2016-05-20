package com.demo.screenlock;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private GestureLock lock;
    private Button btnSave;
    private Button btnReset;
    private Context context;
    List<Integer> passList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        context = this;
        lock = (GestureLock) findViewById(R.id.lockView);

        lock.setOnDrawFinishedListener(new GestureLock.OnDrawFinishedListener() {
            @Override
            public boolean OnDrawFinished(List<Integer> passList) {
                if (passList.size() < 4) {
                    Toast.makeText(context, "密码不能少于4个点", Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    SettingActivity.this.passList = passList;
                    return true;
                }

            }

        });
        btnReset = (Button) findViewById(R.id.btn_reset);
        btnReset.setOnClickListener(this);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_reset:
                lock.resetPoints();
                break;
            case R.id.btn_save:
                if (passList != null) {
                    StringBuilder sb = new StringBuilder();
                    for (Integer i : passList) {
                        sb.append(i);
                    }
                    SharedPreferences sp = context.getSharedPreferences("password", context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("password", sb.toString());
                    editor.commit();

                    Toast.makeText(context, "保存完成", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }
}
