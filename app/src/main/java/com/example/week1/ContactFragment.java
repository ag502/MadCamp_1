package com.example.week1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactFragment extends Fragment {
    RecyclerView contactRecyclerView;
    RecyclerView.LayoutManager contactLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, container, false);
        contactRecyclerView = v.findViewById(R.id.recycler_view);
        contactLayoutManager = new LinearLayoutManager(getContext());
        contactRecyclerView.setLayoutManager(contactLayoutManager);

        ArrayList<UserInfo> users = new ArrayList<>();
        users.add(new UserInfo("010-123-456"));
        users.add(new UserInfo("010-456-789"));
        users.add(new UserInfo("031-123-456"));
        users.add(new UserInfo("031-000-000"));

        ContactAdapter contactAdapter = new ContactAdapter(users);
        contactRecyclerView.setAdapter(contactAdapter);

        return v;
    }
}
