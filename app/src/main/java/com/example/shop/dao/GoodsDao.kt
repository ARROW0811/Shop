package com.example.shop.dao

import androidx.room.*
import com.example.shop.entity.Goods

@Dao
interface GoodsDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoods(vararg goods: Goods?)

    @Update
    suspend fun updateGoods(vararg goods: Goods?)

    @Delete
    suspend fun deleteGoods(vararg goods: Goods?)

    @Query("DELETE FROM GOODS")
    suspend fun deleteAllGoods()

    @Query("SELECT * FROM GOODS WHERE state = 1")
    suspend fun getAllUpGoods(): List<Goods?>?

    @Query("SELECT * FROM GOODS WHERE gid=:gid")
    suspend fun getGoods(gid:Int):Goods

    @Query("SELECT * FROM GOODS WHERE sort=:sort")
    suspend fun getGoodsFromSort(sort:String):List<Goods?>?

    @Query("SELECT * FROM GOODS WHERE phoneNumber=:phoneNumber")
    suspend fun getGoodsFromPhone(phoneNumber:String):List<Goods>?

    @Query("SELECT * FROM GOODS WHERE title like '%' || :searchText || '%' and state=1")
    suspend fun getGoodsFromText(searchText:String):List<Goods>?

    @Query("UPDATE GOODS SET state=:state WHERE gid=:gid")
    suspend fun updateState(state:Int,gid:Int)

    @Query("SELECT * FROM GOODS WHERE state=2 and phoneNumber=:phoneNumber")
    suspend fun getMySoldGoods(phoneNumber: String):List<Goods>?
}