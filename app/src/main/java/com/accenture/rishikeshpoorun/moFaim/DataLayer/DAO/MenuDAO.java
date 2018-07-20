package com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Menu;

import java.util.List;

@Dao
public interface MenuDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMenu(Menu menu);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMenu(Menu menu);

    @Delete
    void deleteMenu(Menu menu);

    @Query("SELECT * FROM MENU_TABLE WHERE RESTAURANT_ID =:restaurantId")
    List<Menu> getAllMenuByRestaurantId(Long restaurantId);

    @Query("SELECT * FROM MENU_TABLE")
    List<Menu> getAllMenu();
}
