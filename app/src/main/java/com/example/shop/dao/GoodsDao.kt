package com.example.shop.dao

import androidx.room.*
import com.example.shop.entity.Goods

@Dao
interface GoodsDao {
    @Insert
    suspend fun insertGoods(vararg goods: Goods?)

    @Update
    suspend fun updateGoods(vararg goods: Goods?)

    @Delete
    suspend fun deleteGoods(vararg goods: Goods?)

    @Query("DELETE FROM GOODS")
    suspend fun deleteAllGoods()

    @get:Query("SELECT * FROM GOODS ORDER BY gid DESC")
    val allGoods: List<Goods?>?
}