package com.example.shop

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shop.dao.CollectDao
import com.example.shop.dao.GoodsDao
import com.example.shop.dao.OrderDao
import com.example.shop.dao.UserDao
import com.example.shop.database.CollectDatabase
import com.example.shop.database.GoodsDatabase
import com.example.shop.database.OrderDatabase
import com.example.shop.database.UserDatabase
import com.example.shop.entity.Goods
import com.example.shop.entity.User
import com.example.shop.util.RoomUtil
import kotlin.properties.Delegates

class MyApplication : Application() {
    lateinit var userDatabase: UserDatabase
    lateinit var goodsDatabase: GoodsDatabase
    lateinit var collectDatabase: CollectDatabase
    lateinit var orderDatabase: OrderDatabase
    lateinit var userDao:UserDao
    lateinit var goodsDao: GoodsDao
    lateinit var collectDao:CollectDao
    lateinit var orderDao:OrderDao
    companion object {
        var instance:MyApplication by Delegates.notNull()
        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        userDatabase=Room.databaseBuilder(instance, UserDatabase::class.java, "user_database").build()

        goodsDatabase=Room.databaseBuilder(instance,GoodsDatabase::class.java,"goods_database").build()

        collectDatabase=Room.databaseBuilder(instance,CollectDatabase::class.java,"collect_database").build()

        orderDatabase=Room.databaseBuilder(instance,OrderDatabase::class.java,"order_database").build()
        userDao=userDatabase.userDao
        goodsDao=goodsDatabase.goodsDao
        collectDao=collectDatabase.collectDao
        orderDao=orderDatabase.orderDao
    }

}
