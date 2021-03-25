package com.example.gpiproject.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "TestTable")
public class TestTable {
    @PrimaryKey
    @NonNull
    @SerializedName("ClOne")
    private  String ClOne;
    @SerializedName("ClTwo")
    private  String ClTwo;
    @SerializedName("ClThree")
    private  String ClThree;



    @Override
    public String toString() {
        return "TestTable{" +
                "ClOne='" + ClOne + '\'' +
                ", ClTwo='" + ClTwo + '\'' +
                ", ClThree='" + ClThree + '\'' +
                '}';
    }

    public String getClOne() {
        return ClOne;
    }

    public void setClOne(String clOne) {
        ClOne = clOne;
    }

    public String getClTwo() {
        return ClTwo;
    }

    public void setClTwo(String clTwo) {
        ClTwo = clTwo;
    }

    public String getClThree() {
        return ClThree;
    }

    public void setClThree(String clThree) {
        ClThree = clThree;
    }


}
