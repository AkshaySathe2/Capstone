package com.udacity.akki.capstone.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 836158 on 14-02-2017.
 */

public class Installment {

    public static final String STATUS_PAID="PAID";
    public static final String STATUS_PENDING="PENDING";

    private String amount;

    private String status;

    private String date;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean compareDate(String date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            Date date1=sdf.parse(date);
            Date date2=sdf.parse(this.date);
            return date2.before(date1);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public String toString() {
        return "ClassPojo [amount = " + amount + ", status = " + status + ", date = " + date + "]";
    }

}
