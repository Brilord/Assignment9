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

import java.util.List;

public class PhotosAdopter extends RecyclerView.Adapter<PhotosAdopter.ViewHolder>{
    // Declare variables to store data from the constructor
    Context context;

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
                //TextView rowName = v.findViewById(R.id.textView1);
                //Toast.makeText(context, "Clicked Item: " + rowName.getText().toString(), Toast.LENGTH_SHORT).show();
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

    }

    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return images.size();
    }
}