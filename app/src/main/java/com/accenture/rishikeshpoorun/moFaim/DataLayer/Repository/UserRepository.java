package com.accenture.rishikeshpoorun.moFaim.DataLayer.Repository;


import com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO.UserDAO;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.User;


public class UserRepository {

    private UserDAO userDAO;

    private static UserRepository mInstance;

    public UserRepository(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public static UserRepository getInstance(UserDAO userDAO){
        if(mInstance == null){
            mInstance = new UserRepository(userDAO);
        }
        return mInstance;
    }

    public User getUserByID(Long userId){
        return userDAO.getUserByID(userId);
    }

    public User checkLogin(String email, String password){
        return userDAO.selectUserAndPassword(email, password);
    }

    public void insertUser(User user){
        userDAO.insertUser(user);
    }

    public void updateUser(User user){
        userDAO.updateUser(user);
    }

    public void deleteUser(User user){
        userDAO.deleteUser(user);
    }

    public User getUserByEmail(String email) {
       return userDAO.getUserByEmail(email);
    }
}
