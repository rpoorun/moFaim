package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Restaurant;
import com.accenture.rishikeshpoorun.moFaim.R;

public class RestaurantActivity extends AppCompatActivity {

    private Long restaurantId;
    private Restaurant restaurant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        restaurantId = getIntent().getLongExtra("restaurantId",0);
        restaurant = MainActivity.restaurantService.getRestaurantById(restaurantId);
    }
}
