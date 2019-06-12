package com.example.imageeditor;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.imageeditor.Utils.BitmapUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import com.yalantis.ucrop.UCrop;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;
import java.util.UUID;

import ja.burhanrashid52.photoeditor.OnSaveBitmap;
import ja.burhanrashid52.photoeditor.PhotoEditor;
import ja.burhanrashid52.photoeditor.PhotoEditorView;


public class MainActivity extends AppCompatActivity  {

    public int tron =0 ;
    public static final String pictureName = "flash.jpg";
    public static final int PERMISSION_PICK_IMAGE = 1000;
    private static final int CAMERA_REQUEST = 1001 ;

    PhotoEditorView photoEditorView;
    PhotoEditor photoEditor;

    private Button  btn_update;
    private EditText edt_email;
    LinearLayout layout_button;
    HorizontalScrollView horizon_palce;
    CoordinatorLayout coordinatorLayout;

    public static Bitmap origianlBitmap , filteredBitmap , finalBitMap;

    CardView   btn_crop_vuong ,btn_crop_tron ,btn_save;

    public static Uri image_selected_uri;

    // tai thu vien image filters

    //firebase
    FirebaseStorage storage;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       android.support.v7.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Edit Image");

        btn_update = findViewById(R.id.btn_upload);
        edt_email =findViewById(R.id.edt_email);
        layout_button = findViewById(R.id.layout_button);
        horizon_palce = findViewById(R.id.horizon_palce);

        photoEditorView = findViewById(R.id.image_preveiw);
        photoEditor = new PhotoEditor.Builder(this,photoEditorView)
                .setPinchTextScalable(true)
                .build();

        coordinatorLayout = findViewById(R.id.coordinator);

        btn_crop_vuong = findViewById(R.id.btn_crop_vuong);
        btn_crop_tron = findViewById(R.id.btn_crop_tron);
        btn_save = findViewById(R.id.btn_save);

        //firebase

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        //load anh dua ra ung dung
        LoadImage();
        addevent();

    }

    private void addevent() {

        // lua chon cat theo hinh chu nhat
       btn_crop_vuong.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               tron = 0;
               startCrop(image_selected_uri);

           }
       });
        // cat theo hinh tron
       btn_crop_tron.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               tron = 1;
               startCrop(image_selected_uri);
           }
       });

       btn_save.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               layout_button.setVisibility(View.VISIBLE);
               horizon_palce.setVisibility(View.INVISIBLE);
           }
       });
       //khi bam vao nut save
       btn_update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String s = edt_email.getText().toString();
               if( !s.equals("")){
                   updateEmail();
                   saveImageToGallery();
                   layout_button.setVisibility(View.INVISIBLE);
                   horizon_palce.setVisibility(View.VISIBLE);
               }else{
                   Toast.makeText(MainActivity.this,"Ban chua nhap email",Toast.LENGTH_SHORT).show();
               }
           }
       });


    }
    // update email to firebase
    private void updateEmail() {
        if(edt_email.getText() != null){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            //update hinh anh len firebase

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            origianlBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();

            StorageReference ref = storageReference.child("images/"+edt_email.getText().toString());
            ref.putBytes(data)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this,"Uploaded",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this,"Failed" +e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Upload "+(int)progress);
                        }
                    });
        }
    }

    private void startCrop(Uri uri) {

        if (tron == 1){
            String destinationFileName = new StringBuilder(UUID.randomUUID().toString()).append(".jpg").toString();

            UCrop uCrop = UCrop.of(uri,Uri.fromFile(new File(getCacheDir(),destinationFileName)));

            uCrop.withOptions(getCropOption());

            uCrop.start(MainActivity.this);
        }
        else{
            String destinationFileName = new StringBuilder(UUID.randomUUID().toString()).append(".jpg").toString();

            UCrop uCrop = UCrop.of(uri,Uri.fromFile(new File(getCacheDir(),destinationFileName)));

            uCrop.start(MainActivity.this);
        }

    }
    // them lua chon khi cat hinh
    private UCrop.Options getCropOption() {
        UCrop.Options options = new UCrop.Options();
        options.setCircleDimmedLayer(true);
        return options;
    }

    // load hinh anh ban dau
    private void LoadImage() {
        origianlBitmap = BitmapUtils.getBitmapFromAssets(this,pictureName,300,300);
        filteredBitmap = origianlBitmap.copy(Bitmap.Config.ARGB_8888,true);
        finalBitMap = origianlBitmap.copy(Bitmap.Config.ARGB_8888,true);
        photoEditorView.getSource().setImageBitmap(origianlBitmap);
    }
// them vao trang chu cac tab

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_camera){
            openCamera();
            return true;
        }
        else if(id == R.id.action_open){
            openImageFormGallery();
            return true;
        }
