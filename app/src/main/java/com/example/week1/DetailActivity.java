package com.example.week1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;

import static java.security.AccessController.getContext;

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
