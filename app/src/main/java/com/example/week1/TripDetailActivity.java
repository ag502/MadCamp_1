package com.example.week1;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TripDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    public GoogleMap mMap;
    String mapx;
    String mapy;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trip_activity_detail);


        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        String addr1 = intent.getStringExtra("addr1");
        String firstimage = intent.getStringExtra("firstimage");
        String tel = intent.getStringExtra("tel");
        mapx = intent.getStringExtra("mapx");
        mapy = intent.getStringExtra("mapy");
        String overview = intent.getStringExtra("overview");
        String homepage = intent.getStringExtra("homepage");
        Log.d("TT", "-----------------------------------" + homepage);

        ImageView imageView = findViewById(R.id.firstimage);
        Glide.with(getApplicationContext()).load(firstimage).centerCrop().into(imageView);
        TextView textView = findViewById(R.id.title);
        textView.setText(title);
        textView.setTypeface(null, Typeface.BOLD);
        TextView textView1 = findViewById(R.id.addr1);
        textView1.setText(addr1);
        TextView textView2 = findViewById(R.id.tel);
        textView2.setText(tel);
        TextView textView3 = findViewById(R.id.overview);
        textView3.setText(Html.fromHtml(overview));
        TextView textView4 = findViewById(R.id.homepage);
        if(homepage != null) {
            textView4.setText(Html.fromHtml(homepage));
        }

        SupportMapFragment mapFragment =  (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng POINT = new LatLng(Double.parseDouble(mapy), Double.parseDouble(mapx));

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(POINT);
        markerOptions.title(title);
//        markerOptions.snippet(addr1);
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(POINT));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
    }

}

