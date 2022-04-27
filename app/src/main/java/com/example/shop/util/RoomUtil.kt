package com.example.shop.util

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shop.MyApplication
import com.example.shop.dao.UserDao
import com.example.shop.database.UserDatabase
import com.example.shop.entity.User

object RoomUtil {
    lateinit var userDao:UserDao
    fun initUserDatabase(name:String): UserDatabase {
        return Room.databaseBuilder(MyApplication.instance, UserDatabase::class.java, name).build()
    }

}