package com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

@Entity(tableName = "RATING_TABLE")
public class Rating {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "RATING_ID")
    private Long ratingId;

    @ColumnInfo(name = "USER_ID")
    private Long userId;

    @ColumnInfo(name = "RESTAURANT_ID")
    private Long restaurantId;

    @ColumnInfo(name = "RATINGS")
    private Float rating;

    public Rating() {
    }

    @Ignore
    public Rating(Long userId, Long restaurantId, Float rating) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.rating = rating;
    }

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "ratingId=" + ratingId +
                ", userId=" + userId +
                ", restaurantId=" + restaurantId +
                ", rating=" + rating +
                '}';
    }
}
