package com.example.assignment9;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhotosAdopter extends RecyclerView.Adapter<PhotosAdopter.MyHolder>{

    List<Photo> noteList;
    private final Context context;
    MyHolder myHolder;
    public PhotosAdopter(List<Photo> noteslist, Context context)
    {
        this.context=context;
        noteList=noteslist;
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image, viewGroup, false);
        return new MyHolder(view);
    }

    public void onBindViewHolder(@NonNull MyHolder myHolder, int position) {
        try {
            Photo data = noteList.get(position);
            //myHolder.title.setText(data.getTitle());
            //myHolder.content.setText(data.getContent());
        } catch (NullPointerException np) {
            Log.d("th", "hhh");
        }
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder  {
        TextView title,content;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            // title=itemView.findViewById(R.id.title);
            // content=itemView.findViewById(R.id.content);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Photo listdata = noteList.get(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    Intent i = new Intent(context, ImageActivity.class);
                    // bundle.putString("id", listdata.id);
                    // bundle.putString("title", listdata.title);
                    // bundle.putString("content", listdata.content);
                    i.putExtras(bundle);
                    context.startActivity(i);
                }
            });
        }
    }
}