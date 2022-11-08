package com.example.assignment9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {
    boolean defCamera;
    PreviewView previewView;
    Preview preview;
    Button capture, switchCamera;
    CameraSelector cameraSelector;
    ProcessCameraProvider cameraProvider;
    private final int REQUEST_CODE_PERMISSIONS = 100;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA","android.permission.WRITE_EXTERNAL_STORAGE"};
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private ImageCapture imageCapture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        defCamera = true;

        previewView = findViewById(R.id.previewView);
        capture = findViewById(R.id.camera_btn);
        switchCamera = findViewById(R.id.switch_camera_btn);

        capture.setOnClickListener(this);
        switchCamera.setOnClickListener(this);

        //if (allPermissionsGranted()) {
            startCameraX();
        //} else {
            //ActivityCompat.requestPermissions(this,
                    //REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        //}
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.camera_btn:

                break;
        }
    }
    private void startCameraX() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    cameraProvider = cameraProviderFuture.get();
                    bindPreview(cameraProvider);

                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, ContextCompat.getMainExecutor(this));


    }

    void bindPreview(@NonNull ProcessCameraProvider cameraProvider) {

        cameraProvider.unbindAll();


        //Selector Use case
        if (defCamera) {
            cameraSelector = new CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build();

        } else {
            cameraSelector = new CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                    .build();
        }
    }
    private void capturePhoto() {
        SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
        File file = new File(getExternalFilesDir("asd"), mDateFormat.format(new Date())+ ".jpg");

        ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(file).build();


        imageCapture.takePicture(outputFileOptions,
                ContextCompat.getMainExecutor(this),
                new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {

                        Uri picUri = outputFileResults.getSavedUri();
                        if (picUri == null){
                            Toast.makeText(CameraActivity.this, "Uri is Empty, Image might be saved", Toast.LENGTH_SHORT).show();
                        }else
//                            Glide.with(getBaseContext())
//                                    .load(picUri)
//                                    .into(imageHolder);


                            Toast.makeText(CameraActivity.this, "Image saved at " + picUri.getPath() + " Uri is not Empty, saved", Toast.LENGTH_SHORT).show();

                    }


                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        exception.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(CameraActivity.this, "Image Capture error", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

    }
}
