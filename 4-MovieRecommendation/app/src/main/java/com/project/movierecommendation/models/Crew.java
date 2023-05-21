package com.project.movierecommendation.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Crew implements Parcelable {

    @SerializedName("adult")
    @Expose
    public Boolean adult;
    @SerializedName("gender")
    @Expose
    public Integer gender;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("known_for_department")
    @Expose
    public String knownForDepartment;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("original_name")
    @Expose
    public String originalName;
    @SerializedName("popularity")
    @Expose
    public Double popularity;
    @SerializedName("profile_path")
    @Expose
    public String profilePath;
    @SerializedName("credit_id")
    @Expose
    public String creditId;
    @SerializedName("department")
    @Expose
    public String department;
    @SerializedName("job")
    @Expose
    public String job;

    public Crew(Boolean adult, Integer gender, Integer id, String knownForDepartment, String name, String originalName, Double popularity, String profilePath, String creditId, String department, String job) {
        this.adult = adult;
        this.gender = gender;
        this.id = id;
        this.knownForDepartment = knownForDepartment;
        this.name = name;
        this.originalName = originalName;
        this.popularity = popularity;
        this.profilePath = profilePath;
        this.creditId = creditId;
        this.department = department;
        this.job = job;
    }

    protected Crew(Parcel in) {
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
        creditId = in.readString();
        department = in.readString();
        job = in.readString();
    }

    public static final Creator<Crew> CREATOR = new Creator<Crew>() {
        @Override
        public Crew createFromParcel(Parcel in) {
            return new Crew(in);
        }

        @Override
        public Crew[] newArray(int size) {
            return new Crew[size];
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

    public String getCreditId() {
        return creditId;
    }

    public String getDepartment() {
        return department;
    }

    public String getJob() {
        return job;
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
        parcel.writeString(creditId);
        parcel.writeString(department);
        parcel.writeString(job);
    }
}
