package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.RecyclerView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity.MainActivity;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Restaurant;
import com.accenture.rishikeshpoorun.moFaim.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantRecyclerView extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Restaurant> list;
    private RestaurantRecyclerViewAdapter adapter;

    public RestaurantRecyclerView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_restaurant_recycler_view, container, false);

        //init the view
        recyclerView = view.findViewById(R.id.restaurant_recycler_view);
        //set a layout manager to arrangement the viewHolder or components
        layoutManager = new LinearLayoutManager(getContext());
        //assign the LayoutManager to the recyclerView
        recyclerView.setLayoutManager(layoutManager);
        //populate the list from database
        list = MainActivity.restaurantService.getAllRestaurant();
        //init the adapter to convert the list to viewHolder or component
        adapter = new RestaurantRecyclerViewAdapter(list, getContext());
        //standardize the sizes
        recyclerView.setHasFixedSize(true);
        //parse the adapter to populate each viewHolder
        recyclerView.setAdapter(adapter);


        return view;
    }

}
