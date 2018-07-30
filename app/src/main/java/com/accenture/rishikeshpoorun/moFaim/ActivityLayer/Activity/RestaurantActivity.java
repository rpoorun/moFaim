package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.RecyclerView.MenuRecyclerView;
import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.RecyclerView.RestaurantRecyclerView;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.GlideApp;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Restaurant;
import com.accenture.rishikeshpoorun.moFaim.R;

import java.util.Locale;

public class RestaurantActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CALL = 1;
    private static final int REQUEST_INTERNET = 1;
    private static Long restaurantId;
    private Restaurant restaurant;
    private TextView restaurantName;
    private TextView restaurantAddress;
    private TextView restaurantStyle;
    private RatingBar restaurantOverallRating;
    private ImageView restaurantBanner;
    private ImageView iconActionReview;
    private ImageView iconActionCall;
    private ImageView iconActionMap;
    private TextView rateItMsg;
    private RatingBar ratingBar;
    private Float userRating;
    private FragmentManager fragmentManager;

    public static Long getRestaurantId(){
        return restaurantId;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        //init the views on this activity
        initViewAttributes();

        //get restaurant object using id from previous activity
        restaurantId = getIntent().getLongExtra("restaurantId",0);
        restaurant = MainActivity.restaurantService.getRestaurantById(restaurantId);

        //populate the different views on this activity
        populateViews();

        //Handles the fragment recycler view
        menuRecylerViewManager(savedInstanceState);



        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                MainActivity.ratingService.updateRating(MainActivity.userSession.getUserId(),restaurantId, v);

                Float newRatings = MainActivity.ratingService.getOverallRestaurantRating(restaurantId);

                restaurantOverallRating.setRating(newRatings);
                if(userRating >0.0f){
                    rateItMsg.setText("Yours:");
                }
                else {
                    rateItMsg.setText("Rate it!");
                }
            }
        });

        iconActionCall.setOnClickListener(this);
        iconActionMap.setOnClickListener(this);
        iconActionReview.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView_action_call:
                makePhoneCall();
                break;

            case R.id.ratingBar_rate_it:
                break;

            case R.id.imageView_action_load_map:
                openLocationOnMap();
                break;

            case R.id.imageView_action_review:
                startActivity(new Intent(this,ReviewActivity.class).putExtra("RestaurantId",restaurantId));
                break;

        }
    }

    /**
     * Set the option in the action/tool bar for this activity
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_restaurant_menu, menu);
        return true;
    }

    /**
     * handles the click action of the option in the toolbar for this activity
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_action_share_restaurant:
                shareRestaurant();
                break;

            case R.id.menu_to_logout:
                Intent toMain = new Intent(this, MainActivity.class);
                startActivity(toMain);
                break;

        }

        return super.onOptionsItemSelected(item);
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

    private void shareRestaurant(   ){
        //check if the application have permission to use internet else request at runtime
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.INTERNET}, REQUEST_INTERNET);
        }
        else {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            String shareBody = String.format("%s is found at %s, contact on: %s",
                    restaurant.getRestaurantName(), restaurant.getAddress(), restaurant.getPhoneNumber().toString());
            String shareSubject = "Hey, this seems yummy";
            share.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
            share.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(share, "Share using"));
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

    private void openLocationOnMap(){
        //format the URI to map location
        String uri = String.format("geo:%f,%f?q=%s", restaurant.getLatitude(), restaurant.getLongitude(), restaurant.getRestaurantName());
        Intent openMap = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        openMap.setPackage("com.google.android.apps.maps");
        startActivity(openMap);
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


    private void initViewAttributes(){
        //init activity attributes
        restaurantName = findViewById(R.id.textView_restaurant_name);
        restaurantAddress = findViewById(R.id.textView_restaurant_address);
        restaurantStyle = findViewById(R.id.textView_restaurant_style);
        restaurantBanner = findViewById(R.id.imageView_restaurant_banner);
        restaurantOverallRating = findViewById(R.id.ratingBar_restaurant_rating);
        iconActionCall = findViewById(R.id.imageView_action_call);
        iconActionMap = findViewById(R.id.imageView_action_load_map);
        ratingBar = findViewById(R.id.ratingBar_rate_it);
        rateItMsg = findViewById(R.id.textView_rate_it_msg);
        iconActionReview = findViewById(R.id.imageView_action_review);
    }


    private void populateViews(){
        //fetch resource ID
        Integer imageId = this.getResources().getIdentifier(restaurant.getPhotoName()+"_banner", "drawable", this.getPackageName());
        userRating = MainActivity.ratingService.getUserRating(MainActivity.userSession.getUserId(), restaurantId);
        restaurantName.setText(restaurant.getRestaurantName());
        restaurantStyle.setText(restaurant.getStyle());
        restaurantAddress.setText(restaurant.getAddress());
        restaurantOverallRating.setRating((restaurant.getOverallRating() == null)? 0.0f:restaurant.getOverallRating());
        GlideApp.with(this)
                .load(imageId)
                .into(restaurantBanner);

        if(userRating != 0.0f){
            rateItMsg.setText("Yours:");
            ratingBar.setRating(userRating);
        }

        if(restaurant.getPhoneNumber() == null){
            iconActionCall.setVisibility(View.GONE);
        }
        else {
            iconActionCall.setVisibility(View.VISIBLE);
        }

        if(restaurant.getLatitude() == null && restaurant.getLongitude() == null){
            iconActionMap.setVisibility(View.GONE);
        }
        else {
            iconActionMap.setVisibility(View.VISIBLE);
        }
    }


    private void menuRecylerViewManager(Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.fragmentLayout_menu) != null){


            if(savedInstanceState != null){
                return;
            }

            fragmentManager.beginTransaction().add(R.id.fragmentLayout_menu, new MenuRecyclerView()).commit();
        }



    }


}
