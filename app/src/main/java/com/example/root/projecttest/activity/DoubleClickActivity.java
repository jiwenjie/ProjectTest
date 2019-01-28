package com.example.root.projecttest.activity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.root.projecttest.CommonUtils;
import com.example.root.projecttest.R;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/24
 * desc:
 * version:1.0
 */
public class DoubleClickActivity extends AppCompatActivity {

   private ImageView clickImg;

   private Long mExitTime = 0L;

   private RadioButton viewBtn;
   private RadioButton valueBtn;

   private Animation animation;

   @SuppressLint("ClickableViewAccessibility")
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_double_click);
      clickImg = findViewById(R.id.Img);
      viewBtn = findViewById(R.id.viewBtn);
      valueBtn = findViewById(R.id.valueBtn);


      clickImg.setOnTouchListener(new View.OnTouchListener() {
         @Override
         public boolean onTouch(View v, MotionEvent event) {
            return CommonUtils.setDoubleAndOneClick(getBaseContext()).onTouchEvent(event);
         }
      });


      clickImg.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if (System.currentTimeMillis() - mExitTime <= 300) {
               Log.e("Test", "双击事件");
               // 认为是双击，否则是单击
               if (viewBtn.isChecked()) {
                  animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.double_rotate);
                  clickImg.startAnimation(animation);
               } else {
                  ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(clickImg, "rotation", 0, 4, -4, 4, -4, 4, 0);
                  clickImg.setPivotX(clickImg.getWidth() / 2);
                  clickImg.setPivotY(clickImg.getHeight());
                  objectAnimator.setDuration(1000);
                  objectAnimator.start();
               }
            } else {
               Log.e("Test", "单击事件");
               mExitTime = System.currentTimeMillis();
//                    Toast.makeText(getApplicationContext(), "点击", Toast.LENGTH_SHORT).show();
            }
         }
      });
   }
}
