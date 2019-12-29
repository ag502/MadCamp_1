package com.example.week1;

import android.Manifest;
import android.hardware.camera2.params.MandatoryStreamCombination;

public class Permission {
    private static String [] permissions = new String[] {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_CONTACTS,
    };

    public static String[] getPermissions() {
        return permissions;
    }

    public static String getCertainPerm(int index) {
        return permissions[index];
    }


}
