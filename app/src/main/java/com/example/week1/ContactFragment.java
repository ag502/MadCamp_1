package com.example.week1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactFragment extends Fragment {
    private RecyclerView contactRecyclerView;
    private RecyclerView.LayoutManager contactLayoutManager;
    private ContactAdapter contactAdapter;
    private View v;

    private ArrayList<UserInfo> users;
    private ReadContact Reader;
    boolean isRefresh = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("View", "-------------------Fragment OnCreateView-------------------");

        v = inflater.inflate(R.layout.fragment_contact, container, false);

        Button btn = v.findViewById(R.id.add_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_INSERT, Uri.parse("content://contacts/people"));
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Reader = null;
        contactRecyclerView = v.findViewById(R.id.recycler_view);
        contactLayoutManager = new LinearLayoutManager(getContext());
        contactRecyclerView.setLayoutManager(contactLayoutManager);

        Reader = new ReadContact(getContext());
        users = Reader.getContactList();

        Log.d("test", " " + users.size());

        contactAdapter = new ContactAdapter(getContext(), users);
        contactRecyclerView.setAdapter(contactAdapter);
    }

}
