package com.example.root.projecttest.widget;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/04
 * desc:圆形 View
 * version:1.0
 */
public class CircleView extends View {

   private int value = 50;
   private int total = 100;

   private Paint valuePaint;
   private Paint totalPaint;

   private int totalColor = Color.parseColor("#FFF0EFF5");
   private int valueColor = Color.parseColor("#FFF7A45A");

   private int circleWidth = 4;

   public CircleView(Context context) {
      super(context);
   }

   public CircleView(Context context, AttributeSet attr) {
      super(context, attr);
   }

   public CircleView(Context context, AttributeSet attr, int defStyle) {
      super(context, attr, defStyle);
   }

   private void initView() {
      valuePaint = new Paint();
      valuePaint.setStyle(Paint.Style.STROKE);
      valuePaint.setStrokeWidth(circleWidth);
      valuePaint.setColor(valueColor);
      valuePaint.setAntiAlias(true);

      totalPaint = new Paint();
      totalPaint.setStyle(Paint.Style.STROKE);
      totalPaint.setStrokeWidth(circleWidth);
      totalPaint.setColor(totalColor);
      totalPaint.setAntiAlias(true);
   }

   public void setValue(int value) {
      setValue(value, 0);
   }

   public void setValue(int value, long duration) {
      ValueAnimator valueAnimator = ValueAnimator.ofInt(this.value, value);
      valueAnimator.setDuration(duration);
      valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
         @Override
         public void onAnimationUpdate(ValueAnimator animation) {
            CircleView.this.value = (int) animation.getAnimatedValue();
            invalidate();
         }
      });
      valueAnimator.start();
   }

   public void setValue(int value, @ColorInt int valueColor, @ColorInt int totalColor) {
      this.value = value;
      this.valueColor = valueColor;
      this.totalColor = totalColor;
      invalidate();
   }

   public int getValue() {
      return value;
   }

   public void setCircleWidth(int circleWidth) {
      this.circleWidth = circleWidth;
      invalidate();
   }

   @SuppressLint("DrawAllocation")
   @Override
   protected void onDraw(Canvas canvas) {
      super.onDraw(canvas);
      initView();

      int center = getWidth() / 2;    // 获取圆心的 x 坐标
      int radius = center - circleWidth;  // 圆环半径
      RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius); // 定义圆弧的形状和大小的界限

      canvas.drawArc(oval, 0, 360, false, totalPaint);
      canvas.drawArc(oval, -90, 360 * value / total, false, valuePaint);
   }
}
