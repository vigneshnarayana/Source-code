package com.example.itc.report;

public class reportmodel {
    String location,asset;

    public reportmodel() {
    }

    public reportmodel(String location, String asset) {
        this.location = location;
        this.asset = asset;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }
}
