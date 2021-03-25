package com.example.newitcdemo.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "Intimation")
public class IntimationModel {
    private  String createdBy;
    @PrimaryKey
    @NonNull
    private  String createdDate;
    private  String empCode;
    private  String inchargeName;
    private  String locationID;
    private  String locationName;

    public IntimationModel(String createdBy, @NonNull String createdDate, String empCode, String inchargeName, String locationID, String locationName) {
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.empCode = empCode;
        this.inchargeName = inchargeName;
        this.locationID = locationID;
        this.locationName = locationName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @NonNull
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(@NonNull String createdDate) {
        this.createdDate = createdDate;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getInchargeName() {
        return inchargeName;
    }

    public void setInchargeName(String inchargeName) {
        this.inchargeName = inchargeName;
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

    @Override
    public String toString() {
        return "IntimationModel{" +
                "createdBy='" + createdBy + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", empCode='" + empCode + '\'' +
                ", inchargeName='" + inchargeName + '\'' +
                ", locationID='" + locationID + '\'' +
                ", locationName='" + locationName + '\'' +
                '}';
    }
}
