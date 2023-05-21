package com.project.movierecommendation.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cast implements Parcelable {

    @SerializedName("adult")
    @Expose()
    public Boolean adult;
    @SerializedName("gender")
    @Expose()
    public Integer gender;
    @SerializedName("id")
    @Expose()
    public Integer id;
    @SerializedName("known_for_department")
    @Expose()
    public String knownForDepartment;
    @SerializedName("name")
    @Expose()
    public String name;
    @SerializedName("original_name")
    @Expose()
    public String originalName;
    @SerializedName("popularity")
    @Expose()
    public Double popularity;
    @SerializedName("profile_path")
    @Expose()
    public String profilePath;
    @SerializedName("cast_id")
    @Expose()
    public Integer castId;
    @SerializedName("character")
    @Expose()
    public String character;
    @SerializedName("credit_id")
    @Expose()
    public String creditId;
    @SerializedName("order")
    @Expose()
    public Integer order;

    public Cast(Boolean adult, Integer gender, Integer id, String knownForDepartment, String name, String originalName, Double popularity, String profilePath, Integer castId, String character, String creditId, Integer order) {
        this.adult = adult;
        this.gender = gender;
        this.id = id;
        this.knownForDepartment = knownForDepartment;
        this.name = name;
        this.originalName = originalName;
        this.popularity = popularity;
        this.profilePath = profilePath;
        this.castId = castId;
        this.character = character;
        this.creditId = creditId;
        this.order = order;
    }

    protected Cast(Parcel in) {
        byte tmpAdult = in.readByte();
        adult = tmpAdult == 0 ? null : tmpAdult == 1;
        if (in.readByte() == 0) {
            gender = null;
        } else {
            gender = in.readInt();
        }
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        knownForDepartment = in.readString();
        name = in.readString();
        originalName = in.readString();
        if (in.readByte() == 0) {
            popularity = null;
        } else {
            popularity = in.readDouble();
        }
        profilePath = in.readString();
        if (in.readByte() == 0) {
            castId = null;
        } else {
            castId = in.readInt();
        }
        character = in.readString();
        creditId = in.readString();
        if (in.readByte() == 0) {
            order = null;
        } else {
            order = in.readInt();
        }
    }

    public static final Creator<Cast> CREATOR = new Creator<Cast>() {
        @Override
        public Cast createFromParcel(Parcel in) {
            return new Cast(in);
        }

        @Override
        public Cast[] newArray(int size) {
            return new Cast[size];
        }
    };

    public Boolean getAdult() {
        return adult;
    }

    public Integer getGender() {
        return gender;
    }

    public Integer getId() {
        return id;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public Integer getCastId() {
        return castId;
    }

    public String getCharacter() {
        return character;
    }

    public String getCreditId() {
        return creditId;
    }

    public Integer getOrder() {
        return order;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (adult == null ? 0 : adult ? 1 : 2));
        if (gender == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(gender);
        }
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(knownForDepartment);
        parcel.writeString(name);
        parcel.writeString(originalName);
        if (popularity == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(popularity);
        }
        parcel.writeString(profilePath);
        if (castId == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(castId);
        }
        parcel.writeString(character);
        parcel.writeString(creditId);
        if (order == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(order);
        }
    }

    @Override
    public String toString() {
        return "Cast{" +
                "adult=" + adult +
                ", gender=" + gender +
                ", id=" + id +
                ", knownForDepartment='" + knownForDepartment + '\'' +
                ", name='" + name + '\'' +
                ", originalName='" + originalName + '\'' +
                ", popularity=" + popularity +
                ", profilePath='" + profilePath + '\'' +
                ", castId=" + castId +
                ", character='" + character + '\'' +
                ", creditId='" + creditId + '\'' +
                ", order=" + order +
                '}';
    }
}
