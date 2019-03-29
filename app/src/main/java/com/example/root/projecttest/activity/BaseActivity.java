package com.example.root.projecttest.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import kotlin.jvm.JvmOverloads;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/02/04
 * desc:
 * version:1.0
 */
public class BaseActivity extends AppCompatActivity {

   /** dynamic apply permissions related methods -- start **/
   public static final int REQUEST_PERMISSION = 1520;

   public void reqPermissionResult(boolean isAllGranted, @NonNull String[] permissions, int reqCode) {

   }

   protected void reqPermissions(@NonNull String[] permissions) {
      reqPermissions(permissions, REQUEST_PERMISSION);
   }

   protected Boolean reqPermissions(@NonNull String[] permissions, int reqCode) {
      List<String> needReqPermissionList = new ArrayList<>();
      boolean hasNoAskPermission = false;
      for (String permission : needReqPermissionList) {
         if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            needReqPermissionList.add(permission);
            /** if user check (don't ask again next time)  */
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
               hasNoAskPermission = true;
            }
         }
      }
      if (needReqPermissionList.size() == 0) {
         return true;
      }
      if (hasNoAskPermission) {
         if (permissions.length == 1) {
            /** if only one permission, indication need execute an action  */
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("当前操作所需权限已被禁止。\n\n请点击\"设置\"-\"权限\"-打开所需权限。")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                          Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                          intent.setData(Uri.parse("package:$packageName"));
                          startActivity(intent);
                       }
                    })
                    .show();
            return false;
         }
      }
      ActivityCompat.requestPermissions(this,
              (String[]) Objects.requireNonNull(needReqPermissionList.toArray()),
              reqCode);
      return false;
   }

   @Override
   public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults);
      boolean isAllGranted = true;
      for (int grantResult : grantResults) {
         if (grantResult != PackageManager.PERMISSION_GRANTED) {
            isAllGranted = false;
         }
      }
      reqPermissionResult(isAllGranted, permissions, requestCode);
   }

   /** dynamic apply permissions related methods -- end **/

}
