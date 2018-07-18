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
        restaurantService = MainActivity.restaurantService;

        try{
            if(restaurantService.getAllRestaurant().isEmpty()) {

                restaurantService.addRestaurant(new Restaurant("Mine Payo", "Stadium Road, Reduit, Moka", "Street Food", null, null, null, null, "minepayo"));
                restaurantService.addRestaurant(new Restaurant("Tamil League", "Tamil Road, Reduit", "Mauritian Cuisine & Snack", null, null, null, null, "tamilleague"));
                restaurantService.addRestaurant(new Restaurant("Mamou", null, "TukShop", null, null, null, null, "mamou"));
                restaurantService.addRestaurant(new Restaurant("Gloria", "Royal Road, Reduit", "Fast Food", null, null, null, null, "gloria"));
                restaurantService.addRestaurant(new Restaurant("Dhol puri", "Royal Road, Reduit", "Snack", null, null, null, null, "dholpuri"));
                restaurantService.addRestaurant(new Restaurant("Rodrigues", null, "Mixed", null, null, null, null, "rodrigues"));
                restaurantService.addRestaurant(new Restaurant("Ken", null, "Mauritian Cuisine", null, null, null, null, "ken"));
            }

        }catch (Exception e){
            //do nothing
        }

    }
}
