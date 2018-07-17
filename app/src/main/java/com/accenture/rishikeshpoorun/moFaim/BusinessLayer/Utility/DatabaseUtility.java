package com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility;


import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service.UserService;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO.MoFaimDatabase;

public class DatabaseUtility {

    private static UserService userService;

    private DatabaseUtility(){
        //do nothing
        //util class
    }

    public static void populateUserTable(MoFaimDatabase database){
        userService = new UserService(database);

        try{
            userService.addUser("admin", "admin@admin.com", "admin", "admin");
            userService.addUser("foo", "foo@foo.com", "foo", "foo");
            userService.addUser("moo", "moo@moo.com", "moo", "moo");

        }catch (Exception e){
            //do nothing
        }

    }
}
