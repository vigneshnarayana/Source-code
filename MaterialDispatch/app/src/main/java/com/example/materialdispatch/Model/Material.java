package com.example.materialdispatch.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Material")
public class Material {


    private String CustomerName;
    private String TrayNumber;
    @PrimaryKey
    @NonNull
    private String Material;
    private String Quantity;
    private String DateAndTime;


    public Material() {
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getTrayNumber() {
        return TrayNumber;
    }

    public void setTrayNumber(String trayNumber) {
        TrayNumber = trayNumber;
    }

    public String getMaterial() {
        return Material;
    }

    public void setMaterial(String material) {
        Material = material;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getDateAndTime() {
        return DateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        DateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "Material{" +
                "CustomerName='" + CustomerName + '\'' +
                ", TrayNumber='" + TrayNumber + '\'' +
                ", Material='" + Material + '\'' +
                ", Quantity='" + Quantity + '\'' +
                ", DateAndTime='" + DateAndTime + '\'' +
                '}';
    }
}
