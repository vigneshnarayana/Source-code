package com.example.gpiproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HeaderPostCreation {

    @SerializedName("purchaseHeaderID")
    @Expose
    private List<HeaderPost> headerDetails = null;

    public List<HeaderPost> getHeaderDetails() {
        return headerDetails;
    }

    public void setHeaderDetails(List<HeaderPost> headerDetails) {
        this.headerDetails = headerDetails;
    }
}
