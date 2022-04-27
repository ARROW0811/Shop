package com.example.shop.dao

import androidx.room.*
import com.example.shop.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(vararg users: User?)

    @Query("SELECT * FROM User WHERE phoneNumber=:phoneNumber LIMIT 1")
    suspend fun getUser(phoneNumber: String): User?

    @Query("SELECT name FROM User WHERE phoneNumber=:phoneNumber LIMIT 1")
    suspend fun getName(phoneNumber: String): String?

    @Query("SELECT password FROM User WHERE phoneNumber=:phoneNumber LIMIT 1")
    suspend fun getPassword(phoneNumber: String): String?
}