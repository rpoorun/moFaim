package com.accenture.rishikeshpoorun.moFaim.ActivityLayer.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import com.accenture.rishikeshpoorun.moFaim.R;

public class ReviewsRecyclerViewAdapter extends RecyclerView.ViewHolder {

    private TextView mComment, mUsername, mDate;

    public ReviewsRecyclerViewAdapter(@NonNull View itemView) {
        super(itemView);
        mComment = itemView.findViewById(R.id.textView_review_comment);
        mUsername = itemView.findViewById(R.id.textView_review_username);
        mDate = itemView.findViewById(R.id.textView_review_date);
    }

    public void setmComment(String c){
        mComment.setText(c);
    }

    public void setmUsername(String u){
        mUsername.setText(String.format("%s commented:", u));
    }

    public void setmDate(Long d){
        mDate.setText(DateFormat.format("dd-MM-yyyy (HH:mm)",d));
    }

}
