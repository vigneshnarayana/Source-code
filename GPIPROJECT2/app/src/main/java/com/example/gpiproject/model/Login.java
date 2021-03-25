package com.example.gpiproject.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "login")
public class Login {

    @PrimaryKey
    @NonNull
    private String userID; ;
    private String userName;
    private String password;
    private String userERPName;
    private String employeeCode;
    private String designation;
    private String department;
    private String userRights;
    private String syncID;
    private String syncPassword;
    private String mobileNumber;
    private String emailID;
    private String status;

    public Login() {
    }

    public Login(String userID, String userName, String password, String userERPName, String employeeCode, String designation, String department, String userRights, String syncID, String syncPassword, String mobileNumber, String emailID, String status) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.userERPName = userERPName;
        this.employeeCode = employeeCode;
        this.designation = designation;
        this.department = department;
        this.userRights = userRights;
        this.syncID = syncID;
        this.syncPassword = syncPassword;
        this.mobileNumber = mobileNumber;
        this.emailID = emailID;
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserERPName() {
        return userERPName;
    }

    public void setUserERPName(String userERPName) {
        this.userERPName = userERPName;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUserRights() {
        return userRights;
    }

    public void setUserRights(String userRights) {
        this.userRights = userRights;
    }

    public String getSyncID() {
        return syncID;
    }

    public void setSyncID(String syncID) {
        this.syncID = syncID;
    }

    public String getSyncPassword() {
        return syncPassword;
    }

    public void setSyncPassword(String syncPassword) {
        this.syncPassword = syncPassword;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoginServiceModel{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userERPName='" + userERPName + '\'' +
                ", employeeCode='" + employeeCode + '\'' +
                ", designation='" + designation + '\'' +
                ", department='" + department + '\'' +
                ", userRights='" + userRights + '\'' +
                ", syncID='" + syncID + '\'' +
                ", syncPassword='" + syncPassword + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", emailID='" + emailID + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
