package com.example.nice.everywhere.ui.main.guideactivity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.example.nice.everywhere.R;
import com.example.nice.everywhere.ui.main.activity.LoginActivity;
import com.example.nice.everywhere.ui.main.activity.MainActivity;

public class SplashActivity extends AppCompatActivity {


    public static final int time = 2000;
    public static final int GOHMOE = 1000;
    public static final int GOGUILD = 1001;
    public static final int GOMINE = 1002;

    private boolean isFistIn = false;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GOHMOE:
                    goHome();
                    break;
                case GOGUILD:
                    goGuild();
                    break;
            }
        }
    };


    private void goHome() {
        SharedPreferences success = getSharedPreferences("isSuccess", MODE_PRIVATE);
        boolean isSuccess = success.getBoolean("isSuccess", true);
        if (!isSuccess) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        finish();
    }

    private void goGuild() {
        Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initPer();
        init();
    }

    private void initPer() {
        /**
         * 动态获取权限，Android 6.0 新特性，一些保护权限，除了要在AndroidManifest中声明权限，还要使用如下代码动态获取
         */
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                    return;
                }
            }
        }
    }

    private void init() {
        SharedPreferences preferences = getSharedPreferences("jike", MODE_PRIVATE);
        isFistIn = preferences.getBoolean("isFistIn", true);
        if (!isFistIn) {
            mHandler.sendEmptyMessageDelayed(GOHMOE, time);
        } else {
            mHandler.sendEmptyMessageDelayed(GOGUILD, time);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFistIn", false);
            editor.commit();
        }

    }

}
