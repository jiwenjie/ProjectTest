package com.example.root.projecttest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.projecttest.R;
import com.example.root.projecttest.other.PopBean;

import java.util.List;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/21
 * desc:
 * version:1.0
 */
public class recyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

   private Context context;
   private List<PopBean> beanList;

   public recyclerAdapter(Context context, List<PopBean> beanList) {
      this.context = context;
      this.beanList = beanList;
   }

   @NonNull
   @Override
   public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
      return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_bottom_pop_item, viewGroup, false));
   }

   @Override
   public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
      ViewHolder holder = (ViewHolder) viewHolder;
      holder.tvName.setText(beanList.get(position).getName());
   }

   @Override
   public int getItemCount() {
      return beanList.size();
   }

   class ViewHolder extends RecyclerView.ViewHolder {

      TextView tvName;

      public ViewHolder(View itemView) {
         super(itemView);
         tvName = itemView.findViewById(R.id.view_bottom_pop_item_textView);
      }
   }
}
