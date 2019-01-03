package com.example.root.projecttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.root.projecttest.activity.MultiplePhotoSelectActivity;
import com.example.root.projecttest.activity.OnePictureProgressActivity;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/02
 * desc:
 * version:1.0
 */
public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1: {
                startActivity(new Intent(this, OnePictureProgressActivity.class));
                break;
            }
            case R.id.btn2: {
                startActivity(new Intent(this, MultiplePhotoSelectActivity.class));
            }
        }
    }
}
