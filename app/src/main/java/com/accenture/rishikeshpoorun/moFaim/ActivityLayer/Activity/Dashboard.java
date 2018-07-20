package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.RecyclerView.RestaurantRecyclerView;
import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.RecyclerView.RestaurantRecyclerViewAdapter;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.UserSession;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Restaurant;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.User;
import com.accenture.rishikeshpoorun.moFaim.R;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity implements SearchView.OnQueryTextListener{
    private TextView profileName;
    private FragmentManager fragmentManager;
    private static final int REQUEST_INTERNET = 1;
    private MenuItem searchAction;
    private SearchView searchView;
    private List<Restaurant> allRestaurantList;
    private static final int TIME_DELAY_EXIT = 500;
    private static int back_pressed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        User user = MainActivity.userService.fetchUserById(MainActivity.userSession.getUserId());
        profileName = (TextView) findViewById(R.id.textView_username);
        profileName.setText(user.toString());
        back_pressed =0;

        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.fragmentLayout_dashboard) != null){


            if(savedInstanceState != null){
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
        switch (item.getItemId())
        {
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

    private void shareLink(){
        //check if the application have permission to use internet else request at runtime
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.INTERNET}, REQUEST_INTERNET);
        }
        else {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            String shareBody = "https://play.google.com/store/apps/details?id=com.accenture.rishikeshpoorun.moFaim";
            String shareSubject = "Link to this new app 'moFaim'";
            share.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
            share.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(share, "Share using"));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_INTERNET){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                shareLink();
            }
            else{
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
                Toast.makeText(this,"Press BACK again to exit",Toast.LENGTH_SHORT).show();
                back_pressed++;

            }
        }catch (Exception e){

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

        //Check the onScreen list instead of querying the database
        allRestaurantList = RestaurantRecyclerViewAdapter.getList();
        List<Restaurant> newList = new ArrayList<>();
        for (Restaurant r: allRestaurantList){
            //check if restaurant name matches the search query
            if(r.getRestaurantName() != null && r.getRestaurantName().toLowerCase().contains(newQuery)){
                //check if restaurant is not in the new list, then add
                if(!newList.contains(r)){
                    newList.add(r);
                }
            }
            //check if restaurant location/address matches the search query
            if (r.getAddress() != null && r.getAddress().toLowerCase().contains(newQuery)){
                //check if restaurant if not in the new list, then add
                if(!newList.contains(r)){
                    newList.add(r);
                }

            }
        }

        RestaurantRecyclerView.updateRestaurantList(newList);
        return true;
    }
}
