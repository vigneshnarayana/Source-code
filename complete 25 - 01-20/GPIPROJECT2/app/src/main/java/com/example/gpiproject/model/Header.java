package com.example.gpiproject.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "header")
public class Header {
    @PrimaryKey
    @NonNull
    private String headerID;
    private String chequeNumber;
    private String flag;
    private String organizationCode;
    private String purchaseType;
    private String buyerCode;
    private String purchaseDocumentNumber;
    private String invoiceDate;
    private String purchaseDate;
    private String crop;
    private String variety;
    private String createdBy;
    private String attribute;
    private String status;
    private String creationDate;

    public Header(String headerID, String chequeNumber, String flag, String organizationCode, String purchaseType, String buyerCode, String purchaseDocumentNumber, String invoiceDate, String purchaseDate, String crop, String variety, String createdBy, String attribute, String status, String creationDate) {
        this.headerID = headerID;
        this.chequeNumber = chequeNumber;
        this.flag = flag;
        this.organizationCode = organizationCode;
        this.purchaseType = purchaseType;
        this.buyerCode = buyerCode;
        this.purchaseDocumentNumber = purchaseDocumentNumber;
        this.invoiceDate = invoiceDate;
        this.purchaseDate = purchaseDate;
        this.crop = crop;
        this.variety = variety;
        this.createdBy = createdBy;
        this.attribute = attribute;
        this.status = status;
        this.creationDate = creationDate;
    }

    public String getHeaderID() {
        return headerID;
    }

    public void setHeaderID(String headerID) {
        this.headerID = headerID;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
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

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
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

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Header{" +
                "headerID='" + headerID + '\'' +
                ", chequeNumber='" + chequeNumber + '\'' +
                ", flag='" + flag + '\'' +
                ", organizationCode='" + organizationCode + '\'' +
                ", purchaseType='" + purchaseType + '\'' +
                ", buyerCode='" + buyerCode + '\'' +
                ", purchaseDocumentNumber='" + purchaseDocumentNumber + '\'' +
                ", invoiceDate='" + invoiceDate + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", crop='" + crop + '\'' +
                ", variety='" + variety + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", attribute='" + attribute + '\'' +
                ", status='" + status + '\'' +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }
}
