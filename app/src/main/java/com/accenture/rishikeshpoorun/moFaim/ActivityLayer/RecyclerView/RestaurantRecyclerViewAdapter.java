package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Restaurant;
import com.accenture.rishikeshpoorun.moFaim.R;

import java.util.List;

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewAdapter.RestaurantViewHolder>{

    private List<Restaurant> list;

    public RestaurantRecyclerViewAdapter(List<Restaurant> list){
        this.list= list;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        TextView textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.component_restaurant_recycler_view, viewGroup, false);
        RestaurantViewHolder restaurantViewHolder = new RestaurantViewHolder(textView);
        return restaurantViewHolder;
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder viewHolder, int i) {
        viewHolder.restaurantName.setText(list.get(i).getRestaurantName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder{

        TextView restaurantName;
        public RestaurantViewHolder(TextView itemView) {
            super(itemView);
            restaurantName = itemView;
        }
    }
}
