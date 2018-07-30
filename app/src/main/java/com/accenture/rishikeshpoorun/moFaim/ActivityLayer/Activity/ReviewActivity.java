package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.UserSession;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Review;
import com.accenture.rishikeshpoorun.moFaim.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {


    private Long restaurantId;
    private DatabaseReference firebase;
    private String userName;
    private ListView listReviews;
    private ArrayList<Review> reviews = new ArrayList<>();
    private ArrayAdapter<Review> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        userName = MainActivity.userSession.getUserName();
        listReviews = findViewById(R.id.listView_reviews);
        adapter = new ArrayAdapter<Review>(this, R.layout.activity_review, R.id.listView_reviews, reviews);
        restaurantId = getIntent().getLongExtra("RestaurantId",0);

        firebase = FirebaseDatabase.getInstance().getReference("REVIEW_TABLE").child(restaurantId.toString());

        Review r = new Review(userName,"testing messages");

        firebase.push().setValue(r);

        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for(DataSnapshot dst : dataSnapshot.getChildren()){
                    Review r = dst.getValue(Review.class);

                    reviews.add(r);
                }
                listReviews.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
