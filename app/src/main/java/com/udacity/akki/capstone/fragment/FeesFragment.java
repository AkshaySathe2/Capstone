package com.udacity.akki.capstone.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.akki.capstone.R;
import com.udacity.akki.capstone.adapter.InstallmentAdapter;
import com.udacity.akki.capstone.custom.DividerItemDecoration;
import com.udacity.akki.capstone.model.Fees;
import com.udacity.akki.capstone.model.Installment;
import com.udacity.akki.capstone.utility.Util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FeesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FeesFragment extends Fragment {

    private Fees fees;
    private Context mContext;
    private ProgressDialog progressDialog;
    private InstallmentAdapter mAdapter;
    @BindView(R.id.txt_fees_date)
    TextView feesDate;
    @BindView(R.id.txt_fees_value)
    TextView feesAmount;
    @BindView(R.id.txt_total)
    TextView totalAmountText;
    @BindView(R.id.txt_paid)
    TextView paidAmountText;
    @BindView(R.id.txt_remaining)
    TextView remainingAmountText;
    @BindView(R.id.recycle_fees_list)
    RecyclerView feesView;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

    public void setFees(Fees fees) {
        this.fees = fees;
    }

    public FeesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fees, container, false);
        ButterKnife.bind(this, view);
        myToolbar.setTitle("Fees");
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

    public static Fragment newInstance() {
        return new FeesFragment();
    }

    private void populateUI() {

        if (fees == null || fees.getInstallment().length == 0) {
            Util.showToast(mContext, getString(R.string.no_data_available));
        } else {

            populateHeading();
            populateList();

        }

    }





    private void populateList() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        feesView.setLayoutManager(mLayoutManager);
        feesView.setItemAnimator(new DefaultItemAnimator());
        feesView.addItemDecoration(new DividerItemDecoration(mContext));
        mAdapter = new InstallmentAdapter(fees.getInstallment());
        feesView.setAdapter(mAdapter);


    }

    private void populateHeading() {
        long paidAmount = 0;
        long remainingAmount = 0;
        long totalAmount = 0;
        long nextInstallmentAmount = 0;
        SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date nextInstallmentDate = null;
        for (Installment temp : fees.getInstallment()) {
            if (temp.getStatus().equalsIgnoreCase(Installment.STATUS_PAID)) {
                paidAmount = paidAmount + Long.valueOf(temp.getAmount());
            } else {
                remainingAmount = remainingAmount + Long.valueOf(temp.getAmount());
                try {
                    Date date = sdfFormat.parse(temp.getDate());
                    if (nextInstallmentDate != null) {
                        //long temp1 = nextInstallmentDate.getTime() - date.getTime();
                        if (nextInstallmentDate.getTime() - date.getTime() > 0) {
                            nextInstallmentDate = date;
                            nextInstallmentAmount = Long.valueOf(temp.getAmount());
                        }
                    } else {
                        nextInstallmentDate = date;
                        nextInstallmentAmount = Long.valueOf(temp.getAmount());
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
            totalAmount = totalAmount + Long.valueOf(temp.getAmount());
        }
        if (nextInstallmentDate != null) {
            feesDate.setText(Util.formatDate(nextInstallmentDate, Util.DD_MM_YYYY));
        } else {
            feesDate.setText(R.string.na);
        }
        feesAmount.setText(String.valueOf(nextInstallmentAmount));
        totalAmountText.setText(String.valueOf(totalAmount));
        paidAmountText.setText(String.valueOf(paidAmount));
        remainingAmountText.setText(String.valueOf(remainingAmount));

    }


}
