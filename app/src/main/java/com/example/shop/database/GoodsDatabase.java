package com.example.shop.database;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.shop.dao.GoodsDao;
import com.example.shop.entity.Goods;

@Database(entities = {Goods.class},version=1,exportSchema = false)
public abstract class GoodsDatabase extends RoomDatabase{
    public abstract GoodsDao getGoodsDao();
}
