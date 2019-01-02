package com.example.root.projecttest.phote_select;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.root.projecttest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/02
 * desc:
 * version:1.0
 */
public class PhotoSelectActivity extends AppCompatActivity {

   private ViewPager viewPager;
   private PhotoSlideAdapter adapter;

   private List<ImageBean> beanList = new ArrayList<>();

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_select_photo);
      viewPager = findViewById(R.id.viewpager);
      viewPager.setAdapter(adapter = new PhotoSlideAdapter(this));
      adapter.addData(getList());
   }

   private List<ImageBean> getList() {
       for (int i = 0; i < 25; i++) {
           ImageBean bean = new ImageBean();
           bean.setUrl("http://guolin.tech/book.png");
           beanList.add(bean);
       }
       return beanList;
   }
}
