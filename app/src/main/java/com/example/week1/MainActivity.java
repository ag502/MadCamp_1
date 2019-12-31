package com.example.week1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActionBar;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.week1.Data.UrlInfo;
import com.example.week1.Data.Permission;
import com.example.week1.Fragments.ContactFragment;
import com.example.week1.Fragments.EmptyFragement;
import com.example.week1.Fragments.GalleryFragment;
import com.example.week1.Fragments.TripFragment;
import com.example.week1.XML.GetXML;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {
    private int currentTabPosition;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private TabLayout tabLayout;
    private SearchView searchView;
    private MenuItem searchItem;
    private ContactFragment contact;
    private GalleryFragment gallery;
    private TripFragment tripInfo;
    private EmptyFragement empty;
    private final String[] permissions = Permission.getPermissions();
    private boolean isSearch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("render", "-----------------MainActivity OnCreate--------------------");
        super.onCreate(savedInstanceState);


        requestPerms();


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("연락처"));
        tabLayout.addTab(tabLayout.newTab().setText("갤러리"));
        tabLayout.addTab(tabLayout.newTab().setText("여행"));

        contact = new ContactFragment();
        gallery = new GalleryFragment();
        tripInfo = new TripFragment();
        empty = new EmptyFragement();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, contact).commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentTabPosition = tab.getPosition();
                Log.d("MainActivity", "선택된 탭" + currentTabPosition);
                Fragment selected = null;

                if (currentTabPosition == 0) {
                    searchItem.setVisible(false);
                    if (permissionCheck("CONTACT") != 0 || permissionCheck("CALL") != 0) {
                        requestPerms();
                    }
                    selected = contact;
                } else if (currentTabPosition == 1) {
                    searchItem.setVisible(false);
                    if (permissionCheck("STORAGE") != 0) {
                        requestPerms();
                    }
                    selected = gallery;
                } else if (currentTabPosition == 2) {
                    searchItem.setVisible(true);
                    if (!isSearch) {
                        selected = empty;
                    } else {
                        selected = tripInfo;
                    }
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
        searchItem = menu.findItem(R.id.action_search);
        searchItem.setVisible(false);

        searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint(getString(R.string.search_keyword));



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                tabLayout.
                if (currentTabPosition == 2) {
                    UrlInfo.setKeyword(query);
                    UrlInfo.setCurrentPage(1);
                    UrlInfo.setMode(UrlInfo.SEARCH_KEYWORD);

                    GetXML getXML = new GetXML(getApplicationContext(), MainActivity.this);
                    getXML.execute(Integer.toString(UrlInfo.getCurrentPage()), UrlInfo.getKeyword());
                    getSupportFragmentManager().beginTransaction().detach(tripInfo).attach(tripInfo).commit();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, tripInfo).commit();
                    isSearch = true;
                    Log.d("print", "-------------------" + query);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment).commit();      // Fragment로 사용할 MainActivity내의 layout공간을 선택합니다.
    }

}
