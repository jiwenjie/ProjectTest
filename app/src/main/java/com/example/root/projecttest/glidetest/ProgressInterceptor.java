package com.example.root.projecttest.glidetest;

import android.support.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/02
 * desc:
 * version:1.0
 */
public class ProgressInterceptor implements Interceptor {

   static final Map<String, ProgressListener> LISTENER_MAP = new HashMap<>();

   public static void addListener(String url, ProgressListener listener) {
      LISTENER_MAP.put(url, listener);
   }

   public static void removeListener(String url) {
      LISTENER_MAP.remove(url);
   }

   @Override
   public Response intercept(@NonNull Chain chain) throws IOException {
      Request request = chain.request();
      Response response = chain.proceed(request);
      String url = request.url().toString();
      ResponseBody body = response.body();
      Response newResponse = response.newBuilder().body(new ProgressResponseBody(url, body)).build();
      return newResponse;
   }
}














