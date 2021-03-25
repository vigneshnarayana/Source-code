package com.zebra.rfid.demo.sdksample.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Data")
public class Data {
    @PrimaryKey
    @NonNull
    private String TagId;
    private String Location;

    public Data() {
    }

    public String getTagId() {
        return TagId;
    }

    public void setTagId(String tagId) {
        TagId = tagId;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Data(@NonNull String tagId, String location) {
        TagId = tagId;
        Location = location;
    }

    @Override
    public String toString() {
        return "Data{" +
                "TagId='" + TagId + '\'' +
                ", Location='" + Location + '\'' +
                '}';
    }
}
