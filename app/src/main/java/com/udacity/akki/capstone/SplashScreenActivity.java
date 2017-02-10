package com.udacity.akki.capstone;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.udacity.akki.capstone.utility.Util;

public class SplashScreenActivity extends AppCompatActivity {

    private Context mContext;
    private static String LOG_TAG = SplashScreenActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mContext = SplashScreenActivity.this;
        String userName = Util.getUserName(mContext);
        String password = Util.getPassword(mContext);
        Log.d(LOG_TAG, "User name :"+userName+" and Password :"+password);
        if(Util.isStringNullOrEmpty(userName) || Util.isStringNullOrEmpty(password)){
            Intent intent=new Intent(mContext,LoginActivity.class);
            startActivity(intent);
        }
    }
}
