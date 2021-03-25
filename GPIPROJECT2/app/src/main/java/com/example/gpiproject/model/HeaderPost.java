package com.example.gpiproject.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "headerpost")
public class HeaderPost {
    @PrimaryKey
    @NonNull
    @SerializedName("headerID")
    private String headerID;
    @SerializedName("organizationCode")
    private String organizationCode;
    @SerializedName("buyerCode")
    private String buyerCode;
    @SerializedName("purchaseDocumentNumber")
    private String purchaseDocumentNumber;
    @SerializedName("purchaseDate")
    private String purchaseDate;
    @SerializedName("attribute")
    private String attribute;
    @SerializedName("crop")
    private String crop;
    @SerializedName("variety")
    private String variety;
    @SerializedName("createdBy")
    private String createdBy;

    @NonNull
    public String getHeaderID() {
        return headerID;
    }

    public void setHeaderID(@NonNull String headerID) {
        this.headerID = headerID;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getPurchaseDocumentNumber() {
        return purchaseDocumentNumber;
    }

    public void setPurchaseDocumentNumber(String purchaseDocumentNumber) {
        this.purchaseDocumentNumber = purchaseDocumentNumber;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "HeaderPost{" +
                "headerID='" + headerID + '\'' +
                ", organizationCode='" + organizationCode + '\'' +
                ", buyerCode='" + buyerCode + '\'' +
                ", purchaseDocumentNumber='" + purchaseDocumentNumber + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", attribute='" + attribute + '\'' +
                ", crop='" + crop + '\'' +
                ", variety='" + variety + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
