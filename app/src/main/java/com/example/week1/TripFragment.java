package com.example.week1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TripFragment extends Fragment {

    private RecyclerView tripRecyclerView;
    private RecyclerView.LayoutManager tripLayoutManager;
    private TripAdapter tripAdapter;
    private ArrayList<LandMark> landMarkArrayList = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trip, container, false);
        GetXML getXML = new GetXML();

        try {
            landMarkArrayList = getXML.execute().get();
        } catch (Exception e) {
            Log.d("Error", "------------------" + e + "-----------------");
        }

        tripRecyclerView = v.findViewById(R.id.trip_recycler_view);
        tripLayoutManager = new LinearLayoutManager(getContext());
        tripRecyclerView.setLayoutManager(tripLayoutManager);

        tripAdapter = new TripAdapter(landMarkArrayList, getContext());
        tripRecyclerView.setAdapter(tripAdapter);

        return v;
    }
}
