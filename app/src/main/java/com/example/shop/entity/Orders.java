package com.example.shop.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Orders")
public class Orders {
    @PrimaryKey(autoGenerate = true)
    private int oid;
    @ColumnInfo(name = "gid",typeAffinity = ColumnInfo.INTEGER)
    private int gid;
    @ColumnInfo(name = "bid",typeAffinity = ColumnInfo.INTEGER)
    private int bid;
    @ColumnInfo(name = "time",typeAffinity = ColumnInfo.TEXT)
    private String time;

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Orders(int gid, int bid, String time) {
        this.gid = gid;
        this.bid = bid;
        this.time = time;
    }
}
