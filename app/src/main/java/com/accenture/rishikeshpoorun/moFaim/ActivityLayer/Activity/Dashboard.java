package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Fragment.Login;
import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.RecyclerView.RestaurantRecyclerView;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Database.MoFaimDatabase;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.User;
import com.accenture.rishikeshpoorun.moFaim.R;

public class Dashboard extends AppCompatActivity {
    private TextView profileName;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Long userId = MainActivity.userSession.getUserId();

        User user = MainActivity.userService.fetchUserById(userId);
        profileName = (TextView) findViewById(R.id.textView_username);
        profileName.setText(user.toString());


        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.fragmentLayout_dashboard) != null){

            if(savedInstanceState != null){
                return;
            }

            fragmentManager.beginTransaction().add(R.id.fragmentLayout_dashboard, new RestaurantRecyclerView()).commit();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.gc();
        Intent stay = new Intent(this, Dashboard.class);
        startActivity(stay);
        finish();
    }
}
