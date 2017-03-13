package com.udacity.akki.capstone.model;

import java.util.List;

/**
 * Created by 836158 on 14-02-2017.
 */

public class Fees {

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

}
