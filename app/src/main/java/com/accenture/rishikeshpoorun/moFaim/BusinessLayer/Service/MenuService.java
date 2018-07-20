package com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Service;

import com.accenture.rishikeshpoorun.moFaim.BusinessLayer.Utility.DatabaseUtility;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.DAO.MenuDAO;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Menu;

import java.util.List;

public class MenuService {

    private MenuDAO menuDao;

    public MenuService(){
        this.menuDao = DatabaseUtility.getDatabase().menuDao();
    }

    public void addMenu(Menu menu){
        menuDao.insertMenu(menu);
    }

    public List<Menu> getAllMenu(){
        return menuDao.getAllMenu();
    }

    public List<Menu> getAllMenuByRestaurantId(Long restaurantId) {
        return menuDao.getAllMenuByRestaurantId(restaurantId);
    }
}
