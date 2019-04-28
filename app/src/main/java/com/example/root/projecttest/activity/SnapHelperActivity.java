package com.example.root.projecttest.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.root.projecttest.R;
import com.example.root.projecttest.adapter.SnapHelperAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/04/16
 * desc:列表左右滑动居中的例子，熟悉对 SnapHelper 的使用
 * version:1.0
 */
public class SnapHelperActivity extends AppCompatActivity {

   private TextView currentNum;
   private RecyclerView recyclerView;
   private SnapHelperAdapter adapter;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_snaphelper);
      initView();
   }

   private void initView() {
      currentNum = findViewById(R.id.currentNum);
      recyclerView = findViewById(R.id.recyclerView);

      LinearSnapHelper helper = new LinearSnapHelper();
      helper.attachToRecyclerView(recyclerView);
      LinearLayoutManager manager = new LinearLayoutManager(getBaseContext());
      manager.setOrientation(LinearLayoutManager.HORIZONTAL);
      recyclerView.setLayoutManager(manager);
      recyclerView.setAdapter(adapter = new SnapHelperAdapter(getBaseContext()));
      adapter.setData(getFakeData());
      recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
         @Override
         public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            final int lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            final int firstCumple = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
            Log.e("tttt", lastVisibleItem + "");
            Log.e("tttts", firstCumple + "");
            currentNum.post(new Runnable() {
               @Override
               public void run() {
                  currentNum.setText(lastVisibleItem);
               }
            });
         }
      });
//      recyclerView.getLayoutManager().
   }

   private List<String> getFakeData() {
      List<String> list = new ArrayList<>();
      for (int i = 0; i < 15; i++) {
         String name = "test" + (i + 1);
         list.add(name);
      }
      return list;
   }
}
