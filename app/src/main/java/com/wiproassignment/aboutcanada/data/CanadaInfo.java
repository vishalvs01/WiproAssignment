package com.wiproassignment.aboutcanada.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CanadaInfo {

    @SerializedName("title")
    private String title;

    @SerializedName("rows")
    private ArrayList<Info> rows;

    public String getTitle() {
        return title;
    }

    public ArrayList<Info> getRows() {
        return rows;
    }
}
