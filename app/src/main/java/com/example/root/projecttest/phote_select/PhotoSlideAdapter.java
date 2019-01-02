package com.example.root.projecttest.phote_select;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.root.projecttest.R;
import com.example.root.projecttest.glidetest.ProgressInterceptor;
import com.example.root.projecttest.glidetest.ProgressListener;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/02
 * desc:
 * version:1.0
 */
public class PhotoSlideAdapter extends PagerAdapter {

    private Activity activity;

    private String url;
//    private static final int[] sDrawables = {R.drawable.wallpaper, R.drawable.wallpaper, R.drawable.wallpaper,
//            R.drawable.wallpaper, R.drawable.wallpaper, R.drawable.wallpaper};

    private List<ImageBean> beanList = new ArrayList<>();
    private TextView textView;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x100: {
                    textView.setText(String.valueOf(msg.arg1));
                    textView.setVisibility(View.VISIBLE);
//                    textView.setVisibility(View.GONE);
                    break;
                }
                case 0x1: {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(String.valueOf(msg.arg1));
                   Log.d("PhotoSlideAdapter", "" + msg.arg1);
                    break;
                }
            }
        }
    };

    public PhotoSlideAdapter(Activity activity) {
        this.activity = activity;
    }

    public void addData(List<ImageBean> list) {
        if (list != null) {
            beanList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(activity).inflate(R.layout.activity_photoview, null);
        PhotoView photoView = view.findViewById(R.id.photo_view);
        textView = view.findViewById(R.id.progressText);
//        PhotoView photoView = new PhotoView(container.getContext());
        ImageBean bean = beanList.get(position);
        url = bean.getUrl();

        photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float v, float v1) {
                activity.finish();
            }
        });

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
                handler.sendMessageDelayed(message, 500);
            }
        });

        Glide.with(activity)
                .load(url)
                .skipMemoryCache(true)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(new GlideDrawableImageViewTarget(photoView) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        ProgressInterceptor.removeListener(url);
                    }
                });

        /**
         * this is a bug, said childView already have parent view, should remove it
         */
        if (photoView.getParent() != null || textView.getParent() != null) {
            ((ViewGroup) photoView.getParent()).removeView(photoView);
            ((ViewGroup) textView.getParent()).removeView(textView);
        }
        container.addView(textView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return photoView;
    }

    @Override
    public int getCount() {
        return beanList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, Object object) {
        return view == object;
    }
}

















