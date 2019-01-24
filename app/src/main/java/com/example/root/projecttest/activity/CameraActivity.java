package com.example.root.projecttest.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.root.projecttest.R;

import java.io.File;

/**
 * author:Jiwenjie
 * email:278630464@qq.com
 * time:2019/01/24
 * desc:
 * version:1.0
 */
public class CameraActivity extends AppCompatActivity {

   private static final int CROP_REQQUEST = 2;
   private static final int IMAGE_CAMERA = 3;
   private static final int IMAGE_PICK_REQUEST = 4;

   public static final String ROOT_DIR = Environment.getExternalStorageDirectory() + "/" + "project";
   public static final String PIC_TEMP = ROOT_DIR + "/temp.jpg";


   private String cameraPath;
   private String outputPath;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_camera);

      findViewById(R.id.cameraBtn).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            getImageFromCamera();
         }
      });

      findViewById(R.id.photoSelect).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            getImageFromAlbum();
         }
      });
   }

   private void getImageFromCamera() {
      // 首先判断是否有 sd 卡
      String state = Environment.getExternalStorageState();
      if (state.equals(Environment.MEDIA_MOUNTED)) {
         Intent getImageByCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

         File file = new File(ROOT_DIR);
         if (!file.exists()) {
            file.mkdirs();
         }
         cameraPath = PIC_TEMP;

         Uri uri;
         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(getBaseContext(), "com.jackson.fileprovider", new File(cameraPath));
         } else {
            uri = Uri.fromFile(new File(cameraPath));
         }

         getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, uri);
         getImageByCamera.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
         startActivityForResult(getImageByCamera, IMAGE_CAMERA);
      }
   }

   private void getImageFromAlbum() {
      Intent intent = new Intent(Intent.ACTION_PICK, null);
      intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
      startActivityForResult(intent, IMAGE_PICK_REQUEST);
   }

   private Uri startImageCrop(Uri uri, int outputX, int outputY, int requestCode) {
      Intent intent;
      intent = new Intent("com.android.camera.action.CROP");
      intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
      intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
      intent.setDataAndType(uri, "image/*");
      intent.putExtra("crop", "true");
      intent.putExtra("aspectX", 1);
      intent.putExtra("aspectY", 2);
      intent.putExtra("outputX", outputX);
      intent.putExtra("outputY", outputY);
      intent.putExtra("scale", true);
      outputPath = "cdnkdc.cdkm.dcm";

      Uri outputUri = Uri.fromFile(new File(outputPath));

      intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
      intent.putExtra("return-data", false);
      intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
      intent.putExtra("noFaceDetection", false);
      startActivityForResult(intent, requestCode);
      return outputUri;
   }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      if (resultCode == Activity.RESULT_OK) {
         // 相册选择
         if (requestCode == IMAGE_PICK_REQUEST) {
            Uri uri = data.getData();
            startImageCrop(uri, 200, 200, CROP_REQQUEST);
         } else if (requestCode == IMAGE_CAMERA) {
            Uri uri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
               uri = FileProvider.getUriForFile(getBaseContext(), "com.jackson.fileprovider", new File(cameraPath));
            } else {
               uri = Uri.fromFile(new File(cameraPath));
            }
            startImageCrop(uri, 200, 200, CROP_REQQUEST);
         } else if (requestCode == CROP_REQQUEST) {
            // TODO: 2019/1/24 , 上传服务器
         }
      }
   }
}
