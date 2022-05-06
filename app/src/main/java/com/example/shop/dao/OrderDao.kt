package com.example.shop.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shop.entity.Goods
import com.example.shop.entity.Order

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrder(vararg order: Order?)

    //@Query("SELECT gid FROM Order WHERE bid=:bid")
    //suspend fun getGoodsFromBid(bid:Int):List<Int>

}