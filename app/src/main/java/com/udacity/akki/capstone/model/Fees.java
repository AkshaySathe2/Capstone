package com.udacity.akki.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 836158 on 14-02-2017.
 */

public class Fees implements Parcelable{

    private List<Installment> installment;

    private String total_fees;



    public List<Installment> getInstallment() {
        return installment;
    }

    public void setInstallment(List<Installment> installment) {
        this.installment = installment;
    }

    public String getTotal_fees() {
        return total_fees;
    }

    public void setTotal_fees(String total_fees) {
        this.total_fees = total_fees;
    }

    public Fees() {
    }

    public Installment getPendingInstallment() {
        Installment latestInstallment = null;
        for (Installment i : installment) {
            if (latestInstallment == null) {
                if (i.getStatus().equalsIgnoreCase(Installment.STATUS_PENDING)) {
                    latestInstallment = i;
                }
            } else {
                if (i.getStatus().equalsIgnoreCase(Installment.STATUS_PENDING) && i.compareDate(latestInstallment.getDate())) {
                    latestInstallment=i;
                }
            }
        }
        return latestInstallment;
    }

    @Override
    public String toString() {
        return "ClassPojo [installment = " + installment + ", total_fees = " + total_fees + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(total_fees);
        parcel.writeList(installment);
    }

    protected Fees(Parcel in) {
        total_fees = in.readString();
        in.readList(installment,Installment.class.getClassLoader());
    }

    public static final Creator<Fees> CREATOR = new Creator<Fees>() {
        @Override
        public Fees createFromParcel(Parcel in) {
            return new Fees(in);
        }

        @Override
        public Fees[] newArray(int size) {
            return new Fees[size];
        }
    };

}
