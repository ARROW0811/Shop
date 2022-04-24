package com.example.shop.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shop.entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User...users);
    @Update
    void updateUser(User...users);
    @Query("SELECT * FROM USER WHERE NAME =:name")
    User getUser(int name);
    @Query("SELECT * FROM USER")
    List<User> getAllUser();
    @Query("SELECT password FROM USER WHERE PHONENUMBER=:phoneNumber")
    String getPassword(String phoneNumber);
    @Query("SELECT * FROM USER WHERE PHONENUMBER=:phoneNumber")
    User getUser(String phoneNumber);
}
