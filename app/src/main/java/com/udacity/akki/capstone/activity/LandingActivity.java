package com.udacity.akki.capstone.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.udacity.akki.capstone.LoginActivity;
import com.udacity.akki.capstone.R;
import com.udacity.akki.capstone.adapter.NotificationSlidePagerAdapter;
import com.udacity.akki.capstone.fragment.AttendanceFragment;
import com.udacity.akki.capstone.fragment.FeesFragment;
import com.udacity.akki.capstone.fragment.TestFragment;
import com.udacity.akki.capstone.model.Installment;
import com.udacity.akki.capstone.model.Notification;
import com.udacity.akki.capstone.model.Test;
import com.udacity.akki.capstone.model.User;
import com.udacity.akki.capstone.utility.AnalyticsUtil;
import com.udacity.akki.capstone.utility.FirebaseDatabaseUtil;
import com.udacity.akki.capstone.utility.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LandingActivity extends AppCompatActivity {

    private Context mContext;
    private static final String LOG_TAG = LandingActivity.class.getSimpleName();
    private int currentPage = 0;// For auto swiping notifications
    //@BindView(R.id.btn_login) EditText edtPassword;
    @BindView(R.id.vp_notifications)
    ViewPager mPager;
    @BindView(R.id.txt_fees_value)
    TextView feesValue;
    @BindView(R.id.txt_fees_date)
    TextView feesDate;
    @BindView(R.id.txt_test_date)
    TextView testDate   ;
    @BindView(R.id.txt_test_name)
    TextView testName;
    @BindView(R.id.txt_test_score)
    TextView testScore;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    private User user;
    private ProgressDialog dialog;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mContext = LandingActivity.this;
        dialog = new ProgressDialog(mContext);
        dialog.setMessage("Loading Data. Please Wait...");
        dialog.show();
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabaseUtil.getDatabase();
        DatabaseReference myRef = database.getReference("Users").child(Util.getUid(mContext));
        myRef.keepSynced(true);
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                user = dataSnapshot.getValue(User.class);
                updateUI();
                //Log.d(LOG_TAG, "Value is: " + user);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(LOG_TAG, "Failed to read value.", error.toException());
            }
        });

        /*ApiInterface apiService =
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
        });*/

    }

    /*@OnClick(R.id.btn_view_all_notifications)
    public void viewAllNotifications(View view) {

    }*/

    @OnClick(R.id.card_fees)
    public void viewFees(View view) {
        Bundle bundle = AnalyticsUtil.cardClick(AnalyticsUtil.FEES_CARD_ID, AnalyticsUtil.FEES_CARD_NAME);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        if (user != null && user.getDetail().getFees() != null) {
            FeesFragment fragmentS1 = new FeesFragment();
            fragmentS1.setFees(user.getDetail().getFees());
            getSupportFragmentManager().beginTransaction().add(R.id.layout_fragment, fragmentS1).addToBackStack("Main").commit();
        } else {
            Util.showToast(mContext, "No Fees Data Available");
        }
    }

    @OnClick(R.id.card_test)
    public void viewTest(View view) {
        Bundle bundle = AnalyticsUtil.cardClick(AnalyticsUtil.TEST_CARD_ID, AnalyticsUtil.TEST_CARD_NAME);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        if (user != null && user.getDetail().getFees() != null) {
            TestFragment fragmentS1 = new TestFragment();
            fragmentS1.setTest(user.getDetail().getTest());
            fragmentS1.setLatestTest(user.getDetail().getLatestTestScores());
            getSupportFragmentManager().beginTransaction().add(R.id.layout_fragment, fragmentS1).addToBackStack("Main").commit();
        } else {
            Util.showToast(mContext, "No Fees Data Available");
        }
    }

    @OnClick(R.id.txt_attendance)
    public void viewAttendance(View view) {
        Bundle bundle = AnalyticsUtil.cardClick(AnalyticsUtil.ATTENDANCE_CARD_ID, AnalyticsUtil.ATTENDANCE_CARD_NAME);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        if (user != null && user.getDetail().getFees() != null) {
            AttendanceFragment fragmentS1 = new AttendanceFragment();
            fragmentS1.setAttendance(user.getDetail().getAttendance());
            getSupportFragmentManager().beginTransaction().add(R.id.layout_fragment, fragmentS1).addToBackStack("Main").commit();
        } else {
            Util.showToast(mContext, "No Data Available");
        }
    }

    private void logoutUser(Context context) {

        // User is signed out
        Log.d(LOG_TAG, "onAuthStateChanged:signed_out");
        Util.showToast(mContext, "User signed out.");
        startActivity(new Intent(context, LoginActivity.class));
        FirebaseAuth.getInstance().signOut();
        finish();
    }

    private void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private void updateUI() {

        //Populating Fees
        Installment installment = user.getDetail().getFees().getPendingInstallment();
        if (installment != null) {
            populateFees(installment);
        }

        //Populating notifications
        PagerAdapter mPagerAdapter;
        mPagerAdapter = new NotificationSlidePagerAdapter(mContext, populateNotifications(user));
        mPager.setAdapter(mPagerAdapter);
        autoSwipeNotifications();

        //Populating Test
        Test test = user.getDetail().getLatestTestScores();
        if (test != null) {
            populateTest(test);
        }

        //Track My Bus

        //Called after everything is loaded
        dismissDialog();
    }

    private void populateTest(Test test) {
        testName.setText(test.getTopic());
        testDate.setText(test.getDoa());
        testScore.setText(test.getMarksObtained() + " / " + test.getMaxMarks());
    }

    private void populateFees(Installment installment) {
        feesValue.setText(installment.getAmount());
        feesDate.setText(installment.getDate());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_logout:
                finish();
                logoutUser(mContext);
                break;
            default:
                super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private List<Notification> populateNotifications(User user) {
        if (user.getDetail().getNotification().size() == 0) {
            List<Notification> tempNotifications = new ArrayList<>();
            tempNotifications.add(new Notification("Notification 1", "1"));
            tempNotifications.add(new Notification("Notification 2", "2"));
            tempNotifications.add(new Notification("Notification 3", "3"));
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
                if (currentPage == user.getDetail().getNotification().size() - 1) {
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
