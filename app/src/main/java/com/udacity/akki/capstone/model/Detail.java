package com.udacity.akki.capstone.model;



/**
 * Created by 836158 on 14-02-2017.
 */

public class Detail {

    private Test[] test;

    private Attendance attendance;

    private Notification[] notification;

    private Fees fees;

    public Test[] getTest() {
        return test;
    }

    public void setTest(Test[] test) {
        this.test = test;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public Notification[] getNotification() {
        return notification;
    }

    public void setNotification(Notification[] notification) {
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
                if (t.compareDate(latestTest.getDoa())) {
                    latestTest=t;
                }
            }
        }
        return latestTest;
    }
}


