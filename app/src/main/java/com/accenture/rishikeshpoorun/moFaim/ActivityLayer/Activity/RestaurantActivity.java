package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Restaurant;
import com.accenture.rishikeshpoorun.moFaim.R;

public class RestaurantActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CALL = 1;
    private Long restaurantId;
    private Restaurant restaurant;
    private TextView restaurantName;
    private TextView restaurantAddress;
    private TextView restaurantStyle;
    private RatingBar restaurantOverallRating;
    private ImageView restaurantBanner;
    private ImageView iconActionCall;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);



        //init activity attributes
        restaurantName = findViewById(R.id.textView_restaurant_name);
        restaurantAddress = findViewById(R.id.textView_restaurant_address);
        restaurantStyle = findViewById(R.id.textView_restaurant_style);
        restaurantBanner = findViewById(R.id.imageView_restaurant_banner);
        restaurantOverallRating = findViewById(R.id.ratingBar_restaurant_rating);
        iconActionCall = findViewById(R.id.imageView_action_call);

        //get restaurant object using id from previous activity
        restaurantId = getIntent().getLongExtra("restaurantId",0);
        restaurant = MainActivity.restaurantService.getRestaurantById(restaurantId);

        //fetch resource ID
        Integer imageId = this.getResources().getIdentifier(restaurant.getPhotoName()+"_icon", "drawable", this.getPackageName());

        restaurantName.setText(restaurant.getRestaurantName());
        restaurantStyle.setText(restaurant.getStyle());
        restaurantAddress.setText(restaurant.getAddress());
        restaurantOverallRating.setRating(restaurant.getOverallRating());
        restaurantBanner.setImageResource(imageId);

        if(restaurant.getPhoneNumber() == null){
            iconActionCall.setVisibility(View.GONE);
        }
        else {
            iconActionCall.setVisibility(View.VISIBLE);
        }

        iconActionCall.setOnClickListener(this);


    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView_action_call:
                makePhoneCall();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
            else{
                Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void makePhoneCall(){
        //format the phone number to callable action
        String dial = "tel:"+ restaurant.getPhoneNumber().toString();

        //check if the application have permission to call else request at runtime
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }
        else {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.gc();
        Intent stay = new Intent(this, Dashboard.class);
        startActivity(stay);
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        finish();



    }
}
