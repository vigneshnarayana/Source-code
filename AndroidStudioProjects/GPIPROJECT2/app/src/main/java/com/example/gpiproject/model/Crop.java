package com.example.gpiproject.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName ="crop")
public class Crop {
    @PrimaryKey
    @NonNull
    private String crop;
    private String cropYear;
    private String status;

    public Crop(String crop, String cropYear, String status) {
        this.crop = crop;
        this.cropYear = cropYear;
        this.status = status;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public String getCropYear() {
        return cropYear;
    }

    public void setCropYear(String cropYear) {
        this.cropYear = cropYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Crop{" +
                "crop='" + crop + '\'' +
                ", cropYear='" + cropYear + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
