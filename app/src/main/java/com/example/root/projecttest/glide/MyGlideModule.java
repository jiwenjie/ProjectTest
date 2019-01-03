package com.example.root.projecttest.glide;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import okhttp3.OkHttpClient;

import java.io.InputStream;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/02
 * desc:
 * version:1.0
 */
public class MyGlideModule implements GlideModule {

   @Override
   public void applyOptions(Context context, GlideBuilder builder) {
//      builder.setDiskCache(new ExternalCacheDiskCacheFactory(context));
   }

   /**
    * 然后在 registerComponents()方法中将我们刚刚创建的
    * OkHttpGlideUrlLoader和 OkHttpFetcher 注册到 Glide 当中，将原来的 HTTP 通讯组件给替换掉，
    * @param context
    * @param glide
    */
   @Override
   public void registerComponents(Context context, Glide glide) {
      OkHttpClient.Builder builder = new OkHttpClient.Builder();
//      builder.retryOnConnectionFailure(true);
      builder.addInterceptor(new ProgressInterceptor());
      OkHttpClient okHttpClient = builder.build();

      glide.register(GlideUrl.class, InputStream.class,
              new OkHttpGlideUrlLoader.Factory(okHttpClient));
   }
}
