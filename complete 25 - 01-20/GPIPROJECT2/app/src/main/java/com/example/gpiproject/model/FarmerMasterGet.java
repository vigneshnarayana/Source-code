package com.example.gpiproject.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "farmerMaster")
public class FarmerMasterGet {
    @PrimaryKey
    @NonNull
    private String farmerCode;
    private String farmerCategory;
    private String farmerName;
    private String farM_FATHER_NAME;
    private String villageCode;
    private String soilType;
    private String farM_ADDRESS1;
    private String farM_ADDRESS2;
    private String farM_ADDRESS3;
    private String country;
    private String pinCode;
    private String mobileNo;
    private String alertMsg;
    private String attributE1;
    private String attributE4;
    private String createdBy;
    private String createdDate;
    private String updatedBy;
    private String updatedDate;
    private String status;
    private String flag;

    public FarmerMasterGet( String farmerCode, String farmerCategory, String farmerName, String farM_FATHER_NAME, String villageCode, String soilType, String farM_ADDRESS1, String farM_ADDRESS2, String farM_ADDRESS3, String country, String pinCode, String mobileNo, String alertMsg, String attributE1, String attributE4, String createdBy, String createdDate, String updatedBy, String updatedDate, String status, String flag) {
        this.farmerCode = farmerCode;
        this.farmerCategory = farmerCategory;
        this.farmerName = farmerName;
        this.farM_FATHER_NAME = farM_FATHER_NAME;
        this.villageCode = villageCode;
        this.soilType = soilType;
        this.farM_ADDRESS1 = farM_ADDRESS1;
        this.farM_ADDRESS2 = farM_ADDRESS2;
        this.farM_ADDRESS3 = farM_ADDRESS3;
        this.country = country;
        this.pinCode = pinCode;
        this.mobileNo = mobileNo;
        this.alertMsg = alertMsg;
        this.attributE1 = attributE1;
        this.attributE4 = attributE4;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.status = status;
        this.flag = flag;
    }

    public String getFarmerCode() {
        return farmerCode;
    }

    public void setFarmerCode(String farmerCode) {
        this.farmerCode = farmerCode;
    }

    public String getFarmerCategory() {
        return farmerCategory;
    }

    public void setFarmerCategory(String farmerCategory) {
        this.farmerCategory = farmerCategory;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getFarM_FATHER_NAME() {
        return farM_FATHER_NAME;
    }

    public void setFarM_FATHER_NAME(String farM_FATHER_NAME) {
        this.farM_FATHER_NAME = farM_FATHER_NAME;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getSoilType() {
        return soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public String getFarM_ADDRESS1() {
        return farM_ADDRESS1;
    }

    public void setFarM_ADDRESS1(String farM_ADDRESS1) {
        this.farM_ADDRESS1 = farM_ADDRESS1;
    }

    public String getFarM_ADDRESS2() {
        return farM_ADDRESS2;
    }

    public void setFarM_ADDRESS2(String farM_ADDRESS2) {
        this.farM_ADDRESS2 = farM_ADDRESS2;
    }

    public String getFarM_ADDRESS3() {
        return farM_ADDRESS3;
    }

    public void setFarM_ADDRESS3(String farM_ADDRESS3) {
        this.farM_ADDRESS3 = farM_ADDRESS3;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAlertMsg() {
        return alertMsg;
    }

    public void setAlertMsg(String alertMsg) {
        this.alertMsg = alertMsg;
    }

    public String getAttributE1() {
        return attributE1;
    }

    public void setAttributE1(String attributE1) {
        this.attributE1 = attributE1;
    }

    public String getAttributE4() {
        return attributE4;
    }

    public void setAttributE4(String attributE4) {
        this.attributE4 = attributE4;
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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "FarmerMasterGet{" +
                "farmerCode='" + farmerCode + '\'' +
                ", farmerCategory='" + farmerCategory + '\'' +
                ", farmerName='" + farmerName + '\'' +
                ", farM_FATHER_NAME='" + farM_FATHER_NAME + '\'' +
                ", villageCode='" + villageCode + '\'' +
                ", soilType='" + soilType + '\'' +
                ", farM_ADDRESS1='" + farM_ADDRESS1 + '\'' +
                ", farM_ADDRESS2='" + farM_ADDRESS2 + '\'' +
                ", farM_ADDRESS3='" + farM_ADDRESS3 + '\'' +
                ", country='" + country + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", alertMsg='" + alertMsg + '\'' +
                ", attributE1='" + attributE1 + '\'' +
                ", attributE4='" + attributE4 + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedDate='" + updatedDate + '\'' +
                ", status='" + status + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
