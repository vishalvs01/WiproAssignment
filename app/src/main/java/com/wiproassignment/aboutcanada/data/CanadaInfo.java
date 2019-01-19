package com.wiproassignment.aboutcanada.data;

import com.google.gson.annotations.SerializedName;
import com.wiproassignment.common.db.entity.InfoEntity;

import java.util.ArrayList;

public class CanadaInfo {

    @SerializedName("title")
    private String title;

    @SerializedName("rows")
    private ArrayList<InfoEntity> rows;

    public String getTitle() {
        return title;
    }

    public ArrayList<InfoEntity> getRows() {
        return rows;
    }
}
