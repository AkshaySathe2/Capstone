package com.udacity.akki.capstone.model;

/**
 * Created by 836158 on 20-02-2017.
 */
public class MyDate {

    private String value;

    private String date;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ClassPojo [value = " + value + ", date = " + date + "]";
    }

}



