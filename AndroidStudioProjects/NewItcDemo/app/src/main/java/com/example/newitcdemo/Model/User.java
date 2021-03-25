package com.example.newitcdemo.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "user")
public class User {
    @PrimaryKey
    @NonNull
    @SerializedName("empCode")
    private String empCode;
    @SerializedName("empName")
    private String empName;
    @SerializedName("password")
    private String password;
    @SerializedName("department")
    private String department;
    @SerializedName("designation")
    private String designation;
    @SerializedName("phonenumber")
    private String phonenumber;
    @SerializedName("email")
    private String email;
    @SerializedName("status")
    private String status;

    @NonNull
    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(@NonNull String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "empCode='" + empCode + '\'' +
                ", empName='" + empName + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", designation='" + designation + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
