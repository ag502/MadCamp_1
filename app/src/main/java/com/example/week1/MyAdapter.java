package com.example.week1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Uri> galleryList = null;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img;

        public MyViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.img);
        }

        @Override
        public  void onClick(View view){
//            img = view.findViewById(R.id.img);
//            img.setMaxHeight(500);
//            img.setMaxWidth(400);
            if(view.getId() == R.id.img){
                Log.d("t", "-----------");
            }
        }
    }

    public MyAdapter(ArrayList<Uri> galleryList) {
        this.galleryList = galleryList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.cell_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
//        myViewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        myViewHolder.img.setImageURI(galleryList.get(position));

//        holder.itemView.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                Intent intent = new Intent()
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return galleryList.size();
    }
}

