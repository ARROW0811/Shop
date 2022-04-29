package com.example.shop.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.shop.dao.GoodsDao;
import com.example.shop.entity.Goods;

@Database(entities = {Goods.class},version=1,exportSchema = false)
public abstract class GoodsDatabase extends RoomDatabase{
    public abstract GoodsDao getGoodsDao();
}
