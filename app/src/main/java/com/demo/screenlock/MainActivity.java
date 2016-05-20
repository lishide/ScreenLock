package com.demo.screenlock;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSetting;
    private Button btnLock;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        btnSetting = (Button) findViewById(R.id.btn_setting);
        btnSetting.setOnClickListener(this);
        btnLock = (Button) findViewById(R.id.btn_lock);
        btnLock.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_setting:
                intent = new Intent(context, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_lock:
                intent = new Intent(context, LockActivity.class);
                startActivity(intent);
                break;
        }
    }

}
