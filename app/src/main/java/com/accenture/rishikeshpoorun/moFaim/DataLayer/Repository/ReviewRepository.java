package com.accenture.rishikeshpoorun.moFaim.DataLayer.Repository;

import android.content.Context;
import android.os.AsyncTask;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReviewRepository{

    private static DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference();

    public ReviewRepository(){
    }



    public static void saveUser(String name, String email, String password){
        firebaseDatabase.child("Name").setValue(name);
        firebaseDatabase.child("Email").setValue(email);
        firebaseDatabase.child("Password").setValue(password);
    }


}
