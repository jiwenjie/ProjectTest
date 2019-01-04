package com.example.root.projecttest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.root.projecttest.R;
import com.example.root.projecttest.phote_select.ImageBean;
import com.example.root.projecttest.phote_select.PhotoSlideAdapter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/02
 * desc: display many pictures progress, there is attention: 1, URL not allowed too long
 * 2: if multiple URLs are the same, the first picture progress is wrong
 * version:1.0
 */
public class MultiplePhotoSelectActivity extends AppCompatActivity {

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_select_photo);
      ViewPager viewPager = findViewById(R.id.photo_viewpager);

      /** multiply picture **/
      viewPager.setAdapter(new PhotoSlideAdapter(this, Arrays.asList("http://img2.imgtn.bdimg.com/it/u=2839214843,4179698749&fm=26&gp=0.jpg",
              "http://img4.imgtn.bdimg.com/it/u=3046672346,1551617892&fm=26&gp=0.jpg", "http://img2.imgtn.bdimg.com/it/u=3712876470,1404603246&fm=26&gp=0.jpg",
              "http://img4.imgtn.bdimg.com/it/u=3818256078,220262368&fm=26&gp=0.jpg", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1546488254918&di=a204d13b98ccfc2bfdebcf6805a6dadb&imgtype=0&src=http%3A%2F%2Fa2.att.hudong.com%2F51%2F76%2F01300537273186137563762257082.jpg")));

   }
}
