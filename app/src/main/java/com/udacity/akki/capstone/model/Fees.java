package com.udacity.akki.capstone.model;

/**
 * Created by 836158 on 14-02-2017.
 */

public class Fees {

    private Installment[] installment;

    private String total_fees;

    public Installment[] getInstallment() {
        return installment;
    }

    public void setInstallment(Installment[] installment) {
        this.installment = installment;
    }

    public String getTotal_fees() {
        return total_fees;
    }

    public void setTotal_fees(String total_fees) {
        this.total_fees = total_fees;
    }

    @Override
    public String toString() {
        return "ClassPojo [installment = " + installment + ", total_fees = " + total_fees + "]";
    }

}