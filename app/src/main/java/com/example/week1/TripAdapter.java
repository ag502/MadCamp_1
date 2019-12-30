package com.example.week1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TripAdapter extends RecyclerView.Adapter {

    private ArrayList<LandMark> landMarkArrayList;
    private Context context;

    class TripViewHolder extends RecyclerView.ViewHolder {
        private ImageView mainImage;
        private TextView title;
        private TextView address;

        public TripViewHolder(@NonNull View itemView) {
            super(itemView);
            mainImage = itemView.findViewById(R.id.main_image);
            title = itemView.findViewById(R.id.title);
            address = itemView.findViewById(R.id.address);
        }
    }

    public TripAdapter(ArrayList<LandMark> landMarkArrayList, Context context) {
        this.landMarkArrayList = landMarkArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_info_rows, parent, false);
        return new TripViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TripViewHolder tripViewHolder = (TripViewHolder) holder;
        Glide.with(context).load(landMarkArrayList.get(position).getTagList().get("firstimage")).into(tripViewHolder.mainImage);
        tripViewHolder.title.setText(landMarkArrayList.get(position).getTagList().get("title"));
        tripViewHolder.address.setText(landMarkArrayList.get(position).getTagList().get("addr1"));
    }

    @Override
    public int getItemCount() {
        return landMarkArrayList.size();
    }
}
