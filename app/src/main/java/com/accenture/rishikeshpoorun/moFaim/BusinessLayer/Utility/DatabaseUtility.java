package com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility;


import android.arch.persistence.room.Room;
import android.content.Context;

import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity.MainActivity;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service.MenuService;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service.RatingService;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service.RestaurantService;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service.UserService;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Database.MoFaimDatabase;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Menu;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Rating;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Restaurant;

import java.net.ConnectException;

public class DatabaseUtility {

    private static UserService userService;
    private static RestaurantService restaurantService;
    private static RatingService ratingService;
    private static MenuService menuService;
    private static MoFaimDatabase database;

    private DatabaseUtility(){
        //do nothing
        //util class
    }

    /**
     * Initialize and create the database connectivity
     */
    public static MoFaimDatabase buildDatabase(Context context) {
         database = Room.databaseBuilder(context, MoFaimDatabase.class, "moFaim.db")
                .allowMainThreadQueries()
                .build();

         return database;
    }


    public static MoFaimDatabase getDatabase(){
        return database;
    }

    /**
     * Populate the tables with hardcoded values
     */
    public static void populateUserTable(){
        userService = new UserService();

        try{
            if(userService.getAllUsers().isEmpty()) {
                userService.addUser("admin", "admin@admin.com", "admin", "admin");
                userService.addUser("foo", "foo@foo.com", "foo", "foo");
                userService.addUser("moo", "moo@moo.com", "moo", "moo");
            }
        }catch (Exception e){
            //do nothing
        }

    }

    /**
     * Populate the tables with hardcoded values
     */
    public static void populateRestaurantTable() {
        restaurantService = new RestaurantService();

        try{
            if(restaurantService.getAllRestaurant().isEmpty()) {

                restaurantService.addRestaurant(new Restaurant("Snack Payo", "Stadium Road, Reduit, Moka", "Street Food", 59671909l,  -20.230054f, 57.498047f, "minepayo"));
                restaurantService.addRestaurant(new Restaurant("Tamil League", "Tamil Road, Reduit", "Mauritian Cuisine & Snack", null,  null, null, "tamilleague"));
                restaurantService.addRestaurant(new Restaurant("Mamou", null, "TukShop", null,  null, null, "mamou"));
                restaurantService.addRestaurant(new Restaurant("Gloria", "Royal Road, Reduit", "Fast Food", null,  null, null, "gloria"));
                restaurantService.addRestaurant(new Restaurant("Dhol puri", "Royal Road, Reduit", "Snack", null,  null, null, "dholpuri"));
                restaurantService.addRestaurant(new Restaurant("Rodrigues", null, "Mixed", null,  null, null, "rodrigues"));
                restaurantService.addRestaurant(new Restaurant("Ken", null, "Mauritian Cuisine", null,  null, null, "ken"));
                restaurantService.addRestaurant(new Restaurant("Boulette Soubi", "Mahebourg", "Snack", null,  null, null, "ken"));
            }

        }catch (Exception e){
            //do nothing
        }

    }


    public static void populateRatingTable(){
        ratingService = new RatingService();

        try{
            if(ratingService.getAllRatings().isEmpty()){

            }

        }catch (Exception e){
            //do nothing
        }
    }

    public static void populateMenuTable(){
        menuService = new MenuService();

        try {
            if(menuService.getAllMenu().isEmpty()){

            menuService.addMenu(new Menu("Mine Payo", 50.00f, 1l, "minepayo"));
            menuService.addMenu(new Menu("Halim Payo", 45.00f, 1l, "minepayo"));
             }
        }catch (Exception e){
            //do nothing
        }
    }
}
