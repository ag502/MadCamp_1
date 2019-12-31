package com.example.week1;

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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URL;
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
            landMarkArrayList = getXML.execute(Integer.toString(Keyword.getCurrentPage()), Keyword.getKeyword()).get();
            totalPage = (int ) Math.ceil((Integer.parseInt(getXML.getPageInfo()[1]) / Double.parseDouble(getXML.getPageInfo()[0])));
//            pageView = Keyword.getCurrentPage() + " / " + totalPage;
            pageInfo.setText(String.valueOf(Keyword.getCurrentPage()) + " / " + String.valueOf(totalPage));
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
                if (Keyword.getCurrentPage() > 1) {
                    Keyword.setCurrentPage(Keyword.getCurrentPage() - 1);
                    Log.d("print", "---------------" + Keyword.getKeyword());
                    getXML = new GetXML(getContext(), getActivity());
                    try {
                        landMarkArrayList = getXML.execute(Integer.toString(Keyword.getCurrentPage()), Keyword.getKeyword()).get();
                        totalPage = (int ) Math.ceil((Integer.parseInt(getXML.getPageInfo()[1]) / Double.parseDouble(getXML.getPageInfo()[0])));
                        pageInfo.setText(Keyword.getCurrentPage() + " / " + totalPage);
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

                if (Keyword.getCurrentPage() < totalPage) {
                    Keyword.setCurrentPage(Keyword.getCurrentPage() + 1);

                    try {
                        landMarkArrayList = getXML.execute(Integer.toString(Keyword.getCurrentPage()), Keyword.getKeyword()).get();
                        totalPage = (int ) Math.ceil((Integer.parseInt(getXML.getPageInfo()[1]) / Double.parseDouble(getXML.getPageInfo()[0])));
                        pageInfo.setText(Keyword.getCurrentPage() + " / " + totalPage);
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