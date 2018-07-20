package com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.util.List;


@Entity(tableName = "USER_TABLE")
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "USER_ID")
    private Long userId;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @ColumnInfo(name = "USER_NAME")
    private String userName;

    @ColumnInfo(name = "EMAIL")
    private String email;

    @ColumnInfo(name = "PASSWORD")
    private String password;

    public User() {
    }
    @Ignore
    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }


    public Long getUserId() {
        return userId;
    }

   /* public void setUserId(Integer userId) {
        this.userId = userId;
    }*/

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
