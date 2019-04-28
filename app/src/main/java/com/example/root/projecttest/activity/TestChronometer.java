package com.example.root.projecttest.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Chronometer;

import com.example.root.projecttest.R;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/04/16
 * desc:
 * version:1.0
 */
public class TestChronometer extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_chronometer);
        //在onCreate()方法中开始倒计时
        Chronometer chronometer = findViewById(R.id.test_chronometer);
        long startTime = SystemClock.elapsedRealtime();
        //每次onCreate()方法都会重新设置base
        chronometer.setBase(startTime);
        chronometer.start();
    }
}
