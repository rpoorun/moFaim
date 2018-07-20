package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.RecyclerView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity.MainActivity;
import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity.RestaurantActivity;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Menu;
import com.accenture.rishikeshpoorun.moFaim.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuRecyclerView extends Fragment {
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private List<Menu> list;
    private MenuRecyclerViewAdaper adapter;
    private Long restaurantId;


    public MenuRecyclerView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_recycler_view, container, false);

        restaurantId = RestaurantActivity.getRestaurantId();


        //init the view
        recyclerView = view.findViewById(R.id.menu_recycler_view);
        //set a layout manager to arrangement the viewHolder or components
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        //assign the LayoutManager to the recyclerView
        recyclerView.setLayoutManager(layoutManager);
        //populate the list from database
        list = MainActivity.menuService.getAllMenuByRestaurantId(restaurantId);
        //init the adapter to convert the list to viewHolder or component
        adapter = new MenuRecyclerViewAdaper(getContext(), list);
        //standardize the sizes
        recyclerView.setHasFixedSize(true);
        //parse the adapter to populate each viewHolder
        recyclerView.setAdapter(adapter);


        return view;
    }

}
