package com.example.week1;


import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {
    public final int PICTURE_REQUEST_CODE = 100;
    private ArrayList<Uri> image_ids = new ArrayList<>();
    View v;

    private ArrayList<Uri> prepareData(){
        Button btnImage = v.findViewById(R.id.btnImage);
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICTURE_REQUEST_CODE);
            }
        });

        return image_ids;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                ClipData clipData = data.getClipData();
                if (clipData != null) {
                    for (int i = 0; i < 20; i++) {
                        if (i < clipData.getItemCount()) {
                            image_ids.add(clipData.getItemAt(i).getUri());
                        }
                    }
                }

            }
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_gallery, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.image_gallery);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (prepareData() != null){
            ArrayList<Uri> createLists = prepareData();
            MyAdapter adapter = new MyAdapter(createLists);
            recyclerView.setAdapter(adapter);
        }
        // Inflate the layout for this fragment
        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        RecyclerView recyclerView = v.findViewById(R.id.image_gallery);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(),2);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (prepareData() != null){
            ArrayList<Uri> createLists = prepareData();
            MyAdapter adapter = new MyAdapter(createLists);
            recyclerView.setAdapter(adapter);
        }
    }
}
