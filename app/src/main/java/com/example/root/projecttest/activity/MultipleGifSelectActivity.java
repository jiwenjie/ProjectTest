package com.example.root.projecttest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.root.projecttest.R;
import com.example.root.projecttest.phote_select.GifSlideAdapter;
import com.example.root.projecttest.phote_select.PhotoSlideAdapter;

import java.util.Arrays;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/04
 * desc:多个 gif 图片显示的加载情况
 * version:1.0
 */
public class MultipleGifSelectActivity extends AppCompatActivity {

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_select_gif);

      ViewPager viewPager = findViewById(R.id.gif_viewpager);

      /** multiply gif **/
      viewPager.setAdapter(new GifSlideAdapter(this, Arrays.asList(
              "http://img.soogif.com/YVv7VNrzYSxwPSZOr9uIrs2c0F8tASgU.gif_s400x0",
              "http://img.soogif.com/kjjnS1BvBZEdps1XrVTC0JlrkgVbqhiQ.gif_s400x0",
              "http://img.soogif.com/mn4Y8K0SLfCkE7tbOQU1SAFxP2zNeK6J.gif_s300x0",
              "http://img.soogif.com/jU6YkPAJ6rhYxWm0LOBgn2zELCG9xqp7.gif_s300x0",
              "http://guolin.tech/test.gif")));

   }
}
