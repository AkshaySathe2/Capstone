package com.udacity.akki.capstone.utility;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by Akshay on 12-Mar-17.
 */

public class AnalyticsUtil {

    public static final String FEES_CARD_ID="card_1";
    public static final String FEES_CARD_NAME="Fees_Card";
    public static final String TEST_CARD_ID = "card_2";
    public static final String TEST_CARD_NAME = "Test_Card";
    public static final String ATTENDANCE_CARD_ID = "card_3";
    public static final String ATTENDANCE_CARD_NAME = "Attendance_Card";


    public static Bundle cardClick(String id,String name){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "card");
        return bundle;
    }

}
