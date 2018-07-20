package com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service;

import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.DatabaseUtility;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO.RatingDAO;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Database.MoFaimDatabase;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO.RestaurantDAO;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Rating;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantService {

    private RestaurantDAO restaurantDao;
    private RatingService ratingService;

    public RestaurantService(){
        this.restaurantDao = DatabaseUtility.getDatabase().restaurantDAO();
        this.ratingService = new RatingService();
    }

    public void addRestaurant(Restaurant restaurant){
        restaurantDao.insertRestaurant(restaurant);
    }

    public Restaurant getRestaurantById(Long restaurantId){
        Restaurant r = restaurantDao.selectRestaurantById(restaurantId);
        r.setOverallRating(ratingService.getOverallRestaurantRating(restaurantId));
        return r;
    }

    public List<Restaurant> getAllRestaurant(){

        List<Restaurant> list = restaurantDao.getAllRestaurant();

        for(int i = 0; i < list.size(); i++){
            Restaurant r = list.get(i);
            list.get(i).setOverallRating(ratingService.getOverallRestaurantRating(r.getRestaurantId()));
        }

        return list;
    }
}
