package com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryption {

    private PasswordEncryption(){
        //Do nothing
        //Utility class
    }

    public static String encrypt(String password){
        return
                BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}
