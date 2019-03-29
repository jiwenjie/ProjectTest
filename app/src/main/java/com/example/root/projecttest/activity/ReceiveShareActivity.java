package com.example.root.projecttest.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.root.projecttest.R;

import static com.example.root.projecttest.activity.ShareElementActivity.TRANSTION_NAME_SHARE;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/02/25
 * desc:
 * version:1.0
 */
public class ReceiveShareActivity extends AppCompatActivity {

   private ImageView imageView;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_receive_share);
      imageView = findViewById(R.id.receiveImg);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
         imageView.setTransitionName(TRANSTION_NAME_SHARE);
      }
   }

   @Override
   public void onBackPressed() {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
         finishAfterTransition();
      }
      super.onBackPressed();
   }
}
