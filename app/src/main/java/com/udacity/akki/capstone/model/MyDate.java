package com.udacity.akki.capstone.model;

/**
 * Created by 836158 on 20-02-2017.
 */
public class MyDate {

    private String value;

    private String date;

    private boolean isEnabled = true;

    public MyDate(int date, String value, boolean isEnabled) {
        this.date=String.valueOf(date);
        this.value=value;
        this.isEnabled=isEnabled;
    }


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

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public String toString() {
        return "ClassPojo [value = " + value + ", date = " + date + "]";
    }

    public MyDate() {
    }
}



