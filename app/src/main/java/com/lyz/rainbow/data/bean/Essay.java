package com.lyz.rainbow.data.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ring on 17/7/17.
 */

public class Essay {

    @SerializedName("day")
    private String day;
    @SerializedName("date")
    private String date;
    @SerializedName("title")
    private String title;
    @SerializedName("e_id")
    private int e_id;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }
}
