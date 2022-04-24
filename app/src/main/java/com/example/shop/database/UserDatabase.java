package com.example.shop.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.shop.dao.UserDao;
import com.example.shop.entity.User;

@Database(entities = {User.class},version=1,exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
}
