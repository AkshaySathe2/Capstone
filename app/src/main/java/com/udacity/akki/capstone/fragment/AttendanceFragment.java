package com.udacity.akki.capstone.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.akki.capstone.R;
import com.udacity.akki.capstone.adapter.AttendanceAdapter;
import com.udacity.akki.capstone.model.Attendance;
import com.udacity.akki.capstone.model.MyDate;
import com.udacity.akki.capstone.model.MyMonth;
import com.udacity.akki.capstone.model.MyYear;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttendanceFragment extends Fragment {


    private Attendance attendance;
    private AttendanceAdapter mAdapter;
    @BindView(R.id.my_toolbar) Toolbar myToolbar;
    @BindView(R.id.txt_attendance_status) TextView attendanceStatus;
    @BindView(R.id.recycle_attendance_list) RecyclerView attendanceView;
    @BindView(R.id.txt_month_display) TextView currentDisplayMonth;
    private Context mContext;
    private int displayedYear;
    private int displayedMonth;

    public AttendanceFragment() {
        // Required empty public constructor
    }
    private static final String[] listOfMonths=new String[]{"JANUARY","FEBRUARY","MARCH","APRIL",
            "MAY","JUNE","JULY","AUGUST","SEPTEMBER",
            "OCTOBER","NOVEMBER","DECEMBER"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attendance, container, false);
        ButterKnife.bind(this, view);
        myToolbar.setTitle("Attendance");
        ((AppCompatActivity) getActivity()).setSupportActionBar(myToolbar);
        try {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(ContextCompat.getDrawable(getActivity(), R.drawable.ic_arrow_back_black_24dp));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        populateUI();
        return view;
    }

    @OnClick(R.id.calendar_next_button)
    public void viewNextMonth(View view) {
        if(displayedMonth==11){
            displayedMonth=0;
            displayedYear++;
        }else{
            displayedMonth+=1;
        }
        currentDisplayMonth.setText(listOfMonths[displayedMonth]);
        List<MyDate> dates=getAttendanceStatus(displayedYear,displayedMonth);
        populateDates(dates);
    }

    @OnClick(R.id.calendar_prev_button)
    public void viewPreviousMonth(View view) {
        if(displayedMonth==0){
            displayedMonth=11;
            displayedYear--;
        }else{
            displayedMonth-=1;
        }
        currentDisplayMonth.setText(listOfMonths[displayedMonth]);
        List<MyDate> dates=getAttendanceStatus(displayedYear,displayedMonth);
        populateDates(dates);
    }

    private void populateUI() {
        int year=getCurrentYear();
        int month=getCurrentMonth();
        displayedMonth=month;
        displayedYear=year;
        currentDisplayMonth.setText(listOfMonths[displayedMonth]);
        List<MyDate> dates=getAttendanceStatus(year,month);
        populateDates(dates);
    }

    private void populateDates(List<MyDate> dates) {
        if(dates!=null){
            RecyclerView.LayoutManager lLayout = new GridLayoutManager(mContext, 7);
            attendanceView.setHasFixedSize(true);
            attendanceView.setLayoutManager(lLayout);
            attendanceView.setItemAnimator(new DefaultItemAnimator());
            //attendanceView.addItemDecoration(new DividerItemDecoration(mContext));
            mAdapter = new AttendanceAdapter(dates,displayedYear,displayedMonth);
            attendanceView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            attendanceStatus.setVisibility(View.GONE);
            attendanceView.setVisibility(View.VISIBLE);
        }else{
            attendanceStatus.setVisibility(View.VISIBLE);
            attendanceView.setVisibility(View.GONE);
        }
    }

    private List<MyDate> getAttendanceStatus(int year, int month) {
        MyYear requiredYear=attendance.fetchRequiredYear(year);
        if(requiredYear!=null){
            MyMonth requiredMonth=requiredYear.fetchRequiredMonth(listOfMonths[month]);
            if(requiredMonth!=null) {
                return requiredMonth.getMyDate();
            }else{
                return null;
            }
        }else {
            return null;
        }
    }

    private int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    private int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }
}
