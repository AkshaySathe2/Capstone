package com.udacity.akki.capstone.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.udacity.akki.capstone.R;
import com.udacity.akki.capstone.activity.LandingActivity;
import com.udacity.akki.capstone.model.Notification;
import com.udacity.akki.capstone.model.Test;
import com.udacity.akki.capstone.model.User;
import com.udacity.akki.capstone.network.ApiClient;
import com.udacity.akki.capstone.network.ApiInterface;
import com.udacity.akki.capstone.utility.FirebaseDatabaseUtil;
import com.udacity.akki.capstone.utility.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link AppWidgetConfigureActivity AppWidgetConfigureActivity}
 */
public class AppWidget extends AppWidgetProvider {

    private static final String LOG_TAG = AppWidget.class.getSimpleName();

    static void updateAppWidget(Context context, final AppWidgetManager appWidgetManager,
                                final int appWidgetId) {

        CharSequence widgetText = AppWidgetConfigureActivity.loadTitlePref(context, appWidgetId);
        // Construct the RemoteViews object
        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        //views.setTextViewText(R.id.appwidget_text, widgetText);



        /*Intent intent = new Intent(context, WidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_provider_layout);
        rv.setRemoteAdapter(appWidgetId, R.id.list1, intent);*/

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabaseUtil.getDatabase();
        DatabaseReference myRef = database.getReference("Users").child(Util.getUid(context));
        myRef.keepSynced(true);
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User user = dataSnapshot.getValue(User.class);
                updateUI(user,views,appWidgetManager,appWidgetId);
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

        Call<User> call = apiService.getUserData(Util.getUid(context), Util.getToken(context));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                //Log.d(LOG_TAG, user.toString());
                updateUI(user,views,appWidgetManager,appWidgetId);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Log error here since request failed
                Log.e(LOG_TAG, t.toString());
            }
        });*/

    }

    private static void updateUI(User user, RemoteViews views, AppWidgetManager appWidgetManager, int appWidgetId) {
        Test test=user.getDetail().getLatestTestScores();
        List<Notification> notification=user.getDetail().getNotification();
        views.setTextViewText(R.id.appwidget_text_test_subject, test.getTopic());
        views.setTextViewText(R.id.appwidget_text_test_score, test.getMarksObtained()+" / "+test.getMaxMarks());
        views.setTextViewText(R.id.appwidget_text_notifications, notification.get(0).getMessage());
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            AppWidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

