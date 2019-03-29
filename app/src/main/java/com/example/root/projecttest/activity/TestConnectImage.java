package com.example.root.projecttest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.root.projecttest.R;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/02/08
 * desc:
 * version:1.0
 */
public class TestConnectImage extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_connect);

        Button button = findViewById(R.id.btnStart);
        ImageView imageView = findViewById(R.id.testImg);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Glide.with(this)
//                        .load("http://192.168.1.103:8080/I:/Dev-Cpp/mmexport1519716601681.jpg")
//                        .into(imageView);
            }
        });

    }
}
