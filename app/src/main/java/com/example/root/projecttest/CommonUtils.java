package com.example.root.projecttest;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/24
 * desc:
 * version:1.0
 */
public class CommonUtils {

   public static GestureDetector setDoubleAndOneClick(Context context) {
      return new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
         @Override
         public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
         }

         @Override
         public boolean onDoubleTap(MotionEvent e) {
            return false;
         }
      });
   }

   /**
    * 设置 View 的左右摇晃的动画
    */
   public static void shakeAnimation(View targetView) {
      shakeAnimation(targetView, 4f, 1500);
   }

   public static void shakeAnimation(View targetView, Float angle, long duration) {
      ObjectAnimator shakeAnimator = ObjectAnimator.ofFloat(targetView, "rotation", 0, angle, -angle, angle, -angle, 0);
      targetView.setPivotX(targetView.getWidth() / 2);
      targetView.setPivotY(targetView.getHeight());
      shakeAnimator.setDuration(duration);
      shakeAnimator.start();
   }
}
