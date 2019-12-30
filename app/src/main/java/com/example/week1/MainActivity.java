package com.example.week1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.app.ActionBar;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    private int currentTabPostion;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private ContactFragment contact;
    private GalleryFragment gallery;
    private TripFragment tripInfo;
    private final String[] permissions = Permission.getPermissions();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("render", "-----------------MainActivity OnCreate--------------------");
        super.onCreate(savedInstanceState);


        requestPerms();

        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("연락처"));
        tabLayout.addTab(tabLayout.newTab().setText("갤러리"));
        tabLayout.addTab(tabLayout.newTab().setText("기타"));

        contact = new ContactFragment();
        gallery = new GalleryFragment();
        tripInfo = new TripFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, contact).commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentTabPostion = tab.getPosition();
                Log.d("MainActivity", "선택된 탭" + currentTabPostion);
                Fragment selected = null;

                if (currentTabPostion == 0) {
                    if (permissionCheck("CONTACT") != 0 || permissionCheck("CALL") != 0) {
                        requestPerms();
                    }
                    selected = contact;
                } else if (currentTabPostion == 1) {
                    if (permissionCheck("STORAGE") != 0) {
                        requestPerms();
                    }
                    selected = gallery;
                } else if (currentTabPostion == 2) {
                    selected = tripInfo;
                    actionBar = getActionBar();
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

    public int permissionCheck(String permKind) {
        int res = -2;
        switch (permKind) {
            case "CONTACT":
                res = ActivityCompat.checkSelfPermission(getApplication(), Permission.getPermissions()[0]);
                return res;
            case "CALL":
                res = ActivityCompat.checkSelfPermission(getApplication(), Permission.getPermissions()[1]);
                return res;
            case "STORAGE":
                res = ActivityCompat.checkSelfPermission(getApplication(), Permission.getPermissions()[2]);
                return res;
            default:
                return res;
        }
    }


    private boolean AllPermissionCheck() {
        int res;

        for (String perm : permissions) {
            res = ActivityCompat.checkSelfPermission(getApplication(), perm);

            if (!(res == PackageManager.PERMISSION_GRANTED)) {
                return false;
            }
        }
        return true;
    }

    public void requestPerms() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!AllPermissionCheck()) {
                requestPermissions(permissions, 0);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        return true;
    }
}
