package com.example.shop.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.shop.dao.OrderDao;
import com.example.shop.entity.Goods;
import com.example.shop.entity.Order;
import com.example.shop.entity.User;

@Database(entities = {Order.class, Goods.class, User.class},version = 1,exportSchema = false)
public abstract class OrderDatabase extends RoomDatabase {
    public abstract OrderDao getOrderDao();
}
