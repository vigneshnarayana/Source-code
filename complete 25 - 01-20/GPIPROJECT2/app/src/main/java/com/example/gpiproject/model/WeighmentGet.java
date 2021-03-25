package com.example.gpiproject.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity(tableName = "WeighmentGet")
public class WeighmentGet {

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
    @SerializedName("rejectStatus")
    String rejectStatus;
    @SerializedName("rejectedType")
    String rejectedType;

    public WeighmentGet(String headerID, @NonNull String baleNumber, String buyerGrade, String classGrade, String rate, String rejectStatus, String rejectedType) {
        this.headerID = headerID;
        this.baleNumber = baleNumber;
        this.buyerGrade = buyerGrade;
        this.classGrade = classGrade;
        this.rate = rate;
        this.rejectStatus = rejectStatus;
        this.rejectedType = rejectedType;
    }

    public String getHeaderID() {
        return headerID;
    }

    public void setHeaderID(String headerID) {
        this.headerID = headerID;
    }

    public String getBaleNumber() {
        return baleNumber;
    }

    public void setBaleNumber(String baleNumber) {
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

    public String getRejectStatus() {
        return rejectStatus;
    }

    public void setRejectStatus(String rejectStatus) {
        this.rejectStatus = rejectStatus;
    }

    public String getRejectedType() {
        return rejectedType;
    }

    public void setRejectedType(String rejectedType) {
        this.rejectedType = rejectedType;
    }

    @Override
    public String toString() {
        return "WeighmentGet{" +
                "headerID='" + headerID + '\'' +
                ", baleNumber='" + baleNumber + '\'' +
                ", buyerGrade='" + buyerGrade + '\'' +
                ", classGrade='" + classGrade + '\'' +
                ", rate='" + rate + '\'' +
                ", rejectStatus='" + rejectStatus + '\'' +
                ", rejectedType='" + rejectedType + '\'' +
                '}';
    }
}
