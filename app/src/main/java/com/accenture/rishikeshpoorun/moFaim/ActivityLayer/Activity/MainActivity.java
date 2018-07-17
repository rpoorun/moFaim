package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity;

import android.arch.persistence.room.Room;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Fragment.Login;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service.UserService;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.DatabaseUtility;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO.MoFaimDatabase;
import com.accenture.rishikeshpoorun.moFaim.R;

public class MainActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager;
    public static UserService userService;
    private MoFaimDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init database connectivity
        // NOTE: using the database connectivity on the main thread since SQLite is cached on the phone
        // NOTE: Need to use Async and background thread for other database
        database = Room.databaseBuilder(getApplicationContext(), MoFaimDatabase.class, "moFaim.db")
                .allowMainThreadQueries()
                .build();

        //init service layer with database
        userService = new UserService(database);

        //population database tables
        DatabaseUtility.populateUserTable(database);

        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.fragmentLayout_main) != null){

            if(savedInstanceState != null){
                return;
            }

            fragmentManager.beginTransaction().add(R.id.fragmentLayout_main, new Login()).commit();
        }
    }
}
