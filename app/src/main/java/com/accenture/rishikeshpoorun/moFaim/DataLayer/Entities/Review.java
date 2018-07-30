package com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities;

import android.arch.persistence.room.Entity;

import java.util.Date;

public class Review {

    private Long dateAndTime;
    private String username;
    private String comment;

    public Review(String username, String comment) {
        this.username = username;
        this.comment = comment;
        dateAndTime = new Date().getTime();
    }

    public Review() {
    }

    public Long getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Long dateAndTime) {
        this.dateAndTime = dateAndTime;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public String toString() {
        return "Review{" +
                "dateAndTime=" + dateAndTime +
                ", username='" + username + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
