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


public class SplashScreen extends AppCompatActivity {


    private final int SPLACE_TIME_OUT = 500;
    private Boolean isBackgroundComplete;
    private TextView mStatus;
    private ImageView mAnimation;
    private String[] loadMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mStatus = findViewById(R.id.textView_splash_status);

        mAnimation =  findViewById(R.id.imageView_splash_animation);

        Glide.with(this)
                .load(R.drawable.splash_animation)
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
                .into(mAnimation);

        loadMessages = generateMessages();

        DatabaseUtility.buildDatabase(this);

        doSplashWelcome();
    }

    private void doSplashWelcome(){
        Thread loadData = new Thread(){
            @Override
            public void run() {
                synchronized (this) {

                    try {
                        int counter = 0;
                        (new loadBackgroundData()).execute();
                        wait(10000);
                        while (true){
                            mStatus.setText(loadMessages[counter]);
                            counter ++;
                            if (!isBackgroundComplete){
                                wait(SPLACE_TIME_OUT);

                            }
                            else {
                                mStatus.setText("Happy Eating!!!");
                                wait(3500);
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

            //after background task is completed, set flag to completed
            isBackgroundComplete = true;
            return null;
        }
    }




}
