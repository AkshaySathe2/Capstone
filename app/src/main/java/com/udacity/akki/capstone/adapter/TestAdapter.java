package com.udacity.akki.capstone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.akki.capstone.R;
import com.udacity.akki.capstone.model.Installment;
import com.udacity.akki.capstone.model.Test;
import com.udacity.akki.capstone.utility.Util;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Akshay on 09-Mar-17.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {

    private Context mContext;
    private Test[] tests;

    public TestAdapter(Test[] test) {
        tests=test;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.test_list_row, parent, false);

        return new TestAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Test test=tests[position];
        holder.serialNo.setText(String.format("%s.", String.valueOf(position + 1)));
        holder.testDate.setText(test.getDoa());
        holder.subject.setText(test.getSubject());
        holder.testName.setText(test.getTopic());
        holder.score.setText(String.format(Locale.ENGLISH,"%s / %s", test.getMarksObtained(), test.getMaxMarks()));
    }

    @Override
    public int getItemCount() {
        return tests.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_test_serialNos)TextView serialNo;
        @BindView(R.id.txt_test_date) TextView testDate;
        @BindView(R.id.txt_test_subject_name) TextView subject;
        @BindView(R.id.txt_test_name) TextView testName;
        @BindView(R.id.txt_test_score) TextView score;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
