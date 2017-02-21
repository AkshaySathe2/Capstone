package com.udacity.akki.capstone.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.udacity.akki.capstone.HomePageParameters;
import com.udacity.akki.capstone.LoginActivity;
import com.udacity.akki.capstone.R;
import com.udacity.akki.capstone.adapter.NotificationSlidePagerAdapter;
import com.udacity.akki.capstone.model.Installment;
import com.udacity.akki.capstone.model.Notification;
import com.udacity.akki.capstone.model.User;
import com.udacity.akki.capstone.network.ApiClient;
import com.udacity.akki.capstone.network.ApiInterface;
import com.udacity.akki.capstone.utility.Util;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LandingActivity extends AppCompatActivity {

    private Context mContext;
    private static final String LOG_TAG = LandingActivity.class.getSimpleName();
    private int currentPage = 0;// For auto swiping notifications
    //@BindView(R.id.btn_login) EditText edtPassword;
    @BindView(R.id.vp_notifications) ViewPager mPager;
    @BindView(R.id.txt_fees_value) TextView feesValue;
    @BindView(R.id.txt_fees_date) TextView feesDate;
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

    private void updateUI() {

        //Populating Fees
        Installment installment = user.getDetail().getFees().getPendingInstallment();
        if (installment != null) {
            populateFees(installment);
        }

        //Populating notifications
        PagerAdapter mPagerAdapter = null;
        mPagerAdapter = new NotificationSlidePagerAdapter(mContext, populateNotifications(user));
        mPager.setAdapter(mPagerAdapter);
        autoSwipeNotifications();


    }

    private void populateFees(Installment installment) {
        feesValue.setText(installment.getAmount());
        feesDate.setText(installment.getDate());
    }

    private Notification[] populateNotifications(User user) {
        if (user.getDetail().getNotification().length == 0) {
            Notification[] tempNotifications = new Notification[3];
            tempNotifications[0] = new Notification("Notification 1", "1");
            tempNotifications[1] = new Notification("Notification 2", "2");
            tempNotifications[2] = new Notification("Notification 3", "3");
            return tempNotifications;
        } else {
            return user.getDetail().getNotification();
        }
    }

    /*Created Handler for swiping pager adapter*/
    private void autoSwipeNotifications() {
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == user.getDetail().getNotification().length - 1) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 500, 3000);
    }


}
