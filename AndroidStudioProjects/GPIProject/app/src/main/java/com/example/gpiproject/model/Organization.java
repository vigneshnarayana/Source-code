package com.example.gpiproject.model;

import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "organization")
public class Organization {
    @PrimaryKey
    @NonNull
    private  String organizationCode;
    private  String organizationName;
    private  String organizationType;
    private  String organizationAddress1;

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public String getOrganizationAddress1() {
        return organizationAddress1;
    }

    public void setOrganizationAddress1(String organizationAddress1) {
        this.organizationAddress1 = organizationAddress1;
    }



    @Override
    public String toString() {
        return "Organization{" +
                "organizationCode='" + organizationCode + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", organizationType='" + organizationType + '\'' +
                ", organizationAddress1='" + organizationAddress1 + '\'' +
                '}';
    }
}
