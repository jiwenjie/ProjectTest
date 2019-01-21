package com.example.root.projecttest.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.root.projecttest.R;
import com.example.root.projecttest.activity.DialogRvActivity;
import com.example.root.projecttest.other.PopBean;

import java.util.List;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/21
 * desc:
 * version:1.0
 */
public class ListBottomDialog extends BottomDialog {

   private String title;
   private List<PopBean> beanList;
   private ItemClickListener listener;

   public ListBottomDialog(Context context, String title, List<PopBean> beanList, ItemClickListener listener) {
      super(context, R.layout.dialog_rv_layout);
      this.title = title;
      this.beanList = beanList;
      this.listener = listener;
   }

   public interface ItemClickListener {
      void onItemClick(int index, PopBean popBean);
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      initView();
   }

   private void initView() {
      findViewById(R.id.view_bottom_pop_cancelText).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            dismiss();
         }
      });

      ListView listView = findViewById(R.id.view_bottom_pop_listView);

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
            position -= ((ListView) parent).getHeaderViewsCount();
            if (position >= 0) {
               listener.onItemClick(position, beanList.get(position));
               dismiss();
            }
         }
      });
   }

   private class MyAdapter extends BaseAdapter {

      private List<PopBean> beanList;
      private boolean singleChoice;

      MyAdapter(List<PopBean> beanList, boolean singleChoice) {
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
//         imageView.setImageResource(beanList.get(position).isCheck() ? R.drawable.ic_launcher_background : R.drawable.ic_launcher_foreground);
         textView.setText(beanList.get(position).getName());
         return view;
      }
   }
}
