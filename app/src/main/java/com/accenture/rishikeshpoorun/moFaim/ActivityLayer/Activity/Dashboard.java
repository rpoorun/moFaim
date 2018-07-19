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
import android.widget.TextView;
import android.widget.Toast;

import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.RecyclerView.RestaurantRecyclerView;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.User;
import com.accenture.rishikeshpoorun.moFaim.R;

public class Dashboard extends AppCompatActivity {
    private TextView profileName;
    private FragmentManager fragmentManager;
    private static final int REQUEST_INTERNET = 1;


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

    /**
     * Set the option in the action/tool bar for this activity
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_dashboard_menu, menu);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.gc();
        Intent stay = new Intent(this, Dashboard.class);
        startActivity(stay);
        finish();
    }
}
