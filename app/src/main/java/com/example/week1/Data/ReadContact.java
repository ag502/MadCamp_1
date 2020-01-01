package com.example.week1.Data;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;


import java.util.ArrayList;

public class ReadContact {
    private Context context;

    public ReadContact(Context context) {
        this.context = context;
    }

    public ArrayList<UserInfo> getContactList() {
        ArrayList<UserInfo> users = new ArrayList<>();
        Cursor contactCursor = null;
        try {
            Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

            String[] projection = new String[] {
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                    ContactsContract.CommonDataKinds.Phone.NUMBER,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.PHOTO_THUMBNAIL_URI
            };


            contactCursor = context.getContentResolver().query(uri, projection, null, null, null);



            if (contactCursor.moveToFirst()) {
                do {
                    String thumbnail = contactCursor.getString(3);
                    String id = contactCursor.getString(0);
                    String name = contactCursor.getString(2);
                    String phoneNumber = contactCursor.getString(1).replaceAll("-", "");

                    if (phoneNumber.substring(0, 3).equals("010")) {
                        phoneNumber = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 7) + "-" + phoneNumber.substring(7, 11);
                    } else  {
                        phoneNumber = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6, 10);
                    }
//                    Log.d("READ", "----------------배열 삽입----------------");
                    Log.d("Id", "dd" + thumbnail);
                    users.add(new UserInfo(id, phoneNumber, name, thumbnail));
                } while (contactCursor.moveToNext());
            }
            return users;

        } catch (Exception e) {
            Log.d("ERROR", "--------------------불러오기 실패---------------------");
            contactCursor.close();
            return users;
        } finally {
            contactCursor.close();
        }
    }
}
