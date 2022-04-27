package com.example.shop.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "name",typeAffinity = ColumnInfo.TEXT)
    private String name;
    @ColumnInfo(name = "school",typeAffinity = ColumnInfo.TEXT)
    private String school;
    @ColumnInfo(name = "studentNumber",typeAffinity = ColumnInfo.TEXT)
    private String studentNumber;
    @ColumnInfo(name = "phoneNumber",typeAffinity = ColumnInfo.TEXT)
    private String phoneNumber;
    @ColumnInfo(name = "password",typeAffinity = ColumnInfo.TEXT)
    private String password;
    @ColumnInfo(name = "iconId",typeAffinity = ColumnInfo.INTEGER)
    private int iconId;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
    public User(String name, String school, String studentNumber, String phoneNumber, String password) {
        this.name = name;
        this.school = school;
        this.studentNumber = studentNumber;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
}
