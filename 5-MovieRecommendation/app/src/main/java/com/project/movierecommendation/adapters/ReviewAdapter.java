package com.project.movierecommendation.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.project.movierecommendation.R;
import com.project.movierecommendation.models.Review;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Review> reviewList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        ((ReviewViewHolder) holder).review_ratingText.setText("0");
        ((ReviewViewHolder) holder).review_ratingBar.setRating(0);

        if (reviewList.get(position).getAuthorDetails().getRating() != null) {
            ((ReviewViewHolder) holder).review_ratingBar.setRating(Float.parseFloat(String.valueOf(reviewList.get(position).getAuthorDetails().getRating() / 2)));
            ((ReviewViewHolder) holder).review_ratingText.setText(String.valueOf(reviewList.get(position).getAuthorDetails().getRating() / 2));
        }

        ((ReviewViewHolder) holder).review_name.setText(String.valueOf(reviewList.get(position).getAuthorDetails().getName()));
        ((ReviewViewHolder) holder).review_date.setText(reviewList.get(position).getCreatedAt());
        ((ReviewViewHolder) holder).review_content.setText(reviewList.get(position).getContent());

        if (reviewList.get(position).getAuthorDetails().getAvatarPath() == null) {

            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.ic_user)
                    .transform(new CircleCrop())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_user)
                    .into(((ReviewViewHolder) holder).review_avatar);
            return;
        }
        if (reviewList.get(position).getAuthorDetails().getAvatarPath().startsWith("/https")) {

            Glide.with(holder.itemView.getContext())
                    .load(reviewList.get(position).getAuthorDetails().getAvatarPath().substring(1))
                    .transform(new CircleCrop())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_user)
                    .into(((ReviewViewHolder) holder).review_avatar);
            return;
        }
        if (reviewList.get(position).getAuthorDetails().getAvatarPath().startsWith("/")) {

            Glide.with(holder.itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/" + reviewList.get(position).getAuthorDetails().getAvatarPath().substring(1))
                    .transform(new CircleCrop())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_user)
                    .into(((ReviewViewHolder) holder).review_avatar);
            return;
        }
        Glide.with(holder.itemView.getContext())
                .load(reviewList.get(position).getAuthorDetails().getAvatarPath())
                .transform(new CircleCrop())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(((ReviewViewHolder) holder).review_avatar);

    }

    @Override
    public int getItemCount() {
        if (reviewList != null) {
            return reviewList.size();
        }
        return 0;
    }


    @SuppressLint("NotifyDataSetChanged")
    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
        notifyDataSetChanged();
    }
}
