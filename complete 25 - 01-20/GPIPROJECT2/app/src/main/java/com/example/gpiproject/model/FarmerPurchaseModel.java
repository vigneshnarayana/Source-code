package com.example.gpiproject.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "FarmerPurchaseModel")
public class FarmerPurchaseModel {
    private String tbLotNumber;
    private String headerID;
    private String attribute3;
    private String orgnCode;
    private String buyerGrade;
    private String farmerCode;
    private String farmerName;
    @PrimaryKey
    @NonNull
    private String baleNumber;
    private String series;
    private String crop;
    private String variety;
    private String rejectStatus;
    private String status;
    private String headerStatus;
    private String createdBy;
    private String createdDate;

    public FarmerPurchaseModel(String tbLotNumber, String headerID, String attribute3, String orgnCode, String buyerGrade, String farmerCode, String farmerName, @NonNull String baleNumber, String series, String crop, String variety, String rejectStatus, String status, String headerStatus, String createdBy, String createdDate) {
        this.tbLotNumber = tbLotNumber;
        this.headerID = headerID;
        this.attribute3 = attribute3;
        this.orgnCode = orgnCode;
        this.buyerGrade = buyerGrade;
        this.farmerCode = farmerCode;
        this.farmerName = farmerName;
        this.baleNumber = baleNumber;
        this.series = series;
        this.crop = crop;
        this.variety = variety;
        this.rejectStatus = rejectStatus;
        this.status = status;
        this.headerStatus = headerStatus;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public String getTbLotNumber() {
        return tbLotNumber;
    }

    public void setTbLotNumber(String tbLotNumber) {
        this.tbLotNumber = tbLotNumber;
    }

    public String getHeaderID() {
        return headerID;
    }

    public void setHeaderID(String headerID) {
        this.headerID = headerID;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getOrgnCode() {
        return orgnCode;
    }

    public void setOrgnCode(String orgnCode) {
        this.orgnCode = orgnCode;
    }

    public String getBuyerGrade() {
        return buyerGrade;
    }

    public void setBuyerGrade(String buyerGrade) {
        this.buyerGrade = buyerGrade;
    }

    public String getFarmerCode() {
        return farmerCode;
    }

    public void setFarmerCode(String farmerCode) {
        this.farmerCode = farmerCode;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    @NonNull
    public String getBaleNumber() {
        return baleNumber;
    }

    public void setBaleNumber(@NonNull String baleNumber) {
        this.baleNumber = baleNumber;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getRejectStatus() {
        return rejectStatus;
    }

    public void setRejectStatus(String rejectStatus) {
        this.rejectStatus = rejectStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHeaderStatus() {
        return headerStatus;
    }

    public void setHeaderStatus(String headerStatus) {
        this.headerStatus = headerStatus;
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
        return "FarmerPurchaseModel{" +
                "tbLotNumber='" + tbLotNumber + '\'' +
                ", headerID='" + headerID + '\'' +
                ", attribute3='" + attribute3 + '\'' +
                ", orgnCode='" + orgnCode + '\'' +
                ", buyerGrade='" + buyerGrade + '\'' +
                ", farmerCode='" + farmerCode + '\'' +
                ", farmerName='" + farmerName + '\'' +
                ", baleNumber='" + baleNumber + '\'' +
                ", series='" + series + '\'' +
                ", crop='" + crop + '\'' +
                ", variety='" + variety + '\'' +
                ", rejectStatus='" + rejectStatus + '\'' +
                ", status='" + status + '\'' +
                ", headerStatus='" + headerStatus + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
