package com.example.tvs.activity.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Product")
public class Product {
    @PrimaryKey
    @NonNull
    private String partNumber;
    private String quantity;
    private String acceptedQuantity;
    private String rejectedQuantity;
    private String empCode;
    private String createdDate;
    private String locationCode;

    public Product(@NonNull String partNumber, String quantity, String acceptedQuantity, String rejectedQuantity, String empCode, String createdDate, String locationCode) {
        this.partNumber = partNumber;
        this.quantity = quantity;
        this.acceptedQuantity = acceptedQuantity;
        this.rejectedQuantity = rejectedQuantity;
        this.empCode = empCode;
        this.createdDate = createdDate;
        this.locationCode = locationCode;
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

    public String getAcceptedQuantity() {
        return acceptedQuantity;
    }

    public void setAcceptedQuantity(String acceptedQuantity) {
        this.acceptedQuantity = acceptedQuantity;
    }

    public String getRejectedQuantity() {
        return rejectedQuantity;
    }

    public void setRejectedQuantity(String rejectedQuantity) {
        this.rejectedQuantity = rejectedQuantity;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    @Override
    public String toString() {
        return "Product{" +
                "partNumber='" + partNumber + '\'' +
                ", quantity='" + quantity + '\'' +
                ", acceptedQuantity='" + acceptedQuantity + '\'' +
                ", rejectedQuantity='" + rejectedQuantity + '\'' +
                ", empCode='" + empCode + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", locationCode='" + locationCode + '\'' +
                '}';
    }
}
