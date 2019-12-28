package com.example.week1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String uri = intent.getStringExtra("uri");
        Uri myuri = Uri.parse(uri);
        Log.d("t", "-------------------------"+myuri);

        ImageView imageView = (ImageView) findViewById(R.id.clicked_image);
        imageView.setImageURI(myuri);
    }
}
