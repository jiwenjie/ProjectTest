package com.example.root.projecttest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/28
 * desc:当使用 Chart 图表库的时候，在外层套用 HorizontalScrollView 会有滑动冲突，所以重写解决
 * version:1.0
 */
public class HrzScrollViewForChart extends HorizontalScrollView {

    private float startX;
    private float startY;
    private boolean isScroll;

    public HrzScrollViewForChart(Context context) {
        this(context, null);
    }

    public HrzScrollViewForChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                startX = ev.getX();
                startY = ev.getY();
                isScroll = false;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (isScroll) return true;

                float endX = ev.getX();
                float endY = ev.getY();
                float distanceX = Math.abs(endX - startX);
                float distanceY = Math.abs(endY - startY);
                if (distanceX > distanceY) {
                    isScroll = true;
                    return true;
                }

                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                isScroll = false;
                break;
            }
        }

        return super.onInterceptTouchEvent(ev);
    }
}
