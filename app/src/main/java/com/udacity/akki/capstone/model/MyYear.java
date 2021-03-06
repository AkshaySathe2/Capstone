package com.udacity.akki.capstone.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 836158 on 20-02-2017.
 */
public class MyYear {


    private String value;

    @SerializedName("s_month")
    private List<MyMonth> myMonth;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<MyMonth> getMyMonth() {
        return myMonth;
    }

    public void setMyMonth(List<MyMonth> myMonth) {
        this.myMonth = myMonth;
    }

    public MyMonth fetchRequiredMonth(String month) {
        for (MyMonth m : myMonth) {
            if (m.getValue().equalsIgnoreCase(month)) {
                return m;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "ClassPojo [value = " + value + ", myMonth = " + myMonth + "]";
    }

}
