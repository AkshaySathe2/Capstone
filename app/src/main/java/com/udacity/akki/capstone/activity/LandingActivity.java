package com.udacity.akki.capstone.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.udacity.akki.capstone.HomePageParameters;
import com.udacity.akki.capstone.R;
import com.udacity.akki.capstone.network.ApiClient;
import com.udacity.akki.capstone.network.ApiInterface;
import com.udacity.akki.capstone.utility.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingActivity extends AppCompatActivity {

    private Context mContext;
    private static  final String LOG_TAG = LandingActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        mContext=LandingActivity.this;
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<HomePageParameters> call = apiService.getHomeData(Util.getUserName(mContext));
        call.enqueue(new Callback<HomePageParameters>() {
            @Override
            public void onResponse(Call<HomePageParameters>call, Response<HomePageParameters> response) {
                HomePageParameters home = response.body();
            }

            @Override
            public void onFailure(Call<HomePageParameters>call, Throwable t) {
                // Log error here since request failed
                Log.e(LOG_TAG, t.toString());
            }
        });



    }
}
