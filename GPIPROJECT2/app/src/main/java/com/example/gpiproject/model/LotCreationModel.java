package com.example.gpiproject.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "lotcreation")
public class LotCreationModel {
    @SerializedName("headerID")
    private String headerId;
    @SerializedName("attribute3")
    private String attribute3;
    @SerializedName("tbLotNumber")
    private Integer lotNumber;
    @SerializedName("farmerCode")
    private String farmerCode;
    @PrimaryKey
    @NonNull
    @SerializedName("baleNumber")
    private String baleNumber;
    @SerializedName("series")
    private Integer series;
    @SerializedName("createdBy")
    private String createdBy;
    @SerializedName("createdDate")
    private String createdDate;

    public String getHeaderId() {
        return headerId;
    }

    public void setHeaderId(String headerId) {
        this.headerId = headerId;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public Integer getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(Integer lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getFarmerCode() {
        return farmerCode;
    }

    public void setFarmerCode(String farmerCode) {
        this.farmerCode = farmerCode;
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

    @Override
    public String toString() {
        return "LotCreationModel{" +
                "headerId='" + headerId + '\'' +
                ", attribute3='" + attribute3 + '\'' +
                ", lotNumber=" + lotNumber +
                ", farmerCode='" + farmerCode + '\'' +
                ", baleNumber='" + baleNumber + '\'' +
                ", series=" + series +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
