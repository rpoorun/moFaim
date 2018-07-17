package com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.User;


@Dao
public interface UserDAO {

    @Query("SELECT * FROM USER_TABLE WHERE USER_ID =:userId")
    User getUserByID(Long userId);

    @Query("SELECT * FROM USER_TABLE WHERE EMAIL =:email AND PASSWORD =:password")
    User selectUserAndPassword(String email, String password);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertUser(User user);

    @Update (onConflict = OnConflictStrategy.REPLACE)
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM USER_TABLE WHERE EMAIL =:email")
    User getUserByEmail(String email);
}
