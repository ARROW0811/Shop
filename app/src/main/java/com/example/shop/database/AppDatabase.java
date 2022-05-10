package com.example.shop.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.shop.dao.CollectDao;
import com.example.shop.dao.GoodsDao;
import com.example.shop.dao.OrdersDao;
import com.example.shop.dao.UserDao;
import com.example.shop.entity.Collect;
import com.example.shop.entity.Goods;
import com.example.shop.entity.Orders;
import com.example.shop.entity.User;

@Database(entities = {Collect.class, Goods.class, User.class, Orders.class},version=1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CollectDao getCollectDao();
    public abstract GoodsDao getGoodsDao();
    public abstract UserDao getUserDao();
    public abstract OrdersDao getOrderDao();
}
