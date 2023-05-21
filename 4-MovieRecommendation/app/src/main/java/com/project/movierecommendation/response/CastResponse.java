package com.project.movierecommendation.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.project.movierecommendation.models.Cast;
import com.project.movierecommendation.models.Crew;

import java.util.List;

public class CastResponse {
    @SerializedName("cast")
    @Expose()
    private List<Cast> cast;

    @SerializedName("id")
    @Expose()
    private int id;

    public int getId() {
        return id;
    }

    @SerializedName("crew")
    @Expose()
    private List<Crew> crew;

    public List<Cast> getCast() {
        return cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    @Override
    public String toString() {
        return "CastResponse{" +
                "cast=" + cast +
                '}';
    }
}
