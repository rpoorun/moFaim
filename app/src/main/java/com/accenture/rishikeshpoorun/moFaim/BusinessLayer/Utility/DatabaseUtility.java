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
                restaurantService.addRestaurant(new Restaurant("Mafia Grill","Royal Road, Mahebourg","Fast Food Burgers",6315010l,-20.410731f,5770.5990f,"mafiagrill"));
                restaurantService.addRestaurant(new Restaurant("Le Bazilic","Rue de Maurice, Mahebourg","Mixed Variety",52548191l, -20.407759f,57.707307f,"bazilic"));
                restaurantService.addRestaurant(new Restaurant("Les Jardins", "Royal Road, Beau Vallon", "French", 6312850l, -20.424349f, 57.701107f, "jardinbovallon"));
                restaurantService.addRestaurant(new Restaurant("Chee LiChop", "Rose Belle", "Asian", 150l, -20.398317f, 57.598466f,"cheelichop" ));
                restaurantService.addRestaurant(new Restaurant("Comlone", "Novelle France", "Mauritian", 6778336l,-20.386728f, 57.561229f,"comlonenf"));
                restaurantService.addRestaurant(new Restaurant("BiteMe!", "Curepipe", "Sushi Restaurant", 54983211l,-20.316394f,57.523583f,"biteme"));
                restaurantService.addRestaurant(new Restaurant("FlameGrill", "Curepipe", "Indian",6740437l,-20.318674f,57.524088f,"flamegrill"));
                restaurantService.addRestaurant(new Restaurant("Hideaway","Quatre Bornes", "French", 4669443l,-20.261677f,57.495339f,"hideaway"));
                restaurantService.addRestaurant(new Restaurant("Terrazza", "Quatre Bornes", "European", 4546330l,-20.258181f,57.490292f,"terrazza"));
                /*restaurantService.addRestaurant(new Restaurant());
                restaurantService.addRestaurant(new Restaurant());
                restaurantService.addRestaurant(new Restaurant());
                restaurantService.addRestaurant(new Restaurant());*/
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
            menuService.addMenu(new Menu("Halim Payo", 45.00f, 1l, "payo_halim"));
                menuService.addMenu(new Menu("Mine Ek Halim", 60.00f, 1l, "payo_mine_ek_halim"));
                menuService.addMenu(new Menu("Sexy Deer Burger", 180.00f, 2l, "sexy_deer_burger"));
                menuService.addMenu(new Menu("Assassin Chick & Lamb", 200.00f, 2l, "assassin_chick_fish_lamb"));
                menuService.addMenu(new Menu("Pink Starter", 120.00f, 2l, "mafiagrill_pink_starter"));
                menuService.addMenu(new Menu("Burger Mafia", 280.00f, 2l, "mafiagrill_mafia_burger"));
                menuService.addMenu(new Menu("Mexican Shot", 250.00f, 2l, "mexican_shot"));
                menuService.addMenu(new Menu("Mafia Wrappers", 300.00f, 2l ,"mafia_lamb_wrapper"));
                menuService.addMenu(new Menu("Fish Curry", 120.00f, 3l, "bazilic_fish_curry"));
                menuService.addMenu(new Menu("Fried Fish",200.0f, 3l, "bazilic_grill_fish" ));
                menuService.addMenu(new Menu("Ring Snacks", 70.0f, 3l, "bazilic_ring_snack"));
                menuService.addMenu(new Menu("American Pizza", 60.00f, 3l, "bazilic_pizza"));
                menuService.addMenu(new Menu("Halim Seafood", 50.0f, 3l , "bazilic_halim"));
                menuService.addMenu(new Menu("Canju Fries", 20.0f, 3l,"bazilic_canju"));
                menuService.addMenu(new Menu("Lamb Stick", 200.0f, 4l, "jardinbovallon_lamb_stick"));
                menuService.addMenu(new Menu("Cream Dessert", 120.0f, 4l, "jardinbovallon_cream_brulle"));
                menuService.addMenu(new Menu("Ice Cream", 50.0f, 4l, "jardinbovallon_icecream"));
                menuService.addMenu(new Menu("SeaFood Platter", 300.0f, 4l, "jardinbovallon_seafood_platter"));
                menuService.addMenu(new Menu("Rice & Meat", 150.0f, 5l,"cheelichop_rice_pork"));
                menuService.addMenu(new Menu("Crispy Chicken", 200f, 5l, "cheelichop_crispy_chicken"));
                menuService.addMenu(new Menu("Whole Dinner", 325f, 5l , "cheelichop_rice_meat_platter"));
                menuService.addMenu(new Menu("Hainanese Snack", 100f, 5l, "cheelichop_hanaise_snack"));
                menuService.addMenu(new Menu("Tangerine Chop", 200f, 5l, "cheelichop_targarine"));
                menuService.addMenu(new Menu("Fast Breast", 200f, 6l,"comlonenf_fast_breast"));
                menuService.addMenu(new Menu("Sizzled Rice", 150f, 6l,"comlonenf_sizzle_rice"));
                menuService.addMenu(new Menu("Brochette", 150f,6l,"comlonenf_stick_chick"));
                menuService.addMenu(new Menu("Mixed Stir Fry", 100f, 6l, "comlonenf_mixed_stir_fry"));
                menuService.addMenu(new Menu("Medium Platter",300f,7l,"biteme_dinner"));
                menuService.addMenu(new Menu("Snack",50f,7l,"biteme_snack"));
                menuService.addMenu(new Menu("Complete Platter",300f,7l,"biteme_complete"));
                menuService.addMenu(new Menu("Sweet Sushi",150f,7l,"biteme_sweet"));
                menuService.addMenu(new Menu("Whole Platter",450f,7l,"biteme_whole_platter"));
                menuService.addMenu(new Menu("Chicken Chops",120f,8l,"flamegrill_chick_chop"));
                menuService.addMenu(new Menu("Brochette",280f,8l,"flamegrill_brochette"));
                menuService.addMenu(new Menu("Mini Thali",250f,8l,"flamegrill_mini_thali"));
                menuService.addMenu(new Menu("Whole Thali",300f,8l,"flamegrillt_thali"));
                menuService.addMenu(new Menu("Drum dips",100f,8l,"flamegrill_drumdips"));
                menuService.addMenu(new Menu("Tiramisu",90f,9l,"hideaway_tiramisu"));
                menuService.addMenu(new Menu("Ice cream Fudge",150f,9l,"hideaway_icecream_chocofudge"));
                menuService.addMenu(new Menu("Brownie",120f,9l,"hideaway_brownie"));
                menuService.addMenu(new Menu("Red Berries",150f,9l,"hideaway_strawberry"));
                menuService.addMenu(new Menu("Shy fudge",120f,9l,"hideaway_shy_fudge"));
                menuService.addMenu(new Menu("Stonegrill Mix",300f,10l,"terrazza_stonegrill_mix"));
                menuService.addMenu(new Menu("Pasta",120f,10l,"terrazza_pasta"));
                menuService.addMenu(new Menu("Lasagna",220f,10l,"terrazza_lasagne"));
                /*menuService.addMenu(new Menu("",,""));
                menuService.addMenu(new Menu("",,""));
                menuService.addMenu(new Menu("",,""));
                menuService.addMenu(new Menu("",,""));
                menuService.addMenu(new Menu("",,""));
                menuService.addMenu(new Menu("",,""));
                menuService.addMenu(new Menu("",,""));
                menuService.addMenu(new Menu("",,""));
                menuService.addMenu(new Menu("",,""));
                menuService.addMenu(new Menu("",,""));
                menuService.addMenu(new Menu("",,""));*/

            }
        }catch (Exception e){
            //do nothing
        }
    }
}
