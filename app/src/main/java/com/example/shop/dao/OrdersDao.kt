package com.example.shop.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shop.entity.Goods
import com.example.shop.entity.Orders

@Dao
interface OrdersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOrder(vararg orders: Orders?)


    @Query("SELECT * FROM Orders where bid=:bid")
    suspend fun getOrdersFromBid(bid:Int):List<Orders>?
}