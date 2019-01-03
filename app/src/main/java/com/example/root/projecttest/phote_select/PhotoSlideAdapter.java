package com.example.root.projecttest.phote_select;

import android.app.Activity;
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
    private List<String> beanList;

    public PhotoSlideAdapter(Activity activity, List<String> beanList) {
        this.activity = activity;
        this.beanList = beanList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(activity).inflate(R.layout.activity_photoview, null);
        PhotoView photoView = view.findViewById(R.id.photo_view);
        final TextView textView = view.findViewById(R.id.progressText);
        photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float v, float v1) {
                activity.finish();
            }
        });


        final String bean = beanList.get(position);

        ProgressInterceptor.addListener(bean, new ProgressListener() {
            @Override
            public void onProgress(final int progress) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("PhotoSlide", "" + progress);
                        textView.setText(String.valueOf(progress));
                    }
                });
            }
        });

        Glide.with(activity)
                .load(bean)
                .skipMemoryCache(true)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(new GlideDrawableImageViewTarget(photoView) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        ProgressInterceptor.removeListener(bean);
                    }
                });

        /**
         * this is a bug, said childView already have parent view, should remove it
         */
//        if (view.getParent() != null) {
//            ((ViewGroup) view.getParent()).removeView(view);
//        }
        container.addView(view);
//        container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return view;
    }

    @Override
    public int getCount() {
        return beanList == null ? 0 : beanList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, Object object) {
        return view == object;
    }
}

















