package com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities;

import android.arch.persistence.room.Entity;

@Entity
public class Review {

    private Long reviewID;
    private Long restaurantId;
    private String userNAme;
    private String comment;


}
