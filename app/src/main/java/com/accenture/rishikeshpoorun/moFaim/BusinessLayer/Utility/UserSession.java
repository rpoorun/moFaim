package com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility;


import android.content.Context;
import android.content.SharedPreferences;

import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.User;

/**
 * Using the sharedPreference to store the user session and session details
 */
public class UserSession {

    private Context context;
    private SharedPreferences sharedPreferences;
    private final String SESSION_FILENAME = "inSession";
    private final String SESSION_STATUS = "loginStatus";
    private final String SESSION_USER_ID = "userID";
    private final String SESSION_USER_NAME = "userName";
    private final String SESSION_USER_EMAIL = "email";
    private static Long userId;



    public UserSession(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SESSION_FILENAME, Context.MODE_PRIVATE);
    }

    /**
     * On creating a session, the method check if the app already have a userSession
     * In which case it will skip
     * Else, a new session is created with the user details
     * @param user
     */
    public void startSession(User user){
        //check if already in session
        if(inSession()){
            userId = getUserId();
            return;
        }
        else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(SESSION_STATUS, true);
            editor.putLong(SESSION_USER_ID, user.getUserId());
            editor.putString(SESSION_USER_NAME, user.getUserName());
            editor.putString(SESSION_USER_EMAIL, user.getEmail());
            userId = user.getUserId();
            editor.commit();
        }
    }

    /**
     * On destroying a session, the method check if the app has a userSession or not
     * In which case it will skip
     * Else, the existing session is set to false and the user details reset
     */
    public void destroySession(){
        if(!inSession()){
            return;
        }
        else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(SESSION_STATUS, false);
            editor.putString(SESSION_USER_EMAIL,null);
            editor.putString(SESSION_USER_NAME,null);
            editor.commit();
        }
    }

    /**
     * Check if session status is on or off
     * @return TRUE if session is set to on
     * @return FALSE if session is set to off
     */
    public boolean inSession() {
        return sharedPreferences.getBoolean(SESSION_STATUS,false);
    }

    public static Long getSessionId(){
        return userId;
    }

    public Long getUserId(){
        if (inSession()){
            Long userId = sharedPreferences.getLong(SESSION_USER_ID,0);
            return userId;
        }
        return null;
    }

    public String getUserName(){
        if (inSession()){
            return sharedPreferences.getString(SESSION_USER_NAME,"");
        }
        return null;
    }

    /**
     * Set a user email address as cached but do not start the session
     * @param email
     */
    public void setUserEmail(String email){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SESSION_USER_EMAIL, email);
        editor.commit();
    }

    public String getUserEmail(){
        return sharedPreferences.getString(SESSION_USER_EMAIL,null);
    }

}
