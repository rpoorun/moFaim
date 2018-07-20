package com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service;

import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.DatabaseUtility;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO.RatingDAO;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Rating;

import java.util.List;

public class RatingService {
    private RatingDAO ratingDao;

    public RatingService() {
        this.ratingDao = DatabaseUtility.getDatabase().ratingDao();
    }

    public Float getUserRating(Long userId, Long restaurantId){

        Rating r = ratingDao.getRating(userId,restaurantId);
        if(r != null) {
            return r.getRating();
        }
        else {
            return 0.0f;
        }
    }


    public Float getOverallRestaurantRating(Long restaurantId){
        List<Rating> ratings = ratingDao.getAllRatingByRestaurantId(restaurantId);
        Float dummy = 0.0f;
        Float avg = 0.0f;
        if(ratings.size()>0) {
            for (Rating r : ratings) {
                dummy += r.getRating();
            }
            avg = dummy / ratings.size();
        }
            return avg;
    }


    public void addRating(Long userId, Long restaurantId, Float rating){
        Rating r = new Rating(userId,restaurantId,rating);
        ratingDao.insertRating(r);
    }

    public void updateRating(Long userId, Long restaurantId, Float newRating){
        Rating r = ratingDao.getRating(userId,restaurantId);
        if(r != null) {
            r.setRating(newRating);
            ratingDao.updateRating(r);
        }
        else {

            ratingDao.insertRating(new Rating(userId,restaurantId,newRating));
        }
    }

    public List<Rating> getAllRatings() {
        return ratingDao.getAllRatings();
    }
}

