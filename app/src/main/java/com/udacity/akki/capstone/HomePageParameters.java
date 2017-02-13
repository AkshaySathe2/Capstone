package com.udacity.akki.capstone;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 836158 on 13-02-2017.
 */

public class HomePageParameters {

    @SerializedName("fees_amount")
    private String feesAmount;

    @SerializedName("myList")
    private String[] notificationsList;

}
