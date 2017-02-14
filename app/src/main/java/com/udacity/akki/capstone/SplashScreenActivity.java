package com.udacity.akki.capstone;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.udacity.akki.capstone.activity.LandingActivity;
import com.udacity.akki.capstone.utility.Util;

public class SplashScreenActivity extends AppCompatActivity {

    private Context mContext;
    private static String LOG_TAG = SplashScreenActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mContext = SplashScreenActivity.this;
        Intent intent = null;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            intent = new Intent(mContext, LandingActivity.class);
        } else {
            intent = new Intent(mContext, LoginActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
