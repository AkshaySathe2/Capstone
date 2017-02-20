package com.udacity.akki.capstone.model;

/**
 * Created by 836158 on 20-02-2017.
 */

public class Test {
    private String topic;

    private String id;

    private String marks_obtained;

    private String passing_marks;

    private String subject;

    private String doa;

    private String max_marks;

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

    public String getMarks_obtained() {
        return marks_obtained;
    }

    public void setMarks_obtained(String marks_obtained) {
        this.marks_obtained = marks_obtained;
    }

    public String getPassing_marks() {
        return passing_marks;
    }

    public void setPassing_marks(String passing_marks) {
        this.passing_marks = passing_marks;
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

    public String getMax_marks() {
        return max_marks;
    }

    public void setMax_marks(String max_marks) {
        this.max_marks = max_marks;
    }

    @Override
    public String toString() {
        return "ClassPojo [topic = " + topic + ", id = " + id + ", marks_obtained = " + marks_obtained + ", passing_marks = " + passing_marks + ", subject = " + subject + ", doa = " + doa + ", max_marks = " + max_marks + "]";
    }
}
