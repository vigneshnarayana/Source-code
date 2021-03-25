package com.example.gpiproject.model;

public class LotcreationPost {
    private  String headerID;
    private  String attribute3;
    private  String tbLotNumber;
    private  String farmerCode;
    private  String baleNumber;
    private  String series;
    private  String crop;
    private  String variety;
    private  String createdBy;
    private  String createdDate;

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

    public String getTbLotNumber() {
        return tbLotNumber;
    }

    public void setTbLotNumber(String tbLotNumber) {
        this.tbLotNumber = tbLotNumber;
    }

    public String getFarmerCode() {
        return farmerCode;
    }

    public void setFarmerCode(String farmerCode) {
        this.farmerCode = farmerCode;
    }

    public String getBaleNumber() {
        return baleNumber;
    }

    public void setBaleNumber(String baleNumber) {
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
        return "LotcreationPost{" +
                "headerID='" + headerID + '\'' +
                ", attribute3='" + attribute3 + '\'' +
                ", tbLotNumber='" + tbLotNumber + '\'' +
                ", farmerCode='" + farmerCode + '\'' +
                ", baleNumber='" + baleNumber + '\'' +
                ", series='" + series + '\'' +
                ", crop='" + crop + '\'' +
                ", variety='" + variety + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
