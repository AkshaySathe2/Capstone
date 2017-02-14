package com.udacity.akki.capstone.model;

/**
 * Created by 836158 on 14-02-2017.
 */

public class Detail {

    private Fees fees;

    public Fees getFees ()
    {
        return fees;
    }

    public void setFees (Fees fees)
    {
        this.fees = fees;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [fees = "+fees+"]";
    }

}
