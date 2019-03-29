package com.example.root.projecttest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.root.projecttest.R;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/03/07
 * desc:
 * version:1.0
 */
public class EChartsActivity extends AppCompatActivity {

   private WebView webView;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_echarts);
      webView = findViewById(R.id.activity_echarts_webView);

      WebSettings oldWebSettings = webView.getSettings();
      oldWebSettings.setJavaScriptEnabled(true);
      oldWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
      oldWebSettings.setDefaultTextEncodingName("UTF-8");
      oldWebSettings.setSupportZoom(false);
      oldWebSettings.setBuiltInZoomControls(false);
      oldWebSettings.setBlockNetworkImage(false);
      oldWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
      webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
      webView.setWebChromeClient(new WebChromeClient());
      webView.setWebViewClient(new WebViewClient() {
         @Override
         public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            webView.loadUrl("javascript:");
         }
      });
   }
}
