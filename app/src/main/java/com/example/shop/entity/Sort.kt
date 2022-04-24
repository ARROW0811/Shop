package com.example.shop.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Sort constructor(){
    @PrimaryKey(autoGenerate = true)
    var id = 0
    @ColumnInfo(name="sort_name")
    var name: String? = null
    @ColumnInfo(name="imageId")
    var imageId = 0

    constructor(id:Int,name:String,imageId:Int):this(){
        this.id=id
        this.name=name
        this.imageId=imageId
    }
    constructor(name:String,imageId:Int):this(){
        this.name=name
        this.imageId=imageId
    }
}