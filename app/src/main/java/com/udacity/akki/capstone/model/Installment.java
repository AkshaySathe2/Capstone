package com.udacity.akki.capstone.model;

/**
 * Created by 836158 on 14-02-2017.
 */

public class Installment {

    private String amount;

    private String status;

    private String date;

    public String getAmount ()
    {
        return amount;
    }

    public void setAmount (String amount)
    {
        this.amount = amount;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getDate ()
    {
        return date;
    }

    public void setDate (String date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [amount = "+amount+", status = "+status+", date = "+date+"]";
    }

}
