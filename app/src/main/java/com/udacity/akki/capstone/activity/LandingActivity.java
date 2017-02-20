package com.udacity.akki.capstone.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.udacity.akki.capstone.HomePageParameters;
import com.udacity.akki.capstone.LoginActivity;
import com.udacity.akki.capstone.R;
import com.udacity.akki.capstone.adapter.NotificationSlidePagerAdapter;
import com.udacity.akki.capstone.model.Notification;
import com.udacity.akki.capstone.model.User;
import com.udacity.akki.capstone.network.ApiClient;
import com.udacity.akki.capstone.network.ApiInterface;
import com.udacity.akki.capstone.utility.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingActivity extends AppCompatActivity {

    private Context mContext;
    private static final String LOG_TAG = LandingActivity.class.getSimpleName();
    //@BindView(R.id.btn_login) EditText edtPassword;
    @BindView(R.id.vp_notifications)ViewPager mPager;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ButterKnife.bind(this);
        mContext = LandingActivity.this;

        // Write a message to the database
        /*FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");*/

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<User> call = apiService.getUserData(Util.getUid(mContext), Util.getToken(mContext));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                //Log.d(LOG_TAG, user.toString());
                updateUI();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Log error here since request failed
                Log.e(LOG_TAG, t.toString());
            }
        });

    }

    @OnClick(R.id.btn_view_all_notifications)
    public void viewAllNotifications(View view) {

    }


    private void logoutUser(Context context) {
        startActivity(new Intent(context, LoginActivity.class));
        finish();
    }

    private void updateUI(){



        PagerAdapter mPagerAdapter = null;
        if (user.getDetail().getNotification().length==0) {
            Notification[] tempNotifications = new Notification[3];
            tempNotifications[0] = new Notification("Notification 1","1");
            tempNotifications[1] = new Notification("Notification 2","2");
            tempNotifications[2] = new Notification("Notification 3","3");
            mPagerAdapter = new NotificationSlidePagerAdapter(mContext, tempNotifications);
        } else {
            mPagerAdapter = new NotificationSlidePagerAdapter(mContext, user.getDetail().getNotification());
        }
        mPager.setAdapter(mPagerAdapter);
    }


}
