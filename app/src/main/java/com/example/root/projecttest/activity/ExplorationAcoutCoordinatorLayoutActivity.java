package com.example.root.projecttest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.root.projecttest.R;
import com.example.root.projecttest.adapter.TabMoveAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/16
 * desc:
 * version:1.0
 */
public class ExplorationAcoutCoordinatorLayoutActivity extends AppCompatActivity {

   private RecyclerView recyclerView;
   private List<String> nameList = new ArrayList<>();


   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_exploration_for_coordinatorlayout);

      recyclerView = findViewById(R.id.recyclerView);

      TabMoveAdapter adapter = new TabMoveAdapter(this, getStringName());
      recyclerView.setLayoutManager(new LinearLayoutManager(this));
      recyclerView.setAdapter(adapter);
   }

   private List<String> getStringName() {
      for (int i = 0; i < 40; i++) {
         nameList.add("name" + i);
      }
      return nameList;
   }
}
