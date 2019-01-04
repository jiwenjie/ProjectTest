package com.example.root.projecttest.phote_select;

import android.animation.ObjectAnimator;
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
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.root.projecttest.R;
import com.example.root.projecttest.glide.ProgressInterceptor;
import com.example.root.projecttest.glide.ProgressListener;
import com.example.root.projecttest.widget.CircleView;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/02
 * desc: photoSelect (when there are many pictures,
 *      left or right slider until view progress)
 * version:1.0
 */
public class GifSlideAdapter extends PagerAdapter {

    private Activity activity;
    private List<String> beanList;

    public GifSlideAdapter(Activity activity, List<String> beanList) {
        this.activity = activity;
        this.beanList = beanList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(activity).inflate(R.layout.activity_photoview_test, null);
        final PhotoView photoView = view.findViewById(R.id.photo_view);

        final CircleView circleView = view.findViewById(R.id.circleView);
        final TextView textView = view.findViewById(R.id.progressText);

        /** this listener representation click page and finish activity **/
        photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float v, float v1) {
                activity.finish();
            }
        });

        final String url = beanList.get(position);

        ProgressInterceptor.addListener(url, new ProgressListener() {
            @Override
            public void onProgress(final int progress) {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("PhotoSlide", "" + progress);

                        if (progress < 100) {
                            ObjectAnimator objectAnimator = ObjectAnimator.ofInt(circleView, "value", progress, progress + 1);
                            objectAnimator.setDuration(200);
                            objectAnimator.start();
                        } else {
                           ObjectAnimator objectAnimator = ObjectAnimator.ofInt(circleView, "value", progress);
                           objectAnimator.setDuration(200);
                           objectAnimator.start();
                        }
                        textView.setText(String.valueOf(progress + "%"));
//                        textView.setText(String.valueOf(progress + "%"));

                        if (progress == 100) {
                            circleView.setVisibility(View.GONE);
                            textView.setVisibility(View.GONE);
                        } else {
                            circleView.setVisibility(View.VISIBLE);
                            textView.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });

        /** 这里是 gif 图的操作 **/
        Glide.with(activity)
                .load(url)
                .asGif()
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.wallpaper)
                .into(new SimpleTarget<GifDrawable>() {
                    @Override
                    public void onResourceReady(GifDrawable resource, GlideAnimation<? super GifDrawable> glideAnimation) {
                        if (resource.isAnimated()) {        // load or process(处理) complete call change method
                            resource.setLoopCount(GifDrawable.LOOP_FOREVER);
                            resource.start();
                        }
                        photoView.setImageDrawable(resource);
                        ProgressInterceptor.removeListener(url);
                        textView.setVisibility(View.GONE);
                        circleView.setVisibility(View.GONE);
                    }
                });

        /**
         * this is a bug, said childView already have parent view, should remove it
         */
        container.addView(view);
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

















