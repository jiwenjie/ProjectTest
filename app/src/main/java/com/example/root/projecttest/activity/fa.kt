package com.example.root.projecttest.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import java.util.ArrayList

/**
 *  author:Jiwenjie
 *  email:278630464@qq.com
 *  time:2019/02/04
 *  desc:
 *  version:1.0
 */
class fa : AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
   }

   /** dynamic apply permissions related methods -- start **/

   private val REQUEST_PERMISSION = 1520

   protected open fun reqPermissionResult(isAllGranted: Boolean, permission: Array<String>, reqCode: Int) {

   }

   /**
    * @param permissions
    * @param reqCode
    * @return true is indicate all permissions have been authorization(授权)
    * and false indicate some permissions have not been authorization
    */
   @JvmOverloads
   fun reqPermissions(permissions: Array<String>, reqCode: Int = REQUEST_PERMISSION): Boolean {
      val needReqPermissionList = ArrayList<String>()
      var hasNoAskPermission = false
      for (permission in needReqPermissionList) {
         if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            needReqPermissionList.add(permission)
            /** if user check (don't ask again next time)  */
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
               hasNoAskPermission = true
            }
         }
      }
      if (needReqPermissionList.size == 0) {
         return true
      }

      if (hasNoAskPermission) {
         if (permissions.size == 1) {
            /** if only one permission, indication need execute an action  */
            AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("当前操作所需权限已被禁止。\n\n请点击\"设置\"-\"权限\"-打开所需权限。")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("设置") { dialog, which ->
                       val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                       intent.data = Uri.parse("package:$packageName")
                       startActivity(intent)
                    }
                    .show()
            return false
         }
      }

      ActivityCompat.requestPermissions(this,
              needReqPermissionList.toTypedArray(),
              reqCode)
      return false
   }

   override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults)
      var isAllGranted = true
      for (grantResult in grantResults) {
         if (grantResult != PackageManager.PERMISSION_GRANTED) {
            isAllGranted = false
         }
      }
      reqPermissionResult(isAllGranted, permissions, requestCode)
   }

   /** dynamic apply permissions related methods -- end **/
}