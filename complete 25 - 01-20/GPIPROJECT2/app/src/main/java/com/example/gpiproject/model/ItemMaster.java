package com.example.gpiproject.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ItemMaster")
public class ItemMaster {

    @PrimaryKey
    @NonNull
    private  String itemCode;
    private  String itemCodeGrp;
    private  String itemGrp;
    private  String itemType;
    private  String itemDescription;
    private  String crop;
    private  String variety;
    private  String costCategory;
    private  String orgnType;
    private  String status;
    private  String flag;
    private  String attribute2;
    private  String attribute3;

    public ItemMaster(String itemCode, String itemCodeGrp, String itemGrp, String itemType) {
        this.itemCode = itemCode;
        this.itemCodeGrp = itemCodeGrp;
        this.itemGrp = itemGrp;
        this.itemType = itemType;

    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemCodeGrp() {
        return itemCodeGrp;
    }

    public void setItemCodeGrp(String itemCodeGrp) {
        this.itemCodeGrp = itemCodeGrp;
    }

    public String getItemGrp() {
        return itemGrp;
    }

    public void setItemGrp(String itemGrp) {
        this.itemGrp = itemGrp;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
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

    public String getCostCategory() {
        return costCategory;
    }

    public void setCostCategory(String costCategory) {
        this.costCategory = costCategory;
    }

    public String getOrgnType() {
        return orgnType;
    }

    public void setOrgnType(String orgnType) {
        this.orgnType = orgnType;
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

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    @Override
    public String toString() {
        return "ItemMaster{" +
                "itemCode='" + itemCode + '\'' +
                ", itemCodeGrp='" + itemCodeGrp + '\'' +
                ", itemGrp='" + itemGrp + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", crop='" + crop + '\'' +
                ", variety='" + variety + '\'' +
                ", costCategory='" + costCategory + '\'' +
                ", orgnType='" + orgnType + '\'' +
                ", status='" + status + '\'' +
                ", flag='" + flag + '\'' +
                ", attribute2='" + attribute2 + '\'' +
                ", attribute3='" + attribute3 + '\'' +
                '}';
    }
}
