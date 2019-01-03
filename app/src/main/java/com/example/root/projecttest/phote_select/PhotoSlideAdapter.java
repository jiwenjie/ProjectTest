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
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.example.root.projecttest.R;
import com.example.root.projecttest.glide.ProgressInterceptor;
import com.example.root.projecttest.glide.ProgressListener;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

import java.util.List;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/02
 * desc: photoSelect (when there are many pictures,
 *      left or right slider until view progress)
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
                        textView.setText(String.valueOf(progress));
                    }
                });
            }
        });

        /** 这里是 gif 图的操作 **/
        Glide.with(activity)
                .load(url)
                .asGif()
                .skipMemoryCache(true)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.wallpaper)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .into(photoView);
                .into(new ImageViewTarget<GifDrawable>(photoView) {
                    @Override
                    protected void setResource(GifDrawable resource) {
                        resource.start();
                    }

                    @Override
                    public void onResourceReady(GifDrawable resource, GlideAnimation<? super GifDrawable> glideAnimation) {
                        super.onResourceReady(resource, glideAnimation);
                        ProgressInterceptor.removeListener(url);
                    }
                });

        /** 此处是正常的图片操作，不包括 gif **/
//        Glide.with(activity)
//                .load(url)
//                .skipMemoryCache(true)  // test use
//                .placeholder(R.drawable.placeholder)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)  // test use
//                .into(new GlideDrawableImageViewTarget(photoView) {
//                    @Override
//                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
//                        super.onResourceReady(resource, animation);
//                        ProgressInterceptor.removeListener(url);
//                    }
//                });

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

















