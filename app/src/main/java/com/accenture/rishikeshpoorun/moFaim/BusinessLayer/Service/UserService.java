package com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service;

import android.util.Patterns;

import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Exception.EmailConflictException;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Exception.EmptyFieldException;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Exception.InvalidEmailException;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Exception.InvalidPasswordException;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.PasswordEncryption;
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
    public void addUser(String fullname, String email, String password, String confirmPassword) throws EmptyFieldException, InvalidEmailException, InvalidPasswordException, EmailConflictException {

        //validate for complete fields
        validateNotNullFields(fullname,email,password,confirmPassword);

        //validate for proper email address
        validateProperEmailAddress(email);

        //validate if email already exist in database
        validateEmailAlreadyExist(email);

        //validate matching password
        validateMatchPassword(password, confirmPassword);

        String encryptedPassword = PasswordEncryption.encrypt(password);

        User u = new User(fullname, email, encryptedPassword);

            userDao.insertUser(u);
    }


    public User fetchUserById(Long id){

        return userDao.getUserByID(id);
    }

    public User searchUserByEmail(String email) throws InvalidEmailException {
        User u = userDao.getUserByEmail(email);

        if(u != null){
            return u;
        }
        else {
            throw new InvalidEmailException("This Email Address account does not exist");
        }
    }

    public User checkLogin(String email, String password) throws InvalidPasswordException, InvalidEmailException {

        User user = searchUserByEmail(email);
        Boolean flag = PasswordEncryption.checkPassword(password, user.getPassword());

        if(flag){
            return user;
        }
        else{
            throw new InvalidPasswordException("Invalid Password for this Email account");
        }
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
     * @throws InvalidEmailException
     */
    private boolean validateProperEmailAddress(String email) throws InvalidEmailException {

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
    private boolean validateMatchPassword(String password, String confirmPassword) throws InvalidPasswordException {
        if(password.contentEquals(confirmPassword)){
            return true;
        }
        else{
            throw new InvalidPasswordException("Password does not match");
        }
    }


   private boolean validateEmailAlreadyExist(String email) throws EmailConflictException, InvalidEmailException {
        User u = userDao.getUserByEmail(email);

        if(u == null){
            return true;
        }
        else{
            throw new EmailConflictException("This Email Address is already registered");
        }
   }

}
