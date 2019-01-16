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

public class TabMoveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

   private Context mContext;

   private List<String> beanList;

   public TabMoveAdapter(Context context, List<String> nameList) {
      this.mContext = context;
      this.beanList = nameList;
   }

   @NonNull
   @Override
   public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_move_stop_item, viewGroup, false));
   }

   @Override
   public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
      ViewHolder holder = (ViewHolder) viewHolder;
      holder.nameText.setText(beanList.get(i));
   }

   @Override
   public int getItemCount() {
      return beanList.size();
   }

   public class ViewHolder extends RecyclerView.ViewHolder {

      private TextView nameText;

      public ViewHolder(View itemView) {
         super(itemView);
         nameText = itemView.findViewById(R.id.activity_move_itemText);
      }
   }
}