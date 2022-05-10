package com.example.shop

import android.app.Application
import androidx.room.Room
import com.example.shop.dao.CollectDao
import com.example.shop.dao.GoodsDao
import com.example.shop.dao.OrdersDao
import com.example.shop.dao.UserDao
import com.example.shop.database.*
import kotlin.properties.Delegates

class MyApplication : Application() {
    lateinit var appDatabase:AppDatabase
    lateinit var userDao:UserDao
    lateinit var goodsDao: GoodsDao
    lateinit var collectDao:CollectDao
    lateinit var ordersDao:OrdersDao
    companion object {
        var instance:MyApplication by Delegates.notNull()
        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appDatabase=Room.databaseBuilder(instance,AppDatabase::class.java,"app_database").build()

        userDao=appDatabase.userDao
        goodsDao=appDatabase.goodsDao
        collectDao=appDatabase.collectDao
        ordersDao=appDatabase.orderDao
    }

}
