package com.example.week1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TripFragment extends Fragment {

    private RecyclerView tripRecyclerView;
    private RecyclerView.LayoutManager tripLayoutManager;
    private TripAdapter tripAdapter;
    private ArrayList<LandMark> landMarkArrayList = null;
    private View v;
    private GetXML getXML;
    private Button prev;
    private Button next;
    int currentPage = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Error", "---------------------OnCreateView--------------------");
        v = inflater.inflate(R.layout.fragment_trip, container, false);
        getXML = new GetXML();

        try {
            landMarkArrayList = getXML.execute().get();
        } catch (Exception e) {
            Log.d("Error", "------------------" + e + "-----------------");
        }

        tripRecyclerView = v.findViewById(R.id.trip_recycler_view);
        tripLayoutManager = new LinearLayoutManager(getContext());
        tripRecyclerView.setLayoutManager(tripLayoutManager);

        tripAdapter = new TripAdapter(landMarkArrayList, getContext());
        tripAdapter.setHasStableIds(true);
        tripRecyclerView.setAdapter(tripAdapter);

        prev = v.findViewById(R.id.prev);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage > 1) {
                    currentPage--;
                    getXML = new GetXML();
                    getXML.execute(Integer.toString(currentPage));
                    tripAdapter = new TripAdapter(landMarkArrayList, getContext());
                    tripRecyclerView.setAdapter(tripAdapter);
                } else {
                    Toast.makeText(getContext(), "첫 번째 페이지 입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        next = v.findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage++;
                getXML = new GetXML();
                try {
                    landMarkArrayList = getXML.execute(Integer.toString(currentPage)).get();
                    tripAdapter = new TripAdapter(landMarkArrayList, getContext());
                    tripRecyclerView.setAdapter(tripAdapter);
                } catch (Exception e) {
                    Log.d("Error", "------------------" + e + "-----------------");
                }
            }
        });


        return v;
    }
}