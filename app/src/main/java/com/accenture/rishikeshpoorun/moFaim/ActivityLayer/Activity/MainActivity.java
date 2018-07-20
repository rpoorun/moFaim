package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Fragment.Login;
import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Fragment.Logout;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service.MenuService;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service.RatingService;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service.RestaurantService;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service.UserService;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.DatabaseUtility;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.UserSession;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Database.MoFaimDatabase;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Rating;
import com.accenture.rishikeshpoorun.moFaim.R;

public class MainActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    public static UserService userService;
    public static RestaurantService restaurantService;
    public static RatingService ratingService;
    public static UserSession userSession;
    public static MenuService menuService;
    private MoFaimDatabase database;
    private static int back_pressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        back_pressed = 0;

        //getdatabase build & connectivity
        database = DatabaseUtility.getDatabase();

        //init session for user logging
        userSession = new UserSession(this);

        //init service layer with database
        userService = new UserService();
        restaurantService = new RestaurantService();
        ratingService = new RatingService();
        menuService = new MenuService();

        fragmentManager = getSupportFragmentManager();

            if (findViewById(R.id.fragmentLayout_main) != null) {

                if (savedInstanceState != null) {
                    return;
                }

                if(userSession.inSession()){
                    fragmentManager.beginTransaction().add(R.id.fragmentLayout_main, new Logout()).commit();
                }
                else {
                    fragmentManager.beginTransaction().add(R.id.fragmentLayout_main, new Login()).commit();
                }
            }


    }



    @Override
    public void onBackPressed() {
        try {
            if (back_pressed > 0) {
                back_pressed = 0;
                super.onBackPressed();
                finish();
            } else {

                System.gc();
                Toast.makeText(this,"Double press BACK again to exit",Toast.LENGTH_SHORT).show();
                back_pressed++;

            }
        }catch (Exception e){

        }
    }
}
