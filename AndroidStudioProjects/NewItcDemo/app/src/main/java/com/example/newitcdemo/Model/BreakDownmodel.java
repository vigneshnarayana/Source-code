package com.example.newitcdemo.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "BreakDown")
public class BreakDownmodel {
    private  String empCode;
    private  String locationID;
    private  String locationName;
    private  String inchargeName;
    private  String createdBy;
    @PrimaryKey
    @NonNull
    private  String createdDate;

    public BreakDownmodel(String empCode, String locationID, String locationName, String inchargeName, String createdBy, String createdDate) {
        this.empCode = empCode;
        this.locationID = locationID;
        this.locationName = locationName;
        this.inchargeName = inchargeName;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getInchargeName() {
        return inchargeName;
    }

    public void setInchargeName(String inchargeName) {
        this.inchargeName = inchargeName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "BreakDownmodel{" +
                "empCode='" + empCode + '\'' +
                ", locationID='" + locationID + '\'' +
                ", locationName='" + locationName + '\'' +
                ", inchargeName='" + inchargeName + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
