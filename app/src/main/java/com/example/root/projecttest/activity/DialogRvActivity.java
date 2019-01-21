package com.example.root.projecttest.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.projecttest.R;
import com.example.root.projecttest.other.PopBean;
import com.example.root.projecttest.widget.ListBottomDialog;

import java.util.Arrays;
import java.util.List;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/17
 * desc:
 * version:1.0
 */
public class DialogRvActivity extends AppCompatActivity {

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_bottom_view);

      findViewById(R.id.btn_popup).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            showDialog("请选择", Arrays.asList(new PopBean("0", "未完成"), new PopBean("1", "已完成")));
         }
      });
   }

   private void showDialog(String title, List<PopBean> beanList) {
      final ListBottomDialog dialog = new ListBottomDialog(this, title, beanList,
              new ListBottomDialog.ItemClickListener() {
         @Override
         public void onItemClick(int index, PopBean popBean) {
            Toast.makeText(getApplicationContext(), "获取的" + popBean.getName(), Toast.LENGTH_SHORT).show();
         }
      });
      dialog.show();
   }
}
