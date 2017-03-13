package com.udacity.akki.capstone.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 836158 on 20-02-2017.
 */

public class Test implements Parcelable {
    private String topic;

    private String id;

    @SerializedName("marks_obtained")
    private String marksObtained;

    @SerializedName("passing_marks")
    private String passingMarks;

    private String subject;

    private String doa;

    @SerializedName("max_marks")
    private String maxMarks;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDoa() {
        return doa;
    }

    public void setDoa(String doa) {
        this.doa = doa;
    }

    public String getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(String marksObtained) {
        this.marksObtained = marksObtained;
    }

    public String getPassingMarks() {
        return passingMarks;
    }

    public void setPassingMarks(String passingMarks) {
        this.passingMarks = passingMarks;
    }

    public String getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(String maxMarks) {
        this.maxMarks = maxMarks;
    }

    @Override
    public String toString() {
        return "ClassPojo [topic = " + topic + ", id = " + id + ", marksObtained = " + marksObtained + ", passingMarks = " + passingMarks + ", subject = " + subject + ", doa = " + doa + ", maxMarks = " + maxMarks + "]";
    }



    public Boolean compareDate(String date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            Date date1=sdf.parse(date);
            Date date2=sdf.parse(this.doa);
            return date2.before(date1);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.topic);
        dest.writeString(this.id);
        dest.writeString(this.marksObtained);
        dest.writeString(this.passingMarks);
        dest.writeString(this.subject);
        dest.writeString(this.doa);
        dest.writeString(this.maxMarks);
    }

    public Test() {
    }

    protected Test(Parcel in) {
        this.topic = in.readString();
        this.id = in.readString();
        this.marksObtained = in.readString();
        this.passingMarks = in.readString();
        this.subject = in.readString();
        this.doa = in.readString();
        this.maxMarks = in.readString();
    }

    public static final Parcelable.Creator<Test> CREATOR = new Parcelable.Creator<Test>() {
        @Override
        public Test createFromParcel(Parcel source) {
            return new Test(source);
        }

        @Override
        public Test[] newArray(int size) {
            return new Test[size];
        }
    };
}
