package com.udacity.akki.capstone.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.akki.capstone.R;
import com.udacity.akki.capstone.model.Installment;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Akshay on 07-Mar-17.
 */

public class InstallmentAdapter extends RecyclerView.Adapter<InstallmentAdapter.MyViewHolder> {

    private Context mContext;
    private Installment[] installments;

    public InstallmentAdapter(Installment[] installment) {
        installments=installment;
    }

    @Override
    public InstallmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.installment_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InstallmentAdapter.MyViewHolder holder, int position) {
        Installment installment = installments[position];
        holder.installmentName.setText(String.format(Locale.ENGLISH,"Installment %d", position + 1));
        holder.installmentDate.setText(installment.getDate());
        holder.installmentValue.setText(installment.getAmount());
        holder.installmentStatus.setText(installment.getStatus().equalsIgnoreCase(Installment.STATUS_PAID)?"P":"N");
        int colorCode=installment.getStatus().equalsIgnoreCase(Installment.STATUS_PAID)?R.color.color_fees_status_paid:R.color.color_fees_status_pending;
        holder.installmentStatus.setBackgroundColor(ContextCompat.getColor(mContext,colorCode));
    }

    @Override
    public int getItemCount() {
        return installments.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_installment_name) TextView installmentName;
        @BindView(R.id.txt_installment_date) TextView installmentDate;
        @BindView(R.id.txt_installment_value) TextView installmentValue;
        @BindView(R.id.txt_installment_status) TextView installmentStatus;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
