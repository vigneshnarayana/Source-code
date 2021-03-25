package com.example.tvs.activity.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "LoginMasters1")
public class Login {
    private String password;
    @PrimaryKey
    @NonNull
    private String locationCode;
    private String status;

    public Login(String password, @NonNull String locationCode, String status) {
        this.password = password;
        this.locationCode = locationCode;
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NonNull
    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(@NonNull String locationCode) {
        this.locationCode = locationCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Login{" +
                "password='" + password + '\'' +
                ", locationCode='" + locationCode + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
