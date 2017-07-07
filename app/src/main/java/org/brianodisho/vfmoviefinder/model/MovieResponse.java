package org.brianodisho.vfmoviefinder.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * POJO for the response returned by the discover endpoint of the api.
 */

public class MovieResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<Movie> movies;


    public int getPage() {
        return page;
    }


    public int getTotalResults() {
        return totalResults;
    }


    public int getTotalPages() {
        return totalPages;
    }


    public List<Movie> getMovies() {
        return movies;
    }
}
