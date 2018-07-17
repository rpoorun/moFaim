package com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Patterns;

import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Exception.EmptyFieldException;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Exception.InvaliEmailFormatException;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Exception.InvalidPasswordException;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO.MoFaimDatabase;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO.UserDAO;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.User;

public class UserService {

    private UserDAO userDao;

    public UserService(MoFaimDatabase database){
        this.userDao = database.userDAO();

    }

    /**
     * Service method to add new user to the database
     * Calls for validation if user already exists or not, is password are matching or not
     * @param fullname
     * @param email
     * @param password
     * @param confirmPassword
     */
    public void addUser(String fullname, String email, String password, String confirmPassword) throws EmptyFieldException, InvaliEmailFormatException, InvalidPasswordException {

        //validate for complete fields
        validateNotNullFields(fullname,email,password,confirmPassword);

        //validate for proper email address
        validateProperEmailAddress(email);

        //validate matching password
        validateMatchPassword(password, confirmPassword);

        User u = new User(fullname, email, password);

            userDao.insertUser(u);
    }


    public User searchUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public User checkLogin(String email, String password) {
        return  userDao.selectUserAndPassword(email, password);
    }

    /**
     * Private validation methods
     *
     */

    /**
     * Validation method to check if all fields are complete
     * @param fullname
     * @param email
     * @param password
     * @param confirmPassword
     * @return True if field parameters are not null
     * @throws EmptyFieldException
     */
    private boolean validateNotNullFields(String fullname, String email, String password, String confirmPassword) throws EmptyFieldException {

        if(fullname.isEmpty() || fullname == null){
            throw new EmptyFieldException("The Full Name field cannot be empty");
        }
        if(email.isEmpty() || email == null){
            throw new EmptyFieldException("The Email field cannot be empty");
        }
        if(password.isEmpty() || password == null){
            throw new EmptyFieldException("The Password field cannot be empty");
        }
        if(confirmPassword.isEmpty() || confirmPassword == null){
            throw new EmptyFieldException("The Confirm Password field cannot be empty");
        }

      return true;
    }

    /**
     * @param email
     * @return True if email format is valid
     * @throws InvaliEmailFormatException
     */
    private boolean validateProperEmailAddress(String email) throws InvaliEmailFormatException {

        if(Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            return true;
        }
        else{
            throw new InvaliEmailFormatException("Please insert a valid Email Address");
        }
    }

    /**
     * @param password
     * @param confirmPassword
     * @return True is both password field matches
     * @throws InvalidPasswordException
     */
    private boolean validateMatchPassword(String password, String confirmPassword) throws InvalidPasswordException {
        if(password.contentEquals(confirmPassword)){
            return true;
        }
        else{
            throw new InvalidPasswordException("Password does not match");
        }
    }
}
