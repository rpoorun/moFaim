package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Fragment.Login;
import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Fragment.Logout;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service.RestaurantService;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service.UserService;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.DatabaseUtility;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.UserSession;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Database.MoFaimDatabase;
import com.accenture.rishikeshpoorun.moFaim.R;

public class MainActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    public static UserService userService;
    public static RestaurantService restaurantService;
    public static UserSession userSession;
    private MoFaimDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init database build & connectivity
        database = DatabaseUtility.getDatabase(this);

        //init session for user logging
        userSession = new UserSession(this);

        //init service layer with database
        userService = new UserService(database);
        restaurantService = new RestaurantService(database);

        //populating database tables
        DatabaseUtility.populateUserTable();
        DatabaseUtility.populateRestaurantTable();


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
        super.onBackPressed();
        System.gc();
        Intent stay = new Intent(this, MainActivity.class);
        startActivity(stay);
        finish();
    }
}
