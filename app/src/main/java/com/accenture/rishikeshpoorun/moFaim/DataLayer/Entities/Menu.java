package com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "MENU_TABLE")
public class Menu {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "MENU_ID")
    private Long menuId;

    @ColumnInfo(name = "MENU_NAME")
    private String menuName;

    @ColumnInfo(name = "MENU_PRICE")
    private Float menuPrice;

    @ColumnInfo(name = "RESTAURANT_ID")
    private Long restaurantId;

    @ColumnInfo(name = "PHOTO_NAME")
    private String photoName;



    public Menu() {
    }

    @Ignore
    public Menu(String menuName, Float menuPrice, Long restaurantId, String photoName) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.restaurantId = restaurantId;
        this.photoName = photoName;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Float getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(Float menuPrice) {
        this.menuPrice = menuPrice;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
}
