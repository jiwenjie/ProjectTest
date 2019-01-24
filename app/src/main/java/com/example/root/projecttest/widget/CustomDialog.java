package com.example.root.projecttest.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.projecttest.R;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/24
 * desc:
 * version:1.0
 */
public class CustomDialog extends Dialog {

   public static final int TYPE_NORMAL = 119;
   public static final int TYPE_EXPIRE = 120;
   public static final int TYPE_STOP = 121;

   private Context context;
   private int type = -1;

   private ImageView tipImage;
   private TextView titleText;
   private TextView subtitleText;
   private TextView actionText;

   public interface OnBottomClickListener {
      void onClick();
   }

   private OnBottomClickListener listener;

   public void setListener(OnBottomClickListener listener) {
      this.listener = listener;
   }

   public CustomDialog(Context context) {
      super(context, R.style.BottomDialog);
      this.context = context;
      init();
   }

   private void init() {
      setCancelable(true);
      setCanceledOnTouchOutside(true);
   }

   public void setType(int type) {
      this.type = type;
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.dialog_custome);
   }

   @Override
   public void show() {
      if (type == -1) return;
      super.show();
      switch (type) {
         case TYPE_NORMAL : {
            break;
         }
         case TYPE_EXPIRE: {
            break;
         }
         case TYPE_STOP: {
            break;
         }
      }
   }
}
