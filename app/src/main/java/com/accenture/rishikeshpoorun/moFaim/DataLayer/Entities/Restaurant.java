package com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.util.Arrays;
import java.util.List;

@Entity(tableName = "RESTAURANT_TABLE")
public class Restaurant {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "RESTAURANT_ID")
    private Long restaurantId;

    @ColumnInfo(name = "RESTAURANT_NAME")
    private String restaurantName;

    @ColumnInfo(name = "ADDRESS")
    private String address;

    @ColumnInfo(name = "STYLE")
    private String style;

    @ColumnInfo(name = "PHONE_NUMBER")
    private Long phoneNumber;

    @ColumnInfo(name = "RATING")
    private Float overallRating;

    @ColumnInfo(name = "LATITUDE")
    private Float latitude;

    @ColumnInfo(name = "LONGITUDE")
    private Float longitude;

    @ColumnInfo(name = "PHOTO")
    private String photoName;

    public Restaurant() {
    }

    @Ignore
    public Restaurant(String restaurantName, String address, String style ,Long phoneNumber, Float latitude, Float longitude, String photoName) {
        this.restaurantName = restaurantName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.photoName = photoName;
        this.style = style;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Float getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(Float overallRating) {
        this.overallRating = overallRating;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }


    @Override
    public String toString() {
        return "RestaurantActivity{" +
                "restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", overallRating=" + overallRating +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", photoName=" + photoName +
                '}';
    }
}
