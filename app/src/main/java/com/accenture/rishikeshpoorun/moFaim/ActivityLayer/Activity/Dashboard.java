package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.User;
import com.accenture.rishikeshpoorun.moFaim.R;

public class Dashboard extends AppCompatActivity {
    private TextView profileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent previous = getIntent();
        Long userId = Long.valueOf(previous.getStringExtra("userId"));

        User user = MainActivity.userService.fetchUserById(userId);
        profileName = (TextView) findViewById(R.id.textView_username);
        profileName.setText(user.toString());

    }
}
