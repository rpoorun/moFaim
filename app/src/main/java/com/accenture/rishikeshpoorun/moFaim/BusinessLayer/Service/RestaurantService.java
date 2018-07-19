package com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service;

import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.DatabaseUtility;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Database.MoFaimDatabase;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO.RestaurantDAO;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Restaurant;

import java.util.List;

public class RestaurantService {

    private RestaurantDAO restaurantDao;

    public RestaurantService(){
        this.restaurantDao = DatabaseUtility.getDatabase().restaurantDAO();
    }

    public void addRestaurant(Restaurant restaurant){
        restaurantDao.insertRestaurant(restaurant);
    }

    public Restaurant getRestaurantById(Long restaurantId){
        return restaurantDao.selectRestaurantById(restaurantId);
    }

    public List<Restaurant> getAllRestaurant(){
        return restaurantDao.getAllRestaurant();
    }
}
