package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.accenture.rishikeshpoorun.moFaim.ActivityLayer.Activity.RestaurantActivity;
import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Restaurant;
import com.accenture.rishikeshpoorun.moFaim.R;

import java.util.List;

public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewAdapter.RestaurantViewHolder>{

    private List<Restaurant> list;
    private Context context;

    public RestaurantRecyclerViewAdapter(List<Restaurant> list, Context context){
        this.list= list;
        this.context = context;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.component_restaurant_recycler_view, viewGroup, false);
        RestaurantViewHolder restaurantViewHolder = new RestaurantViewHolder(itemView, context, list);
        return restaurantViewHolder;
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder viewHolder, int i) {
        Restaurant restaurant = list.get(i);
        Integer imageId = context.getResources().getIdentifier(restaurant.getPhotoName()+"_icon", "drawable", context.getPackageName());

        viewHolder.restaurantName.setText(restaurant.getRestaurantName());
        viewHolder.restaurantStyle.setText(restaurant.getStyle());
        viewHolder.restaurantAddress.setText(restaurant.getAddress());
        if(imageId == null){
            imageId = context.getResources().getIdentifier("minepayo_icon", null, null);
        }
        viewHolder.restaurantIcon.setImageResource(imageId);
        viewHolder.restaurantRating.setRating(restaurant.getOverallRating());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class RestaurantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView restaurantIcon;
        TextView restaurantName;
        TextView restaurantAddress;
        TextView restaurantStyle;
        RatingBar restaurantRating;
        List<Restaurant> list;
        Context context;
        public RestaurantViewHolder(View itemView, Context context, List<Restaurant> list) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.textView_restaurant_name);
            restaurantAddress = itemView.findViewById(R.id.textView_restaurant_address);
            restaurantStyle = itemView.findViewById(R.id.textView_restaurant_style);
            restaurantIcon = itemView.findViewById(R.id.imageView_restaurant_icon);
            restaurantRating = itemView.findViewById(R.id.ratingBar_restaurant_overallRating);

            this.context = context;
            this.list = list;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent toRestaurant = new Intent(context, RestaurantActivity.class);
            toRestaurant.putExtra("restaurantId", list.get(getAdapterPosition()).getRestaurantId());
            context.startActivity(toRestaurant);
        }
    }
}
