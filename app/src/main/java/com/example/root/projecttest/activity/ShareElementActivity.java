package com.example.root.projecttest.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.root.projecttest.R;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/02/25
 * desc:
 * version:1.0
 */
public class ShareElementActivity extends AppCompatActivity {

   private ImageView imageView;

   public static final String TRANSTION_NAME_SHARE = "share_element";

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_share_element);
      imageView = findViewById(R.id.activity_shareImg);
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
         imageView.setTransitionName(TRANSTION_NAME_SHARE);
      }
      imageView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent intent = new Intent(getBaseContext(), ReceiveShareActivity.class);
            ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(ShareElementActivity.this, imageView, TRANSTION_NAME_SHARE);
            startActivity(intent, compat.toBundle());
         }
      });
   }

   @Override
   public void onBackPressed() {

      super.onBackPressed();
   }
}
