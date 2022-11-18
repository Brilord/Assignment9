package com.example.assignment9;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class PhotosAdopter extends RecyclerView.Adapter<PhotosAdopter.ViewHolder>{
    // Declare variables to store data from the constructor
    Context context;
    StorageReference storageRef;
    FirebaseStorage storage;
    StorageReference mountainsRef;
    //int[] images;
    List<Photo> images;
    // Create a static inner class and provide references to all the Views for each data item.
    // This is particularly useful for caching the Views within the item layout for fast access.
    public static class ViewHolder extends RecyclerView.ViewHolder{
        // Declare member variables for all the Views in a row
        ImageView rowImage;
        // Create a constructor that accepts the entire row and search the View hierarchy to find each subview
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Store the item subviews in member variables
            rowImage = itemView.findViewById(R.id.imageView);
        }
    }

    // Provide a suitable constructor
    public PhotosAdopter(Context context, List<Photo> images){
        // Initialize the class scope variables with values received from constructor
        this.context = context;
        this.images = images;
    }
    // Create new views to be invoked by the layout manager
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a LayoutInflater object
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View view = inflater.inflate(R.layout.image, parent, false);
        // To attach OnClickListener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // Replace the contents of a view to be invoked by the layout manager
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the contents of the View with that element
        //holder.rowImage.setImageResource(images[position]);
        storage = FirebaseStorage.getInstance();




    }


//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
////            title=itemView.findViewById(R.id.title);
////            content=itemView.findViewById(R.id.content);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //Bundle bundle = new Bundle();
//
////                    Bundle bundle = new Bundle();
////                    Intent i = new Intent(context, ImageActivity.class);
//////                    bundle.putString("id",listdata.id);
//////                    bundle.putString("title",listdata.title);
//////                    bundle.putString("content",listdata.content);
//////                    i.putExtras(bundle);
////                    context.startActivity(i);
//                }
//            });


    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return images.size();
    }
}