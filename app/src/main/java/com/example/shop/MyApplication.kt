package com.example.shop

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shop.dao.UserDao
import com.example.shop.database.UserDatabase
import com.example.shop.entity.User
import com.example.shop.util.RoomUtil
import kotlin.properties.Delegates

class MyApplication : Application() {
    lateinit var userDatabase: UserDatabase
    lateinit var userDao:UserDao
    companion object {
        var instance:MyApplication by Delegates.notNull()
        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        userDatabase=Room.databaseBuilder(MyApplication.instance, UserDatabase::class.java, "user_database").build()
        //RoomUtil.initUserDatabase("user_database")
        userDao=userDatabase.userDao
    }

}