//        else if(id == R.id.action_save){
//
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }
    //mo may anh de chup anh
    private void openCamera() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()){
                            ContentValues values = new ContentValues();
                            values.put(MediaStore.Images.Media.TITLE,"New Picture");
                            values.put(MediaStore.Images.Media.DESCRIPTION,"From Camera");
                            image_selected_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    values);
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,image_selected_uri);
                            startActivityForResult(cameraIntent,CAMERA_REQUEST);
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Cannot Crop image",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                    }
                })
                .check();
    }

    public void saveImageToGallery() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if(report.areAllPermissionsGranted()){
                           photoEditor.saveAsBitmap(new OnSaveBitmap() {
                               @Override
                               public void onBitmapReady(Bitmap saveBitmap) {

                                   photoEditorView.getSource().setImageBitmap(saveBitmap);

                                   final String path = BitmapUtils.insertImage(getContentResolver()
                                           ,saveBitmap,System.currentTimeMillis()+"_profile.jpg"
                                           ,null);
                                   if(!TextUtils.isEmpty(path)){
                                       Snackbar snackbar = Snackbar.make(coordinatorLayout,
                                               "Đã lưu vào trong Ambul !",
                                               Snackbar.LENGTH_LONG)
                                               .setAction("OPEN", new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
                                                       openImage(path);
                                                   }
                                               });
                                       snackbar.show();
                                   }
                                   else {
                                       Snackbar snackbar = Snackbar.make(coordinatorLayout,
                                               "Unable save to gallery !",
                                               Snackbar.LENGTH_LONG);
                                       snackbar.show();
                                   }
                               }

                               @Override
                               public void onFailure(Exception e) {

                               }
                           });
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Premission",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
        .check();
    }

    private void openImage(String path) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(path),"image/*");
        startActivity(intent);
    }

    private void openImageFormGallery() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if(report.areAllPermissionsGranted()){
                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(intent,PERMISSION_PICK_IMAGE);
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Lỗi Crop!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
        .check();
    }
    //ham hien thi va lua chon hinh anh
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       if(resultCode == RESULT_OK && requestCode == PERMISSION_PICK_IMAGE){
           Bitmap bitmap = BitmapUtils.getBitmapFromGallery(this,data.getData(),800,800);
           image_selected_uri = data.getData();
//           bitmap = getCroppedBitmap(bitmap);
           // xoa du lieu
           origianlBitmap.recycle();
           finalBitMap.recycle();
           filteredBitmap.recycle();

           origianlBitmap = bitmap.copy(Bitmap.Config.ARGB_8888,true);
           finalBitMap = origianlBitmap.copy(Bitmap.Config.ARGB_8888,true);
           filteredBitmap = origianlBitmap.copy(Bitmap.Config.ARGB_8888,true);

           photoEditorView.getSource().setImageBitmap(origianlBitmap);
           // bien doi anh thanh hinh trong bang thu vien piccaso

           //Picasso.with(MainActivity.this).load(image_selected_uri).transform(new CircleTransform()).into(img_view);
           bitmap.recycle();

           // render hinh anh
       }

        if(resultCode == RESULT_OK && requestCode == CAMERA_REQUEST){
            Bitmap bitmap = BitmapUtils.getBitmapFromGallery(this,image_selected_uri,800,800);

            origianlBitmap.recycle();
            finalBitMap.recycle();
            filteredBitmap.recycle();

            origianlBitmap = bitmap.copy(Bitmap.Config.ARGB_8888,true);
            finalBitMap = origianlBitmap.copy(Bitmap.Config.ARGB_8888,true);
            filteredBitmap = origianlBitmap.copy(Bitmap.Config.ARGB_8888,true);

            photoEditorView.getSource().setImageBitmap(origianlBitmap);
            bitmap.recycle();

            // render hinh anh
        }


       else if (requestCode == UCrop.REQUEST_CROP){
           handleCropResult(data);
       }

    }

    // bien doi anh ve hinh tron

    public Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
        //return _bmp;
        return output;
    }
    // khi da cat xong hinh anh
    private void handleCropResult(Intent data) {
        final Uri resultUri = UCrop.getOutput(data);
        if (resultUri != null){
            photoEditorView.getSource().setImageURI(resultUri);
            //lay anh sau khi cat de chinh sua
            Bitmap bitmap = ((BitmapDrawable)photoEditorView.getSource().getDrawable()).getBitmap();
            //khi cat xong bien doi hinh anh ve hinh tron
            if (tron == 1){
                bitmap = getCroppedBitmap(bitmap);
            }
            origianlBitmap = bitmap.copy(Bitmap.Config.ARGB_8888,true);
            filteredBitmap = origianlBitmap;
            finalBitMap = origianlBitmap;
            // hien thi anh sau khi cat
            photoEditorView.getSource().setImageBitmap(origianlBitmap);

        }else{
            Toast.makeText(this,"Cannot Crop image",Toast.LENGTH_SHORT).show();
        }
    }
}
