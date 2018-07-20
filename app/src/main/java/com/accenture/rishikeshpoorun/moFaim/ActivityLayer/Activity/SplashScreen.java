package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.DatabaseUtility;
import com.accenture.rishikeshpoorun.moFaim.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.concurrent.ExecutionException;


public class SplashScreen extends AppCompatActivity {


    private final int SPLACE_TIME_OUT = 500;
    private Boolean isBackgroundComplete = false;
    private TextView mStatus;
    private ImageView mAnimation;
    private String[] waitMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mStatus = findViewById(R.id.textView_splash_status);
        mAnimation =  findViewById(R.id.imageView_splash_animation);
        waitMsg =generateMessages();
        DatabaseUtility.buildDatabase(getApplicationContext());

        doSplashWelcome();
    }

    private void doSplashWelcome(){
        Thread loadData = new Thread(){
            @Override
            public void run() {
                synchronized (this) {
                    try {
                        wait(2000);
                        int counter = 0;
                        (new loadBackgroundData()).execute();

                        while (true){

                            if (!isBackgroundComplete){
                                mStatus.setText(waitMsg[counter]);
                                wait(SPLACE_TIME_OUT);
                                counter++;
                            }
                            else {
                                mStatus.setText("Happy Eating!!!");
                                wait(1500);
                                break;
                            }
                        }

                    } catch (Exception e) {
                    } finally {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }
            }
        };
        loadData.start();
        doAnimation();
    }

    private String[] generateMessages(){
        String[] messages = new String[6];
        messages[0] = "Getting your toaster warm";
        messages[1] = "Getting your favourite butter and jam";
        messages[2] = "Now toasting your sandwich";
        messages[3] = "Just a little bit more";
        messages[4] = "Getting your sandwich crisp";
        messages[5] = "Done!!";


        return messages;
    }

    private class loadBackgroundData extends AsyncTask<Context, Integer, String> {

        @Override
        protected String doInBackground(Context... contexts) {

                //populating database tables
                DatabaseUtility.populateUserTable();
                DatabaseUtility.populateRestaurantTable();
                DatabaseUtility.populateMenuTable();
                DatabaseUtility.populateRatingTable();
            //after background task is completed, set flag to completed
            isBackgroundComplete = true;
            return null;
        }
    }

   private void doAnimation() {

            Glide.with(getApplicationContext())
                    .load(R.drawable.splash_animation)
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                    .into(mAnimation);


    }




}
