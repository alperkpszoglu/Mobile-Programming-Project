package com.project.movierecommendation.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review implements Parcelable {
    @SerializedName("author")
    @Expose
    public String author;
    @SerializedName("author_details")
    @Expose
    public Author authorDetails;
    @SerializedName("content")
    @Expose
    public String content;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("url")
    @Expose
    public String url;

    public Review(String author, Author authorDetails, String content, String createdAt, String id, String updatedAt, String url) {
        this.author = author;
        this.authorDetails = authorDetails;
        this.content = content;
        this.createdAt = createdAt;
        this.id = id;
        this.updatedAt = updatedAt;
        this.url = url;
    }

    protected Review(Parcel in) {
        author = in.readString();
        content = in.readString();
        createdAt = in.readString();
        id = in.readString();
        updatedAt = in.readString();
        url = in.readString();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    public Author getAuthorDetails() {
        return authorDetails;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getId() {
        return id;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(author);
        parcel.writeString(content);
        parcel.writeString(createdAt);
        parcel.writeString(id);
        parcel.writeString(updatedAt);
        parcel.writeString(url);
    }

    @Override
    public String toString() {
        return "Review{" +
                "author='" + author + '\'' +
                ", authorDetails=" + authorDetails +
                ", content='" + content + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", id='" + id + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
