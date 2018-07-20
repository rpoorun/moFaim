package com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Rating;

import java.util.List;

@Dao
public interface RatingDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRating(Rating rating);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateRating(Rating rating);

    @Delete
    void deleteRating(Rating rating);

    @Query("SELECT * FROM RATING_TABLE WHERE USER_ID =:userId AND RESTAURANT_ID =:restaurantId")
    Rating getRating(Long userId, Long restaurantId);

    @Query("SELECT * FROM RATING_TABLE WHERE RESTAURANT_ID =:restaurantId")
    List<Rating> getAllRatingByRestaurantId(Long restaurantId);

    @Query("SELECT * FROM RATING_TABLE")
    List<Rating> getAllRatings();
}
