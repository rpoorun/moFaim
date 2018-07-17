package com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility;


import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity.MainActivity;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service.RestaurantService;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service.UserService;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Database.MoFaimDatabase;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Restaurant;

public class DatabaseUtility {

    private static UserService userService;
    private static RestaurantService restaurantService;

    private DatabaseUtility(){
        //do nothing
        //util class
    }

    /**
     * Populate the tables with hardcoded values
     */
    public static void populateUserTable(){
        userService = MainActivity.userService;

        try{
            userService.addUser("admin", "admin@admin.com", "admin", "admin");
            userService.addUser("foo", "foo@foo.com", "foo", "foo");
            userService.addUser("moo", "moo@moo.com", "moo", "moo");

        }catch (Exception e){
            //do nothing
        }

    }

    /**
     * Populate the tables with hardcoded values
     */
    public static void populateRestaurantTable() {
        restaurantService = MainActivity.restaurantService;

        try{
            restaurantService.addRestaurant(new Restaurant("Mine Payo", null, null, null, null, null, null));
            restaurantService.addRestaurant(new Restaurant("Tamil League", null, null, null, null, null, null));
            restaurantService.addRestaurant(new Restaurant("Mamou", null, null, null, null, null, null));
            restaurantService.addRestaurant(new Restaurant("Gloria", null, null, null, null, null, null));
            restaurantService.addRestaurant(new Restaurant("Dhol puri", null, null, null, null, null, null));
            restaurantService.addRestaurant(new Restaurant("Rodrigue", null, null, null, null, null, null));
            restaurantService.addRestaurant(new Restaurant("Ken", null, null, null, null, null, null));


        }catch (Exception e){
            //do nothing
        }

    }
}
