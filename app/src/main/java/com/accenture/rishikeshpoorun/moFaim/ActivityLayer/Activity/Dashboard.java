package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.RecyclerView.RestaurantRecyclerView;
import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.RecyclerView.RestaurantRecyclerViewAdapter;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.UserSession;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Restaurant;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.User;
import com.accenture.rishikeshpoorun.moFaim.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Dashboard extends AppCompatActivity implements SearchView.OnQueryTextListener, CompoundButton.OnCheckedChangeListener {
    private TextView profileName;
    private TextView userLocationMsg;
    private FragmentManager fragmentManager;
    private static final int REQUEST_INTERNET = 1;
    private MenuItem searchAction;
    private SearchView searchView;
    private List<Restaurant> allRestaurantList;
    private static final int TIME_DELAY_EXIT = 500;
    private static int back_pressed;
    private Switch switchLocation;
    private static boolean flagKnowLocation;
    private static String[] locationAddr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        if(MainActivity.userSession.getUserId() == null){
            startActivity(new Intent(this, MainActivity.class));
        }
        User user = MainActivity.userService.fetchUserById(MainActivity.userSession.getUserId());
        profileName = (TextView) findViewById(R.id.textView_username);
        userLocationMsg = findViewById(R.id.textView_known_user_location);
        switchLocation = findViewById(R.id.switch_dashboard_location);
        profileName.setText("Hello " + user.getUserName());
        allRestaurantList = MainActivity.restaurantService.getAllRestaurant();
        back_pressed = 0;


        switchLocation.setChecked(flagKnowLocation);
        switchLocation.setOnCheckedChangeListener(this);

        fragmentManager = getSupportFragmentManager();

        //update fragment if location flag is set to known
        if(flagKnowLocation){
            getUserLocation();

        }

        if (findViewById(R.id.fragmentLayout_dashboard) != null) {


            if (savedInstanceState != null) {

                return;
            }

            fragmentManager.beginTransaction().add(R.id.fragmentLayout_dashboard, new RestaurantRecyclerView()).commit();
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
        menuInflater.inflate(R.menu.toolbar_dashboard_menu, menu);

        searchAction = menu.findItem(R.id.menu_action_search);

        //searchAction.setOnActionExpandListener(onActionExpandListener);

        MenuItem menuItem = menu.findItem(R.id.menu_action_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    /**
     * handles the click action of the option in the toolbar for this activity
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_action_share:
                shareLink();
                break;

            case R.id.menu_to_logout:
                Intent toMain = new Intent(this, MainActivity.class);
                startActivity(toMain);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void shareLink() {
        //check if the application have permission to use internet else request at runtime
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, REQUEST_INTERNET);
        } else {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            String shareBody = "https://play.google.com/store/apps/details?id=com.accenture.rishikeshpoorun.moFaim";
            String shareSubject = "Link to this new app 'moFaim'";
            share.putExtra(Intent.EXTRA_SUBJECT, shareSubject);
            share.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(share, "Share using"));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_INTERNET) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                shareLink();
            } else {
                Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
            }
        }

        //if gps permission granted open to enable location
        if(requestCode == 10){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
               getUserLocation();
            }
            else {
                Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * handle user action when pressing the back button
     */
    @Override
    public void onBackPressed() {
        try {
            if (back_pressed > 0) {
                back_pressed = 0;
                super.onBackPressed();
                finish();
            } else {

                System.gc();
                Toast.makeText(this, "Double press BACK again to exit", Toast.LENGTH_SHORT).show();
                back_pressed++;

            }
        } catch (Exception e) {

        }

    }


    /**
     * Method to get the text from the search bar
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * Method to update the text if user changes the search query from the search bar
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newQuery) {

        RestaurantRecyclerView.updateRestaurantList(filterRestaurantList(newQuery));
        return true;
    }


    /**
     * Takes a string query a seek the whole restaurant list for possible matches
     * Returns the possible matches
     * @param query
     * @return
     */
    private List<Restaurant> filterRestaurantList(String query){
        //Check the onScreen list instead of querying the database
        List<Restaurant> newList = new ArrayList<>();
        for (Restaurant r : allRestaurantList) {
            //check if restaurant name matches the search query
            if (r.getRestaurantName() != null && r.getRestaurantName().toLowerCase().contains(query.toLowerCase())) {
                //check if restaurant is not in the new list, then add
                if (!newList.contains(r)) {
                    newList.add(r);
                }
            }
            //check if restaurant location/address matches the search query
            if (r.getAddress() != null && r.getAddress().toLowerCase().contains(query.toLowerCase())) {
                //check if restaurant if not in the new list, then add
                if (!newList.contains(r)) {
                    newList.add(r);
                }

            }
        }

        return newList;
    }



    /**
     * Method to get the location address and city names from Lat & long
     */
    private String[] getLocationAddress(Double latitude, Double longitude) {
        String[] address = new String[3];
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> resultList;

        try {
            resultList = geocoder.getFromLocation(latitude, longitude, 1);

            address[0] = resultList.get(0).getAddressLine(0);
            address[1] = resultList.get(0).getAdminArea();
            address[2] = resultList.get(0).getLocality();

        } catch (Exception e) {

        }


        return address;
    }

    /**
     * Handles the user action on button location
     * @param compoundButton
     * @param b
     */
    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (switchLocation.isChecked()) {
            //on position
            getUserLocation();
        }
        else{
            //off position
            //repopulate the recycler view with all restaurant
            userLocationMsg.setText("");
            RestaurantRecyclerView.updateRestaurantList(allRestaurantList);
            //reset flag
            flagKnowLocation = false;
        }

    }

    private void getUserLocation() {
        //check if the application have permission to use internet else request at runtime
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, REQUEST_INTERNET);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 10);
        }
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 10);
        }

                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                LocationListener locationListener = new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                                    Double lat = location.getLatitude();
                                    Double lon = location.getLongitude();
                                    //convert the coordinates to city name
                                    locationAddr = getLocationAddress(lat,lon);
                                    //set flag to true
                                    flagKnowLocation = true;
                                    //display location
                                    userLocationMsg.setText("Food near: "+locationAddr[2]);
                                    //update the recyclerview with new possible location
                                    RestaurantRecyclerView.updateRestaurantList(filterRestaurantList(locationAddr[2]));
                                }

                        @Override
                        public void onStatusChanged(String s, int i, Bundle bundle) {

                                }

                        @Override
                        public void onProviderEnabled(String s) {

                                }

                        @Override
                        public void onProviderDisabled(String s) {
                            //go to setting to enable gps service
                            //getApplicationContext().startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                            Intent gpsOpt = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(gpsOpt);

                                }
                 };

                    locationManager.requestLocationUpdates("gps", 1, 20, locationListener);
    }



}
