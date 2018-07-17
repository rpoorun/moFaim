package com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Arrays;

@Entity(tableName = "RESTAURANT_TABLE")
public class Restaurant {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "RESTAURANT_ID")
    private Long restaurantId;

    @ColumnInfo(name = "RESTAURANT_NAME")
    private String restaurantName;

    @ColumnInfo(name = "ADDRESS")
    private String address;

    @ColumnInfo(name = "PHONE_NUMBER")
    private Long phoneNumber;

    @ColumnInfo(name = "RATING")
    private Double overallRating;

    @ColumnInfo(name = "LATITUDE")
    private Float latitude;

    @ColumnInfo(name = "LONGITUDE")
    private Float longitude;

    @ColumnInfo(name = "PHOTO")
    private byte[] photo;

    public Restaurant() {
    }

    @Ignore
    public Restaurant(String restaurantName, String address, Long phoneNumber, Double overallRating, Float latitude, Float longitude, byte[] photo) {
        this.restaurantName = restaurantName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.overallRating = overallRating;
        this.latitude = latitude;
        this.longitude = longitude;
        this.photo = photo;
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

    public Double getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(Double overallRating) {
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", restaurantName='" + restaurantName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", overallRating=" + overallRating +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", photo=" + Arrays.toString(photo) +
                '}';
    }
}
