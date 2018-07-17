package com.accenture.rishikeshpoorun.moFaim.DataLayer.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO.RestaurantDAO;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO.UserDAO;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Restaurant;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.User;

@Database(entities = {User.class, Restaurant.class}, version = 1)
public abstract class MoFaimDatabase extends RoomDatabase{

        public abstract UserDAO userDAO();

        public abstract RestaurantDAO restaurantDAO();

    }

