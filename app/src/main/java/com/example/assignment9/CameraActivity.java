package com.example.assignment9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

        if (allPermissionsGranted()) {
            startCameraX();
        } else {
            ActivityCompat.requestPermissions(this,
                    REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.camera_btn:
                capturePhoto();
                break;
        }
    }
    private void capturePhoto() {
        SimpleDateFormat mDateFormat = new SimpleDateFromat("yyyyMMddHHmmss", Locale.US);
        File file = new File(getExternalFilesDir("asd"), mDateFormat.format(new Date())+ ".jpg");
        ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(file).build();

    }

}