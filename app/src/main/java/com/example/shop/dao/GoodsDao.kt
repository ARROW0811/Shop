package com.example.shop.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shop.entity.Goods;

import java.util.List;

@Dao
public interface GoodsDao {
    @Insert
    void insertGoods(Goods...goods);
    @Update
    void updateGoods(Goods...goods);
    @Delete
    void deleteGoods(Goods...goods);
    @Query("DELETE FROM GOODS")
    void deleteAllGoods();
    /*
    @Query("SELECT * FROM GOODS ORDER BY DESC")
    List<Goods> getAllGoods();

     */
}
