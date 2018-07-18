package com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility;

import android.util.Patterns;

import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Exception.EmailConflictException;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Exception.EmptyFieldException;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Exception.InvalidEmailException;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Exception.InvalidPasswordException;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO.UserDAO;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.User;

public class InputValidation {


    /**
     * Validation method to check if all fields are complete
     * @return True if field parameters are not null
     * @throws EmptyFieldException
     */
    public static boolean validateUsernameNotNull(String fullname) throws EmptyFieldException{
        if(fullname.isEmpty() || fullname == null){
            throw new EmptyFieldException("The Full Name field cannot be empty");
        }
        return true;
    }

    public static boolean validateEmailNotNull(String email)throws EmptyFieldException{
        if(email.isEmpty() || email == null){
            throw new EmptyFieldException("The Email field cannot be empty");
        }
        return true;
    }

    public static boolean validatePasswordNotNull(String password) throws EmptyFieldException{
        if(password.isEmpty() || password == null){
            throw new EmptyFieldException("The Password field cannot be empty");
        }
        return true;
    }
    public static boolean validateConfirmPasswordNotNull( String confirmPassword) throws EmptyFieldException {
        if(confirmPassword.isEmpty() || confirmPassword == null){
            throw new EmptyFieldException("The Confirm Password field cannot be empty");
        }
        return true;
    }

    /**
     * @param email
     * @return True if email format is valid
     * @throws InvalidEmailException
     */
    public static boolean validateProperEmailAddress(String email) throws InvalidEmailException {

        if(Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            return true;
        }
        else{
            throw new InvalidEmailException("Please insert a valid Email Address");
        }
    }

    /**
     * @param password
     * @param confirmPassword
     * @return True is both password field matches
     * @throws InvalidPasswordException
     */
   public static boolean validateMatchPassword(String password, String confirmPassword) throws InvalidPasswordException {
        if(password.contentEquals(confirmPassword)){
            return true;
        }
        else{
            throw new InvalidPasswordException("Password does not match");
        }
    }


    public static boolean validateEmailAlreadyExist(String email, UserDAO userDao) throws EmailConflictException, InvalidEmailException {
        User u = userDao.getUserByEmail(email);

        if(u == null){
            return true;
        }
        else{
            throw new EmailConflictException("This Email Address is already registered");
        }
    }

}
