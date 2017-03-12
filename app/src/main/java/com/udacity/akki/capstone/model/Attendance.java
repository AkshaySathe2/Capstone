package com.udacity.akki.capstone.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 836158 on 20-02-2017.
 */
public class Attendance {

    @SerializedName("s_year")
    private MyYear[] myYear;

    public MyYear[] getMyYear()
    {
        return myYear;
    }

    public void setMyYear(MyYear[] myYear)
    {
        this.myYear = myYear;
    }

    public MyYear fetchRequiredYear(int year){
        for(MyYear y:myYear){
            if(y.getValue().equalsIgnoreCase(String.valueOf(year))){
                return y;
            }
        }
        return  null;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [myYear = "+ myYear +"]";
    }

}
