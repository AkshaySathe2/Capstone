package com.udacity.akki.capstone.model;

/**
 * Created by 836158 on 20-02-2017.
 */

public class Notification {

    private String message;

    private String id;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", id = "+id+"]";
    }

}
