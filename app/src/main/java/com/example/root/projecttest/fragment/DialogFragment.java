package com.example.root.projecttest.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.root.projecttest.R;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/04/28
 * desc:
 * version:1.0
 */
public class DialogFragment extends AppCompatDialogFragment {

   private TestFragment oneFragment = TestFragment.newInstance("1");
   private TestFragment twoFragment = TestFragment.newInstance("2");
   private TestFragment threeFragment = TestFragment.newInstance("3");

   private FragmentTransaction transaction;

   private boolean isFirst = true;

//   @Override
//   public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//      return new AlertDialog.Builder(getActivity())
//              .setTitle("I am DialogFragment show")
//              .setIcon(R.mipmap.ic_launcher).create();
//   }

   @Nullable
   @Override
   public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      return inflater.inflate(R.layout.dialog_fragment_test, container, false);
   }

   @Override
   public void onActivityCreated(@Nullable Bundle savedInstanceState) {
      super.onActivityCreated(savedInstanceState);

      getView().findViewById(R.id.fragment1).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Toast.makeText(getContext(), "one", Toast.LENGTH_SHORT).show();
            showFragment(1);
         }
      });
      getView().findViewById(R.id.fragment2).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Toast.makeText(getContext(), "two", Toast.LENGTH_SHORT).show();
            showFragment(2);
         }
      });
      getView().findViewById(R.id.fragment3).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Toast.makeText(getContext(), "three", Toast.LENGTH_SHORT).show();
            showFragment(3);
         }
      });
   }

   private void showFragment(int index) {
      transaction = getChildFragmentManager().beginTransaction();

      if (isFirst) {
         transaction.add(R.id.frameLyt, oneFragment, "one");
         transaction.add(R.id.frameLyt, twoFragment, "two");
         transaction.add(R.id.frameLyt, threeFragment, "three");

         isFirst = !isFirst;
      }

      transaction.hide(oneFragment);
      transaction.hide(twoFragment);
      transaction.hide(threeFragment);

      switch (index) {
         case 1: {
            transaction.show(oneFragment);
            break;
         }
         case 2: {
            transaction.show(twoFragment);
            break;
         }
         case 3: {
            transaction.show(threeFragment);
            break;
         }
      }
      transaction.commit();
   }
}
