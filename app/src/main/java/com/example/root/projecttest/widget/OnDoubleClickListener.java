package com.example.root.projecttest.widget;

import android.view.MotionEvent;
import android.view.View;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/24
 * desc:
 * version:1.0
 */
public class OnDoubleClickListener implements View.OnTouchListener {

    private int count = 0;//点击次数
    private long firstClick = 0;//第一次点击时间
    private long secondClick = 0;//第二次点击时间
    /**
     * 两次点击时间间隔，单位毫秒
     */
    private final int totalTime = 300;


    private DoubleClickCallback mCallback;

    /**
     * 自定义回掉接口
     */
    public interface DoubleClickCallback {
        void onDoubleClick();
        void onOneClick();
    }

    public OnDoubleClickListener(DoubleClickCallback callback) {
        super();
        this.mCallback = callback;
    }

    /**
     * 触摸事件的处理
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            // 按下
            if (1 == count) {
                firstClick = System.currentTimeMillis();
//                if (mCallback != null) {
//                    mCallback.onOneClick();
//                }
            } else if (2 == count) {
                secondClick = System.currentTimeMillis();   // 记录第二次时间
                if (secondClick - firstClick < totalTime) { // 判断两次点击时间间隔是否在设定时间内
                    if (mCallback != null) {
                        mCallback.onDoubleClick();
                    }
                    count = 0;
                    firstClick = 0;
                } else {
                    firstClick = secondClick;
                    count = 1;
                }
                secondClick = 0;
            }
//            count ++;
        }
        return true;
    }
}














