package com.project.movierecommendation.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.project.movierecommendation.R;

public class CastViewHolder extends RecyclerView.ViewHolder {

    ImageView cast_image;
    TextView cast_name, cast_character;
    LottieAnimationView loading;


    public CastViewHolder(@NonNull View itemView) {
        super(itemView);
        cast_character = itemView.findViewById(R.id.cast_character);
        cast_name = itemView.findViewById(R.id.cast_name);
        cast_image = itemView.findViewById(R.id.cast_avatar);
        loading = itemView.findViewById(R.id.loading_animation);
    }
}
