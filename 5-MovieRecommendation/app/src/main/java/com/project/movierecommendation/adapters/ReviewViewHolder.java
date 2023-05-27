package com.project.movierecommendation.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.movierecommendation.R;

public class ReviewViewHolder extends RecyclerView.ViewHolder {

    ImageView review_avatar;
    TextView review_content, review_name, review_ratingText,review_date;
    RatingBar review_ratingBar;

    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        review_avatar = itemView.findViewById(R.id.review_avatar);
        review_name = itemView.findViewById(R.id.review_name);
        review_content = itemView.findViewById(R.id.review_content);
        review_ratingText = itemView.findViewById(R.id.review_rating_text);
        review_ratingBar = itemView.findViewById(R.id.review_rating);
        review_date = itemView.findViewById(R.id.review_date);
    }
}
