package com.udacity.akki.capstone.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.akki.capstone.R;
import com.udacity.akki.capstone.model.MyDate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Akshay on 12-Mar-17.
 */

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.MyViewHolder> {

    private List<MyDate> myDates = new ArrayList<>();
    private Context mContext;
    private int displayedMonth;
    private int displayedYear;

    public AttendanceAdapter(MyDate[] dates, int displayedYear, int displayedMonth) {
        myDates = new ArrayList<>();
        myDates.addAll(Arrays.asList(dates));
        this.displayedMonth = displayedMonth;
        this.displayedYear = displayedYear;
        matchStartEndDays();
    }

    private void matchStartEndDays() {
        //First Day of month
        Calendar firstDay = Calendar.getInstance();
        firstDay.set(displayedYear, displayedMonth, 1);

        //Last Day of Month
        int daysInMonth = firstDay.getActualMaximum(Calendar.DAY_OF_MONTH);
        Calendar lastDay = Calendar.getInstance();
        lastDay.set(displayedYear, displayedMonth, daysInMonth);

        //Previous Month
        Calendar previousMonth = Calendar.getInstance();
        previousMonth.set(displayedYear, displayedMonth, 1);
        previousMonth.add(Calendar.MONTH, -1);
        int daysInPreviousMonth = previousMonth.getActualMaximum(Calendar.DAY_OF_MONTH);
        int dayOfWeek = firstDay.get(Calendar.DAY_OF_WEEK);
        while (dayOfWeek > 1) {
            MyDate date = new MyDate(daysInPreviousMonth--, "NA", false);
            myDates.add(0, date);
            dayOfWeek--;
        }

        MyDate d = myDates.get(myDates.size() - 1);
        int lastDayAvailable = Integer.valueOf(d.getDate());
        while (lastDayAvailable < daysInMonth) {
            MyDate date = new MyDate(++lastDayAvailable, "NA", false);
            myDates.add(date);
        }

        dayOfWeek = lastDay.get(Calendar.DAY_OF_WEEK);
        int i = 1;
        while (dayOfWeek < 7) {
            MyDate date = new MyDate(i++, "NA", false);
            myDates.add(date);
            dayOfWeek++;
        }


        // Create a calendar object and set year and month

// Get the number of days in that month

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.attendance_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyDate date = myDates.get(position);
        holder.attendanceDate.setText(date.getDate());
        holder.attendaceStatus.setText(date.getValue());
        if (date.getValue().equalsIgnoreCase("A")) {
            holder.attendaceStatus.setBackgroundColor(Color.parseColor("#FF0000"));
        } else if (date.getValue().equalsIgnoreCase("P")) {
            holder.attendaceStatus.setBackgroundColor(Color.parseColor("#008000"));
        } else {
            holder.attendaceStatus.setBackgroundColor(Color.parseColor("#222222"));
        }
    }

    @Override
    public int getItemCount() {
        return myDates.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_date)
        TextView attendanceDate;
        @BindView(R.id.txt_status)
        TextView attendaceStatus;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
