package com.example.gpiproject.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "variety")
public class Variety {
    @PrimaryKey
    @NonNull
    private  String variety;
    private  String varietyType;
    private  String varietyName;
    private  String varietyDescription;
    private  String status;

    public Variety(String variety, String varietyType, String varietyName, String varietyDescription, String status) {
        this.variety = variety;
        this.varietyType = varietyType;
        this.varietyName = varietyName;
        this.varietyDescription = varietyDescription;
        this.status = status;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getVarietyType() {
        return varietyType;
    }

    public void setVarietyType(String varietyType) {
        this.varietyType = varietyType;
    }

    public String getVarietyName() {
        return varietyName;
    }

    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    }

    public String getVarietyDescription() {
        return varietyDescription;
    }

    public void setVarietyDescription(String varietyDescription) {
        this.varietyDescription = varietyDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Variety{" +
                "variety='" + variety + '\'' +
                ", varietyType='" + varietyType + '\'' +
                ", varietyName='" + varietyName + '\'' +
                ", varietyDescription='" + varietyDescription + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
