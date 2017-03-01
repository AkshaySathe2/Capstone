package com.udacity.akki.capstone.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.akki.capstone.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeesFragment extends Fragment {



    public FeesFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fees, container, false);
    }

    public static Fragment newInstance() {
        return new FeesFragment();
    }
}
