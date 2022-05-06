package com.example.shop.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Order{
    @PrimaryKey(autoGenerate = true)
    var id=0
    @ColumnInfo(name="gid", typeAffinity = ColumnInfo.INTEGER)
    var gid=0
    @ColumnInfo(name="bid", typeAffinity = ColumnInfo.INTEGER)
    var bid=0
    @ColumnInfo(name = "time", typeAffinity = ColumnInfo.TEXT)
    var time=""

    constructor(gid: Int, bid: Int, time: String) {
        this.gid = gid
        this.bid = bid
        this.time = time
    }
}