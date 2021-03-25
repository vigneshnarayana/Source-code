package com.example.gpiproject.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName="purchase")
public class PurchaseModel {
    @SerializedName("headerID")
    String headerID;
    @PrimaryKey
    @NonNull
    @SerializedName("baleNumber")
    String baleNumber;
    @SerializedName("buyerGrade")
    String buyerGrade;
    @SerializedName("classGrade")
    String classGrade;
    @SerializedName("rate")
    String rate;
    @SerializedName("rejectedStatus")
    String rejectedStatus;
    @SerializedName("rejectedType")
    String rejectedType;
    @SerializedName("remark")
    String remark;

    public String getHeaderID() {
        return headerID;
    }

    public void setHeaderID(String headerID) {
        this.headerID = headerID;
    }

    @NonNull
    public String getBaleNumber() {
        return baleNumber;
    }

    public void setBaleNumber(@NonNull String baleNumber) {
        this.baleNumber = baleNumber;
    }

    public String getBuyerGrade() {
        return buyerGrade;
    }

    public void setBuyerGrade(String buyerGrade) {
        this.buyerGrade = buyerGrade;
    }

    public String getClassGrade() {
        return classGrade;
    }

    public void setClassGrade(String classGrade) {
        this.classGrade = classGrade;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRejectedStatus() {
        return rejectedStatus;
    }

    public void setRejectedStatus(String rejectedStatus) {
        this.rejectedStatus = rejectedStatus;
    }

    public String getRejectedType() {
        return rejectedType;
    }

    public void setRejectedType(String rejectedType) {
        this.rejectedType = rejectedType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "PurchaseModel{" +
                "headerID='" + headerID + '\'' +
                ", baleNumber='" + baleNumber + '\'' +
                ", buyerGrade='" + buyerGrade + '\'' +
                ", classGrade='" + classGrade + '\'' +
                ", rate='" + rate + '\'' +
                ", rejectedStatus='" + rejectedStatus + '\'' +
                ", rejectedType='" + rejectedType + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
