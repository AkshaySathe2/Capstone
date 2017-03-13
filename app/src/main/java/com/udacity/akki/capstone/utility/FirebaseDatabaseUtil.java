package com.udacity.akki.capstone.utility;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Akshay on 14-Mar-17.
 */

public class FirebaseDatabaseUtil {

    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }

}
