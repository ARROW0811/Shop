package com.example.shop.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.shop.dao.CollectDao;
import com.example.shop.entity.Collect;
import com.example.shop.entity.Goods;
import com.example.shop.entity.User;

@Database(entities = {Collect.class,Goods.class, User.class},version=1,exportSchema = false)
public abstract class CollectDatabase extends RoomDatabase {

    public abstract CollectDao getCollectDao();
}
