package com.example.shop.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Goods constructor(){
    @PrimaryKey(autoGenerate = true)
    var gid = 0

    @ColumnInfo(name="title")
    var title: String=""
    @ColumnInfo(name="sort")
    var sort: String=""
    @ColumnInfo(name="image")
    var image: Int =0
    @ColumnInfo(name="price")
    lateinit var price: String
    @ColumnInfo(name="primePrice")
    var primePrice: String=""
    @ColumnInfo(name="describe")
    var describe: String=""
    @ColumnInfo(name="location")
    var location: String=""
    @ColumnInfo(name="deliver")
    var deliver: String=""
    @ColumnInfo(name="phoneNumber")
    var phoneNumber:String=""
    @ColumnInfo(name="state")
    var state:Int=0
    constructor(gid:Int,title: String, image: Int, price: String):this(){
        this.gid=gid
        this.title = title
        this.image = image
        this.price = price
    }

    constructor(title: String, image: Int, price: String, describe: String, location: String, sort: String, primePrice: String, deliver: String,phoneNumber:String,state:Int) : this() {
        this.title = title
        this.image = image
        this.price = price
        this.describe = describe
        this.location = location
        this.sort = sort
        this.primePrice = primePrice
        this.deliver = deliver
        this.phoneNumber=phoneNumber
        this.state=state
    }




}