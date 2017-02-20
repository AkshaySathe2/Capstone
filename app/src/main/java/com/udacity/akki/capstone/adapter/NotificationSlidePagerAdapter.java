package com.udacity.akki.capstone.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.akki.capstone.model.Notification;

/**
 * Created by 836158 on 20-02-2017.
 */

public class NotificationSlidePagerAdapter extends PagerAdapter {

    private static final int NUM_PAGES = 5;
    private static Notification[] notificationList;
    private final Context context;

    public NotificationSlidePagerAdapter(Context ac, Notification[] notificationsList) {
        notificationList=notificationsList;
        context=ac;
    }


    @Override
    public int getCount() {
        return notificationList.length>5?5:notificationList.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        TextView view = new TextView(context);
        view.setText(notificationList[position].getMessage());
        view.setTextSize(22);
        view.setTextColor(Color.parseColor("#000000"));
        view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        view.setGravity(Gravity.CENTER);
        collection.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

}
