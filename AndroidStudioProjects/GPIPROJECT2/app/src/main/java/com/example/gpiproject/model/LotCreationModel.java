package com.example.gpiproject.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "lotcreation")
public class LotCreationModel {
    private Integer lotNumber;
    private String headerId;
    private String farmerCode;
    private String farmerName;
    private String farmerFatherName;
    private String villageCode;
    @PrimaryKey
    @NonNull
    private String baleNumber;
    private Integer series;
    private String createdBy;
    private String createdDate;
    private String modifiedBy;
    private String modifiedDate;

    public Integer getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(Integer lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getHeaderId() {
        return headerId;
    }

    public void setHeaderId(String headerId) {
        this.headerId = headerId;
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

    public String getFarmerFatherName() {
        return farmerFatherName;
    }

    public void setFarmerFatherName(String farmerFatherName) {
        this.farmerFatherName = farmerFatherName;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    @NonNull
    public String getBaleNumber() {
        return baleNumber;
    }

    public void setBaleNumber(@NonNull String baleNumber) {
        this.baleNumber = baleNumber;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
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

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "LotCreationModel{" +
                "lotNumber=" + lotNumber +
                ", headerId='" + headerId + '\'' +
                ", farmerCode='" + farmerCode + '\'' +
                ", farmerName='" + farmerName + '\'' +
                ", farmerFatherName='" + farmerFatherName + '\'' +
                ", villageCode='" + villageCode + '\'' +
                ", baleNumber='" + baleNumber + '\'' +
                ", series=" + series +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                '}';
    }
}
