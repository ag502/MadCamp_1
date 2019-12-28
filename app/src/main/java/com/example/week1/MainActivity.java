package com.example.week1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ContactFragment contact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("연락처"));
        tabLayout.addTab(tabLayout.newTab().setText("갤러리"));
        tabLayout.addTab(tabLayout.newTab().setText("기타"));

        contact = new ContactFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, contact).commit();



        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭" + position);
                Fragment selected = null;

                if (position == 0) {
                    selected = contact;
                } else if (position == 1) {
                    selected = contact;
                } else if (position == 2) {
                    selected = contact;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

//    private boolean hasPermissions() {
//        int res;
//
//        String[] permissions = new String[]{Manifest.permission.READ_CONTACTS};
//
//        for (String perm : permissions) {
//            res = checkCallingOrSelfPermission(perm);
//
//            if (!(res == PackageManager.PERMISSION_GRANTED)) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private void requestPerms() {
//        String[] permissions = new String[]{Manifest.permission.READ_CONTACTS};
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (!hasPermissions()) {
//                requestPermissions(permissions, 0);
//            }
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        switch (requestCode) {
//            case 0:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(getApplicationContext(), "권한이 승인 되었습니다.", Toast.LENGTH_LONG).show();
//                    canDrawFragment = true;
//                } else {
//                    Toast.makeText(getApplicationContext(), "권한이 거부 되었습니다.", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//                return;
//        }
//    }
}
