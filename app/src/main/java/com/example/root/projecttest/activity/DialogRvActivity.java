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

import com.example.root.projecttest.R;
import com.example.root.projecttest.other.PopBean;

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

   private void showDialog(
                           String title,
                           List<PopBean> beanList) {
      final Dialog dialog = new Dialog(this, R.style.BottomDialog);

      View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_rv_layout, null);
      dialog.setContentView(view);

      view.findViewById(R.id.view_bottom_pop_cancelText).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            dialog.dismiss();
         }
      });

      ListView listView = view.findViewById(R.id.view_bottom_pop_listView);

      if (!TextUtils.isEmpty(title)) {
         View header = getLayoutInflater().inflate(R.layout.view_bottom_pop_header, null);
         TextView titleText = header.findViewById(R.id.view_bottom_pop_header_titleTex);
         titleText.setText(title);
         listView.addHeaderView(header);
      }

      listView.setAdapter(new MyAdapter(beanList, true));
      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

         }
      });

      Window dialogWindow = dialog.getWindow();
      //设置dialog的显示位置
      dialogWindow.setGravity(Gravity.BOTTOM);
      WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
      lp.width = WindowManager.LayoutParams.MATCH_PARENT;
      lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

      lp.alpha = 9f; // 透明度
      dialogWindow.setAttributes(lp);
      dialog.show();
   }

   private class MyAdapter extends BaseAdapter {

      private List<PopBean> beanList;
      private boolean singleChoice;

      public MyAdapter(List<PopBean> beanList, boolean singleChoice) {
         this.beanList = beanList;
         this.singleChoice = singleChoice;
      }

      @Override
      public int getCount() {
         return beanList == null ? 0 : beanList.size();
      }

      @Override
      public Object getItem(int position) {
         return null;
      }

      @Override
      public long getItemId(int position) {
         return 0;
      }

      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
         @SuppressLint("ViewHolder") View view = View.inflate(parent.getContext(), R.layout.view_bottom_pop_item, null);
         ImageView imageView = view.findViewById(R.id.view_bottom_pop_item_imageView);
         TextView textView = view.findViewById(R.id.view_bottom_pop_item_textView);
         imageView.setVisibility(singleChoice ? View.GONE : View.VISIBLE);
         imageView.setImageResource(beanList.get(position).isCheck() ? R.drawable.ic_launcher_background : R.drawable.ic_launcher_foreground);
         textView.setText(beanList.get(position).getName());
         return view;
      }
   }
}
