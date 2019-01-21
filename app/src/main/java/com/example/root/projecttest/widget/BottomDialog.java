package com.example.root.projecttest.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.root.projecttest.R;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/21
 * desc:底部弹窗的基础 dialog
 * version:1.0
 */
public class BottomDialog extends Dialog {

   @LayoutRes
   private int layoutId;

   public BottomDialog(Context context, @LayoutRes int layoutId) {
      super(context, R.style.BottomDialog);
      this.layoutId = layoutId;
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(layoutId);

      Window dialogWindow = getWindow();
      dialogWindow.setGravity(Gravity.BOTTOM);
      WindowManager.LayoutParams lp = dialogWindow.getAttributes();
      lp.width = WindowManager.LayoutParams.MATCH_PARENT;
      lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
      dialogWindow.setAttributes(lp);
   }
}
