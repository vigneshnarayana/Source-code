package com.example.gpiproject.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="weighment")
public class Weighment {
    private String headerId;
    @PrimaryKey
    @NonNull
    private String baleNumber;
    private String netWeight;
    private String createdDate;
    private String createdBy;

    public String getHeaderId() {
        return headerId;
    }

    public void setHeaderId(String headerId) {
        this.headerId = headerId;
    }

    @NonNull
    public String getBaleNumber() {
        return baleNumber;
    }

    public void setBaleNumber(@NonNull String baleNumber) {
        this.baleNumber = baleNumber;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Weighment{" +
                "headerId='" + headerId + '\'' +
                ", baleNumber='" + baleNumber + '\'' +
                ", netWeight='" + netWeight + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
