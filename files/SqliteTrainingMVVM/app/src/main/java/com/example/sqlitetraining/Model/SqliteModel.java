package com.example.sqlitetraining.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sqliteModel")
public class SqliteModel {


    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;
    private String surname;
    private String marks;


    public SqliteModel(Integer id, String name, String surname, String marks) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.marks = marks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

}
