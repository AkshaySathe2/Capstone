package com.udacity.akki.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 836158 on 20-02-2017.
 */
public class Attendance implements Parcelable {

    @SerializedName("s_year")
    private List<MyYear> myYear;

    public List<MyYear> getMyYear()
    {
        return myYear;
    }

    public void setMyYear(List<MyYear> myYear)
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.myYear);
    }

    public Attendance() {
    }

    protected Attendance(Parcel in) {
        this.myYear = new ArrayList<MyYear>();
        in.readList(this.myYear, MyYear.class.getClassLoader());
    }

    public static final Parcelable.Creator<Attendance> CREATOR = new Parcelable.Creator<Attendance>() {
        @Override
        public Attendance createFromParcel(Parcel source) {
            return new Attendance(source);
        }

        @Override
        public Attendance[] newArray(int size) {
            return new Attendance[size];
        }
    };
}
