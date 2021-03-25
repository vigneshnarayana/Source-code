package com.example.newitcdemo.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "Product")
public class Product {
    @PrimaryKey
    @NonNull
    private  String  partNumber;
    private  String  quantity;
    private  String  acceptedStatus;
    private  String  rejectedStatus;
    private  String  createdBy;
    private  String  createdDate;

    public Product(@NonNull String partNumber, String quantity, String acceptedStatus, String rejectedStatus, String createdBy, String createdDate) {
        this.partNumber = partNumber;
        this.quantity = quantity;
        this.acceptedStatus = acceptedStatus;
        this.rejectedStatus = rejectedStatus;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    @NonNull
    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(@NonNull String partNumber) {
        this.partNumber = partNumber;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAcceptedStatus() {
        return acceptedStatus;
    }

    public void setAcceptedStatus(String acceptedStatus) {
        this.acceptedStatus = acceptedStatus;
    }

    public String getRejectedStatus() {
        return rejectedStatus;
    }

    public void setRejectedStatus(String rejectedStatus) {
        this.rejectedStatus = rejectedStatus;
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
        return "Product{" +
                "partNumber='" + partNumber + '\'' +
                ", quantity='" + quantity + '\'' +
                ", acceptedStatus='" + acceptedStatus + '\'' +
                ", rejectedStatus='" + rejectedStatus + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
