package com.udacity.akki.capstone.model;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 836158 on 20-02-2017.
 */

public class Test {
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

}
