package com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service;

import android.util.Patterns;

import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity.MainActivity;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Exception.EmailConflictException;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Exception.EmptyFieldException;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Exception.InvalidEmailException;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Exception.InvalidPasswordException;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Exception.InvalidUsernameException;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.DatabaseUtility;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.InputValidation;
import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.PasswordEncryption;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Database.MoFaimDatabase;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO.UserDAO;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.User;

import java.util.List;

import static com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.PasswordEncryption.encrypt;

public class UserService {

    private UserDAO userDao;

    public UserService(){
        this.userDao = DatabaseUtility.getDatabase().userDAO();

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
        InputValidation.validateUsernameNotNull(fullname);
        InputValidation.validateEmailAlreadyExist(email, userDao);
        InputValidation.validatePasswordNotNull(password);
        InputValidation.validateConfirmPasswordNotNull(confirmPassword);

        //validate for proper email address
        InputValidation.validateProperEmailAddress(email);

        //validate if email already exist in database
        InputValidation.validateEmailAlreadyExist(email,userDao);

        //validate matching password
        InputValidation.validateMatchPassword(password, confirmPassword);

        String encryptedPassword = encrypt(password);

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

    public User checkLogin(String email, String password) throws InvalidPasswordException, InvalidEmailException, EmptyFieldException {
        //validate fields are not null
        InputValidation.validatePasswordNotNull(password);
        InputValidation.validateEmailNotNull(email);

        User user = searchUserByEmail(email);
        Boolean flag = PasswordEncryption.checkPassword(password, user.getPassword());

        if(flag){
            return user;
        }
        else{
            throw new InvalidPasswordException("Invalid Password for this Email account");
        }
    }


    public boolean matchUsernameAndEmail(String username, String email) throws EmptyFieldException, InvalidUsernameException, InvalidEmailException {
        //validate fields are not empty
        InputValidation.validateUsernameNotNull(username);
        InputValidation.validateEmailNotNull(email);

        User u = MainActivity.userService.searchUserByEmail(email);
        //validate if username and email matches
        if(u.getUserName().equalsIgnoreCase(username)){
            return true;
        }
        else{
            throw new InvalidUsernameException("No account found for this username");
        }
    }

    public boolean setNewPassword(User user, String password, String confirmPassword) throws EmptyFieldException, InvalidPasswordException {
        //validate fields
        InputValidation.validatePasswordNotNull(password);
        InputValidation.validateConfirmPasswordNotNull(confirmPassword);

        //validate both field matches
        InputValidation.validateMatchPassword(password, confirmPassword);

        String encryptPassword = PasswordEncryption.encrypt(password);

        user.setPassword(encryptPassword);

        userDao.updateUser(user);

        return true;
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

}
