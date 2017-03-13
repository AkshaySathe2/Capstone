package com.udacity.akki.capstone.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 836158 on 20-02-2017.
 */
public class MyMonth {

    private String value;

    @SerializedName("s_date")
    private List<MyDate> myDate;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<MyDate> getMyDate() {
        return myDate;
    }

    public void setMyDate(List<MyDate> myDate) {
        this.myDate = myDate;
    }

    @Override
    public String toString() {
        return "ClassPojo [value = " + value + ", myDate = " + myDate + "]";
    }

}
