package com.example.root.projecttest.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.root.projecttest.R;
import com.example.root.projecttest.fragment.DialogFragment;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/04/28
 * desc:
 * version:1.0
 */
public class DialogFragmentActivity extends AppCompatActivity {

   private TextView button;

   private DialogFragment fragment;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_dialog_fragment);

      button = findViewById(R.id.btn_dialogFragment);
      button.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            fragment = new DialogFragment();
            fragment.show(getSupportFragmentManager(), "dialogFragmentText");
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .add(R.id.fragmentShow, fragment)
//                    .commit();
         }
      });
   }
}
