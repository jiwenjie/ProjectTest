package com.example.root.projecttest.glidetest;

import android.util.Log;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.*;

import java.io.IOException;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/02
 * desc:
 * version:1.0
 */
public class ProgressResponseBody extends ResponseBody {

   private static final String TAG = "ProgressResponseBody";

   private BufferedSource bufferedSource;
   private ResponseBody responseBody;
   private ProgressListener listener;

   public ProgressResponseBody(String url, ResponseBody responseBody) {
      this.responseBody = responseBody;
      listener = ProgressInterceptor.LISTENER_MAP.get(url);
   }

   @Override
   public MediaType contentType() {
      return responseBody.contentType();
   }

   @Override
   public long contentLength() {
      return responseBody.contentLength();
   }

   @Override
   public BufferedSource source() {
      if (bufferedSource == null) {
         bufferedSource = Okio.buffer(new ProgressSource(responseBody.source()));
      }
      return bufferedSource;
   }

   private class ProgressSource extends ForwardingSource {
      long totalBytesRead = 0;
      int currentProgress;

      ProgressSource(Source source) {
         super(source);
      }

      @Override
      public long read(Buffer sink, long byteCount) throws IOException {
         long bytesRead = super.read(sink, byteCount);
         long fullLength = responseBody.contentLength();
         if (bytesRead == -1) {
            totalBytesRead = fullLength;
         } else {
            totalBytesRead += bytesRead;
         }
         int progress = (int) (100f * totalBytesRead / fullLength);
//         Log.d(TAG, "download progress is " + progress);
         if (listener != null && progress != currentProgress) {
            listener.onProgress(progress);
         }
         if (listener != null && totalBytesRead == fullLength) {
            listener = null;
         }

         currentProgress = progress;

         return bytesRead;
      }
   }
}











