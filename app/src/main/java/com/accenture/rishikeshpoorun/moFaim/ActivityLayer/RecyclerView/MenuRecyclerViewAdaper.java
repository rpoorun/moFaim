package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.accenture.rishikeshpoorun.moFaim.DataLayer.Entities.Menu;
import com.accenture.rishikeshpoorun.moFaim.R;

import java.util.List;

public class MenuRecyclerViewAdaper extends RecyclerView.Adapter<MenuRecyclerViewAdaper.MenuViewHolder> {

    private List<Menu> list;
    private Context context;

    public MenuRecyclerViewAdaper(Context context, List<Menu> list){
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.component_menu_recycler_view, viewGroup, false);
        MenuViewHolder menuViewHolder = new MenuViewHolder(itemView);
        return menuViewHolder;
    }

    @Override
    public void onBindViewHolder(MenuViewHolder menuViewHolder, int i) {
        Menu menu = list.get(i);
        Integer imageId = context.getResources().getIdentifier(menu.getPhotoName()+"_icon", "drawable", context.getPackageName());

        if(imageId == null){
            imageId = context.getResources().getIdentifier("minepayo_icon", null, null);
        }
        menuViewHolder.menuIcon.setImageResource(imageId);
        menuViewHolder.menuName.setText(menu.getMenuName());
        menuViewHolder.menuPrice.setText("Rs."+ menu.getMenuPrice().toString());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView menuIcon;
        TextView menuName;
        TextView menuPrice;
        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            menuIcon = itemView.findViewById(R.id.imageView_menu_icon);
            menuName = itemView.findViewById(R.id.textView_menu_name);
            menuPrice = itemView.findViewById(R.id.textView_menu_price);
        }
    }
}
