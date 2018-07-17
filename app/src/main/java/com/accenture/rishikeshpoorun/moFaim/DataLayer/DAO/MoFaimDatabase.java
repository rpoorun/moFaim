package com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.User;

@Database(entities = User.class, version = 1)
public abstract class MoFaimDatabase extends RoomDatabase{

        public abstract UserDAO userDAO();

    }

