package com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Restaurant;

import java.util.List;

@Dao
public interface RestaurantDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRestaurant(Restaurant restaurant);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateRestaurant(Restaurant restaurant);

    @Delete
    void deleteRestaurant(Restaurant restaurant);

    @Query("SELECT * FROM RESTAURANT_TABLE")
    List<Restaurant> getAllRestaurant();

    @Query("SELECT * FROM RESTAURANT_TABLE WHERE RESTAURANT_ID =:restaurantId")
    Restaurant selectRestaurantById(Long restaurantId);

}
