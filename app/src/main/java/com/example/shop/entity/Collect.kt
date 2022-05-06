package com.example.shop.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Collect{
    @PrimaryKey(autoGenerate = true)
    var id=0
    @ColumnInfo(name="gid", typeAffinity = ColumnInfo.INTEGER)
    var gid=0
    @ColumnInfo(name = "phoneNumber", typeAffinity = ColumnInfo.TEXT)
    var phoneNumber=""

    constructor(gid: Int, phoneNumber: String) {
        this.gid = gid
        this.phoneNumber = phoneNumber
    }
}