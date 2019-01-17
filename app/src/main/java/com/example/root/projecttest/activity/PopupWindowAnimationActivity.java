package com.example.root.projecttest.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.root.projecttest.R;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/17
 * desc:
 * version:1.0
 */
public class PopupWindowAnimationActivity extends AppCompatActivity {

   private Button btnPop;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_popupwindow_animation);
      btnPop = findViewById(R.id.btn_popup);

      btnPop.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            openPopupWindow(v);
         }
      });
   }

   private void openPopupWindow(View v) {
      View view = getLayoutInflater().inflate(R.layout.bottom_dialog, null);
      PopupWindow popupWindow = new PopupWindow(view, Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
      // 设置PopupWindow的背景
      popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
      // 设置PopupWindow是否能响应外部点击事件
      popupWindow.setOutsideTouchable(true);
      popupWindow.setFocusable(true);
      popupWindow.setClippingEnabled(false);
      popupWindow.setAnimationStyle(R.style.popupWindowAnimation);
      // 设置popupWindow的显示位置，此处是在手机屏幕底部且水平居中的位置
      popupWindow.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
   }
}












