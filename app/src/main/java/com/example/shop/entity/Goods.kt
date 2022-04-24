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
    @ColumnInfo(name="goods_name")
    var name: String=""
    var sort: String? = null
    var imageId: Int = 0
    lateinit var price: String
    var primePrice: String? = null
    var describe: String? = null
    var location: String? = null
    var deliver: String? = null
    var uid:String?=null
    var state:String?=null


    constructor(title: String, imageId: Int, price: String):this(){
        this.title = title
        this.imageId = imageId
        this.price = price
    }

    constructor(gid: Int, title: String, name: String, imageId: Int, price: String, describe: String?, location: String?, sort: String?, primePrice: String?, deliver: String?,uid:String?,state:String?) : this() {
        this.gid = gid
        this.title = title
        this.name = name
        this.imageId = imageId
        this.price = price
        this.describe = describe
        this.location = location
        this.sort = sort
        this.primePrice = primePrice
        this.deliver = deliver
        this.uid=uid
        this.state=state
    }




}