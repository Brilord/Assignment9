package com.example.assignment9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firestore.v1.FirestoreGrpc;

public class ImageActivity extends AppCompatActivity {

    ImageView imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imageView2 = findViewById(R.id.imageView22);

        StorageReference storageRef;
        FirebaseStorage storage;
        StorageReference mountainRef;

        storage = FirebaseStorage.getInstance();
        mountainRef = storage.getReference().child("Images/").child("20221114150402");
        Glide.with(this).load(mountainRef).into(imageView2);
    }
}