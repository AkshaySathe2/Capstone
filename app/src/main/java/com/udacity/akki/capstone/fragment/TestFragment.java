package com.udacity.akki.capstone.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.akki.capstone.R;
import com.udacity.akki.capstone.adapter.TestAdapter;
import com.udacity.akki.capstone.custom.DividerItemDecoration;
import com.udacity.akki.capstone.model.Test;
import com.udacity.akki.capstone.utility.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {

    private static final String PARCELABLE_KEY = "Test";
    private static final String PARCELABLE_KEY_2 = "LatestTest";
    private List<Test> test = new ArrayList<>();
    private Context mContext;
    private TestAdapter mAdapter;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.txt_testScore)
    TextView score;
    @BindView(R.id.txt_topic)
    TextView topic;
    @BindView(R.id.txt_subject)
    TextView subject;
    @BindView(R.id.txt_test_date)
    TextView testDate;
    @BindView(R.id.recycle_test_list)
    RecyclerView testView;
    private Test latestTest;

    public TestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        if (savedInstanceState != null) {
            test = savedInstanceState.getParcelableArrayList(PARCELABLE_KEY);
            latestTest = savedInstanceState.getParcelable(PARCELABLE_KEY_2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        ButterKnife.bind(this, view);
        myToolbar.setTitle(getString(R.string.test));
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(PARCELABLE_KEY, (ArrayList<? extends Parcelable>) test);
        outState.putParcelable(PARCELABLE_KEY_2, latestTest);
    }

    private void populateUI() {
        if (test == null || test.size() == 0) {
            Util.showToast(mContext, R.string.no_data_available);
        } else {
            populateHeading();
            populateList();
        }
    }

    private void populateList() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        testView.setLayoutManager(mLayoutManager);
        testView.setItemAnimator(new DefaultItemAnimator());
        testView.addItemDecoration(new DividerItemDecoration(mContext));
        mAdapter = new TestAdapter(test);
        testView.setAdapter(mAdapter);
    }

    private void populateHeading() {
        //DateFormat sdfForDisplay = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        if (latestTest != null) {
            score.setText(latestTest.getMarksObtained() + " / " + latestTest.getMaxMarks());
            subject.setText(latestTest.getSubject());
            topic.setText(latestTest.getTopic());
            testDate.setText(latestTest.getDoa());
        }
    }

    public void setTest(List<Test> test) {
        this.test.clear();
        this.test.addAll(test);
    }

    public void setLatestTest(Test latestTest) {
        this.latestTest = latestTest;
    }
}
