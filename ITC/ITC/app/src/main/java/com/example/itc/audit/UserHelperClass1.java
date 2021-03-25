package com.example.itc.audit;

public class UserHelperClass1
{

    String asset;
    String time;

    public UserHelperClass1()
    {

    }

    public UserHelperClass1(String asset, String time) {
        this.asset = asset;
        this.time = time;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
