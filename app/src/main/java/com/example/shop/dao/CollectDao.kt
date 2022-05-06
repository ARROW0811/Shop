package com.example.shop.dao

import androidx.room.*
import com.example.shop.entity.Collect

@Dao
interface CollectDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCollect(vararg collect:Collect?)

    @Query("DELETE FROM Collect WHERE phoneNumber=:phoneNumber and gid=:gid")
    suspend fun deleteCollect(phoneNumber: String,gid:Int)

    @Query("SELECT gid FROM Collect WHERE phoneNumber=:phoneNumber")
    suspend fun getMyCollect(phoneNumber:String):List<Int>

    @Query("SELECT id FROM Collect WHERE phoneNumber=:phoneNumber and gid=:gid")
    suspend fun getId(phoneNumber: String,gid:Int):Int?
}