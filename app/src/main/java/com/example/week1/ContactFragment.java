package com.example.week1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactFragment extends Fragment {
    RecyclerView contactRecyclerView;
    RecyclerView.LayoutManager contactLayoutManager;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        requestPerms();

        v = inflater.inflate(R.layout.fragment_contact, container, false);
        contactRecyclerView = v.findViewById(R.id.recycler_view);
        contactLayoutManager = new LinearLayoutManager(getContext());
        contactRecyclerView.setLayoutManager(contactLayoutManager);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (hasPermissions()) {
            ReadContact Reader = new ReadContact(getContext());
            ArrayList<UserInfo> users = Reader.getContactList();

            ContactAdapter contactAdapter = new ContactAdapter(users);
            contactRecyclerView.setAdapter(contactAdapter);
        }
    }


    private boolean hasPermissions() {
        int res;

        String[] permissions = new String[]{Manifest.permission.READ_CONTACTS};

        for (String perm : permissions) {
            res = ActivityCompat.checkSelfPermission(getContext(), perm);

            if (!(res == PackageManager.PERMISSION_GRANTED)) {
                return false;
            }
        }
        return true;
    }

    private void requestPerms() {
        String[] permissions = new String[]{Manifest.permission.READ_CONTACTS};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!hasPermissions()) {
                requestPermissions(permissions, 0);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 0:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), "권한이 승인 되었습니다.", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getContext(), "권한이 거부 되었습니다.", Toast.LENGTH_SHORT).show();
                    System.exit(0);
                }
                return;
        }
    }
}
