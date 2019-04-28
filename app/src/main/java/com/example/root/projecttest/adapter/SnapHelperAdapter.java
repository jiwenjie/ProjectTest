package com.example.root.projecttest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.projecttest.R;

import java.util.List;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/04/16
 * desc:
 * version:1.0
 */
public class SnapHelperAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

   private Context mContext;
   private List<String> beanList;

   public SnapHelperAdapter(Context context) {
      this.mContext = context;
   }

   public void setData(List<String> beanList) {
      this.beanList = beanList;
      notifyDataSetChanged();
   }

   @NonNull
   @Override
   public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
      return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_snaphelper_item, viewGroup, false));
   }

   @Override
   public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
      ViewHolder holder = (ViewHolder) viewHolder;
      holder.textView.setText(beanList.get(position));
   }

   @Override
   public int getItemCount() {
      return beanList.size();
   }

   private class ViewHolder extends RecyclerView.ViewHolder {

      private TextView textView;

      public ViewHolder(View itemView) {
         super(itemView);
         textView = itemView.findViewById(R.id.activity_numText);
      }
   }
}
