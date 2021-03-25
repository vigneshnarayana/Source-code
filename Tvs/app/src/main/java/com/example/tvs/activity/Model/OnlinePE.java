package com.example.tvs.activity.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "OnlinePE")
public class OnlinePE {
    private String empCode;
    private String locationCode;
    private String inchargeCode;
    private String managerCode;
    @PrimaryKey
    @NonNull
    private String createdDate;
    private String onlinePEMessage;

    public OnlinePE(String empCode, String locationCode, String inchargeCode, String managerCode, @NonNull String createdDate, String onlinePEMessage) {
        this.empCode = empCode;
        this.locationCode = locationCode;
        this.inchargeCode = inchargeCode;
        this.managerCode = managerCode;
        this.createdDate = createdDate;
        this.onlinePEMessage = onlinePEMessage;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getInchargeCode() {
        return inchargeCode;
    }

    public void setInchargeCode(String inchargeCode) {
        this.inchargeCode = inchargeCode;
    }

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode;
    }

    @NonNull
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(@NonNull String createdDate) {
        this.createdDate = createdDate;
    }

    public String getOnlinePEMessage() {
        return onlinePEMessage;
    }

    public void setOnlinePEMessage(String onlinePEMessage) {
        this.onlinePEMessage = onlinePEMessage;
    }

    @Override
    public String toString() {
        return "OnlinePE{" +
                "empCode='" + empCode + '\'' +
                ", locationCode='" + locationCode + '\'' +
                ", inchargeCode='" + inchargeCode + '\'' +
                ", managerCode='" + managerCode + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", onlinePEMessage='" + onlinePEMessage + '\'' +
                '}';
    }
}
