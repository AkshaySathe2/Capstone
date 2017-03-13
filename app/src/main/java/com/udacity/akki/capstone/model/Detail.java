package com.udacity.akki.capstone.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by 836158 on 14-02-2017.
 */

public class Detail {

    private List<Test> test=new ArrayList<>();

    private Attendance attendance;

    private List<Notification> notification;

    private Fees fees;

    public List<Test> getTest() {
        return test;
    }

    public void setTest(List<Test> test) {
        this.test = test;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public List<Notification> getNotification() {
        return notification;
    }

    public void setNotification(List<Notification> notification) {
        this.notification = notification;
    }

    public Fees getFees() {
        return fees;
    }

    public void setFees(Fees fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "ClassPojo [test = " + test + ", attendance = " + attendance + ", notification = " + notification + ", fees = " + fees + "]";
    }

    public Test getLatestTestScores() {
        Test latestTest = null;
        for (Test t : test) {
            if (latestTest == null) {
                    latestTest = t;

            } else {
                if (!t.compareDate(latestTest.getDoa())) {
                    latestTest=t;
                }
            }
        }
        return latestTest;
    }
}


