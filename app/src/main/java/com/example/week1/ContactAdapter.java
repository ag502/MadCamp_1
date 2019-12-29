package com.example.week1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView phoneNumber;
        private TextView name;
        private ImageView thumbnail;
        private Button deleteButton;
        private Button callButton;
        private Button editButton;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            phoneNumber = itemView.findViewById(R.id.phone_number);
            name = itemView.findViewById(R.id.name);
            thumbnail = itemView.findViewById(R.id.user_thumbnail);
            deleteButton = itemView.findViewById(R.id.delete_button);
            callButton = itemView.findViewById(R.id.call_button);


            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.getContentResolver().delete(ContactsContract.RawContacts.CONTENT_URI, ContactsContract.RawContacts.CONTACT_ID + " = " + users.get(getAdapterPosition()).getId(), null);
                    users.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), users.size());
                }
            });

            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+users.get(getAdapterPosition()).getPhoneNumber()));
                    context.startActivity(intent);
                }
            });

            thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/" + users.get(getAdapterPosition()).getId()));
                    context.startActivity(intent);
                }
            });
        }
    }

    ArrayList<UserInfo> users;
    Context context;

    public ContactAdapter(Context context, ArrayList<UserInfo> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_rows, parent, false);
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ContactViewHolder viewHolder = (ContactViewHolder) holder;
        viewHolder.phoneNumber.setText(users.get(position).getPhoneNumber());
        viewHolder.name.setText(users.get(position).getName());
        if (users.get(position).getThumbNail() != null) {
            viewHolder.thumbnail.setImageURI(Uri.parse(users.get(position).getThumbNail()));
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
