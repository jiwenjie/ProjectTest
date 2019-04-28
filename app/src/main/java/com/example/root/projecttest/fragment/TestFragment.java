package com.example.root.projecttest.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.projecttest.R;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/04/28
 * desc:
 * version:1.0
 */
public class TestFragment extends Fragment {

   public static TestFragment newInstance(String name) {
      TestFragment fragment = new TestFragment();
      Bundle bundle = new Bundle();
      bundle.putString("name", name);
      fragment.setArguments(bundle);
      return fragment;
   }

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_test, container, false);
   }

   @Override
   public void onActivityCreated(@Nullable Bundle savedInstanceState) {
      super.onActivityCreated(savedInstanceState);
      String name = getArguments().getString("name");
      ((TextView) getView().findViewById(R.id.testText)).setText(name);
   }
}
