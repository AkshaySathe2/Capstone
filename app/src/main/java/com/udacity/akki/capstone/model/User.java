package com.udacity.akki.capstone.model;

/**
 * Created by 836158 on 14-02-2017.
 */

public class User {

    private Detail detail;

    private String contact_no;

    private String name;

    private String email_id;

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    @Override
    public String toString() {
        return "ClassPojo [detail = " + detail + ", contact_no = " + contact_no + ", name = " + name + ", email_id = " + email_id + "]";
    }
}
