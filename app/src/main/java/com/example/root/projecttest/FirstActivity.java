package com.example.root.projecttest;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.example.root.projecttest.glidetest.ProgressInterceptor;
import com.example.root.projecttest.glidetest.ProgressListener;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/02
 * desc:
 * version:1.0
 */
public class FirstActivity extends AppCompatActivity {

    String url = "http://guolin.tech/book.png";

    Button button;
    ImageView image;
    TextView textView;

    ProgressDialog progressDialog;

    Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btn_action);
        image = findViewById(R.id.image);
        textView = findViewById(R.id.progressText);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0x1: {
                        textView.setText(String.valueOf(msg.arg1));
                        break;
                    }
                    case 0x100: {
                        textView.setText(String.valueOf(msg.arg1));
                        textView.setVisibility(View.GONE);
                        break;
                    }
                }
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });
    }

    public void loadImage() {

        ProgressInterceptor.addListener(url, new ProgressListener() {
            @Override
            public void onProgress(int progress) {

                Message message = Message.obtain();
                message.arg1 = progress;
                if (progress == 100) {
                    message.what = 0x100;
                } else {
                    message.what = 0x1;
                }
                handler.sendMessage(message);

//            progressDialog.setProgress(progress);
            }
        });
        Glide.with(this)
                .load(url)
                .skipMemoryCache(true)
                .placeholder(R.drawable.placeholder)
                .dontTransform()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .into(new GlideDrawableImageViewTarget(image) {
                    @Override
                    public void onLoadStarted(Drawable placeholder) {
                        super.onLoadStarted(placeholder);
//                    progressDialog.show();
                    }

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
//                    progressDialog.dismiss();
                        ProgressInterceptor.removeListener(url);
                    }
                });
    }
}
