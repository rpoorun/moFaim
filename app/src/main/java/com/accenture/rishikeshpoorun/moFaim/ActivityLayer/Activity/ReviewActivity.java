package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.text.format.DateFormat;

import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.RecyclerView.ReviewsRecyclerViewAdapter;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.UserSession;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Review;
import com.accenture.rishikeshpoorun.moFaim.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {


    private Long restaurantId;
    private DatabaseReference firebase;
    private String userName;
    private RecyclerView RecyclerReviews;
    private EditText mCommentInput;
    private ImageView mbtnSend;
    private ArrayList<Review> reviews = new ArrayList<>();
    private DatabaseReference dbRef;
    private FirebaseRecyclerAdapter<Review, ReviewsRecyclerViewAdapter> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        userName = MainActivity.userSession.getUserName();
//        listReviews = findViewById(R.id.listView_reviews);
        RecyclerReviews = findViewById(R.id.recyclerView_reviews);
        mCommentInput = findViewById(R.id.editText_review_input);
        mbtnSend = findViewById(R.id.btnSend);
        restaurantId = getIntent().getLongExtra("RestaurantId",0);
        dbRef = FirebaseDatabase.getInstance().getReference();
        firebase = FirebaseDatabase.getInstance().getReference(String.format("REVIEW_TABLE/%s", restaurantId.toString()));

        mbtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = mCommentInput.getText().toString();
                firebase.push().setValue(new Review(userName, comment));
                mCommentInput.setText("");
            }
        });

        populateReviewsRecycler();


    }





    private void populateReviewsRecycler(){

        Query query = FirebaseDatabase.getInstance()
                .getReference("REVIEW_TABLE")
                .child(restaurantId.toString())
                .limitToFirst(50)
                .orderByKey();

        FirebaseRecyclerOptions<Review> options = new FirebaseRecyclerOptions.Builder<Review>()
                .setQuery(query, Review.class)
                .build();


        RecyclerView recyclerView = findViewById(R.id.recyclerView_reviews);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FirebaseRecyclerAdapter<Review, ReviewsRecyclerViewAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ReviewsRecyclerViewAdapter holder, int position, @NonNull Review model) {
                holder.setmDate(model.getDateAndTime());
                holder.setmComment(model.getComment());
                holder.setmUsername(model.getUsername());
            }

            @NonNull
            @Override
            public ReviewsRecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.component_review_list_view,viewGroup,false);

                return new ReviewsRecyclerViewAdapter(view);
            }
        };

        recyclerView.setAdapter(adapter);
    }



    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }




    //    private void populateReviewsList(){
//
//        Query query = firebase.limitToFirst(50);
//
//        FirebaseListOptions<Review> options = new FirebaseListOptions.Builder<Review>()
//                .setQuery(query, Review.class)
//                .setLayout(R.layout.component_review_list_view)
//                .build();
//
//        adapter = new FirebaseListAdapter<Review>(options) {
//            @Override
//            protected void populateView(View v, Review model, int position) {
//
//                TextView mComment, mUsername, mDate;
//                mComment = v.findViewById(R.id.textView_review_comment);
//                mUsername = v.findViewById(R.id.textView_review_username);
//                mDate = v.findViewById(R.id.textView_review_date);
//
//                mComment.setText(model.getComment());
//                mUsername.setText(model.getUsername());
//                mDate.setText(DateFormat.format("dd-MM-yyyy (HH:mm)", model.getDateAndTime()));
//            }
//        };
//
//        listReviews.setAdapter(adapter);
//    }
}
