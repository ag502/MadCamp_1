package com.example.week1.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.week1.Data.UrlInfo;
import com.example.week1.Data.LandMark;
import com.example.week1.R;
import com.example.week1.Adapter.TripAdapter;
import com.example.week1.XML.GetXML;

import java.util.ArrayList;

public class TripFragment extends Fragment {

    private RecyclerView tripRecyclerView;
    private RecyclerView.LayoutManager tripLayoutManager;
    private TripAdapter tripAdapter;
    private ArrayList<LandMark> landMarkArrayList = null;
    private View v;
    private GetXML getXML;
    private TextView pageInfo;
    private Button prev;
    private Button next;
    private int totalPage;
    private String pageView;
//    int currentPage = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Error", "---------------------OnCreateView--------------------");
        v = inflater.inflate(R.layout.fragment_trip, container, false);

        pageInfo = v.findViewById(R.id.page);

        getXML = new GetXML(getContext(), getActivity());


        try {
            landMarkArrayList = getXML.execute(Integer.toString(UrlInfo.getCurrentPage()), UrlInfo.getKeyword()).get();
            totalPage = (int ) Math.ceil((Integer.parseInt(getXML.getPageInfo()[1]) / Double.parseDouble(getXML.getPageInfo()[0])));
//            pageView = UrlInfo.getCurrentPage() + " / " + totalPage;
            pageInfo.setText(String.valueOf(UrlInfo.getCurrentPage()) + " / " + String.valueOf(totalPage));
        } catch (Exception e) {
            Log.d("Error", "------------------" + e + "-----------------");
        }

        tripRecyclerView = v.findViewById(R.id.trip_recycler_view);
        tripLayoutManager = new LinearLayoutManager(getContext());
        tripRecyclerView.setLayoutManager(tripLayoutManager);

        tripAdapter = new TripAdapter(landMarkArrayList, getContext());
        tripRecyclerView.setAdapter(tripAdapter);

        prev = v.findViewById(R.id.prev);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UrlInfo.getCurrentPage() > 1) {
                    UrlInfo.setCurrentPage(UrlInfo.getCurrentPage() - 1);
                    Log.d("print", "---------------" + UrlInfo.getKeyword());
                    getXML = new GetXML(getContext(), getActivity());
                    try {
                        landMarkArrayList = getXML.execute(Integer.toString(UrlInfo.getCurrentPage()), UrlInfo.getKeyword()).get();
                        totalPage = (int ) Math.ceil((Integer.parseInt(getXML.getPageInfo()[1]) / Double.parseDouble(getXML.getPageInfo()[0])));
<<<<<<< HEAD:app/src/main/java/com/example/week1/TripFragment.java
                        pageInfo.setText(Keyword.getCurrentPage() + " / " + totalPage);
=======
                        pageInfo.setText(String.valueOf(UrlInfo.getCurrentPage()) + " / " + String.valueOf(totalPage));
>>>>>>> bb265dde7c216756793b19b445ee4f21da597bd8:app/src/main/java/com/example/week1/Fragments/TripFragment.java
                    } catch (Exception e) {
                        Log.d("Error", "------------------" + e + "-----------------");
                    }
                    tripAdapter.setAdapter(landMarkArrayList);
                    tripAdapter.notifyDataSetChanged();

                    if (tripRecyclerView.computeVerticalScrollOffset() != 0) {
                        tripRecyclerView.smoothScrollToPosition(0);
                    }
                } else {
                    Toast.makeText(getContext(), "첫 번째 페이지 입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        next = v.findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getXML = new GetXML(getContext(), getActivity());

<<<<<<< HEAD:app/src/main/java/com/example/week1/TripFragment.java
                if (Keyword.getCurrentPage() < totalPage) {
                    Keyword.setCurrentPage(Keyword.getCurrentPage() + 1);
=======

                if (UrlInfo.getCurrentPage() < totalPage) {
                    UrlInfo.setCurrentPage(UrlInfo.getCurrentPage() + 1);
>>>>>>> bb265dde7c216756793b19b445ee4f21da597bd8:app/src/main/java/com/example/week1/Fragments/TripFragment.java

                    try {
                        landMarkArrayList = getXML.execute(Integer.toString(UrlInfo.getCurrentPage()), UrlInfo.getKeyword()).get();
                        totalPage = (int ) Math.ceil((Integer.parseInt(getXML.getPageInfo()[1]) / Double.parseDouble(getXML.getPageInfo()[0])));
<<<<<<< HEAD:app/src/main/java/com/example/week1/TripFragment.java
                        pageInfo.setText(Keyword.getCurrentPage() + " / " + totalPage);
=======
                        pageInfo.setText(String.valueOf(UrlInfo.getCurrentPage()) + " / " + String.valueOf(totalPage));
>>>>>>> bb265dde7c216756793b19b445ee4f21da597bd8:app/src/main/java/com/example/week1/Fragments/TripFragment.java
                    } catch (Exception e) {
                        Log.d("Error", "------------------" + e + "-----------------");
                    }

                    tripAdapter.setAdapter(landMarkArrayList);
                    tripAdapter.notifyDataSetChanged();

                    if (tripRecyclerView.computeVerticalScrollOffset() != 0) {
                        tripRecyclerView.smoothScrollToPosition(0);
                    }
                } else {
                    Toast.makeText(getContext(), "마지막 페이지 입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
    }

    public RecyclerView getTripRecyclerView() {
        return tripRecyclerView;
    }
}