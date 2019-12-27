package com.example.week1;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.util.Log;

import java.util.ArrayList;

public class ReadContact {
    private Context context;

    public ReadContact(Context context) {
        this.context = context;
    }

    public ArrayList<UserInfo> getContactList() {
        try {
            Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

            String[] projection = new String[] {
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                    ContactsContract.CommonDataKinds.Phone.NUMBER,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
            };


            Cursor contactCursor = context.getContentResolver().query(uri, projection, null, null, null);

            ArrayList<UserInfo> users = new ArrayList<>();

            if (contactCursor.moveToFirst()) {
                do {
                    String name = contactCursor.getString(2);
                    String phoneNumber = contactCursor.getString(1).replaceAll("-", "");

                    if (phoneNumber.substring(0, 3).equals("010")) {
                        phoneNumber = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 7) + "-" + phoneNumber.substring(7, 11);
                    } else if (phoneNumber.substring(0, 3).equals("031")) {
                        phoneNumber = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6, 10);
                    }

                    users.add(new UserInfo(phoneNumber, name));
                } while (contactCursor.moveToNext());
            }
            return users;

        } catch (Exception e) {
            android.os.Process.killProcess(android.os.Process.myPid());
            return null;
        }
    }
}
