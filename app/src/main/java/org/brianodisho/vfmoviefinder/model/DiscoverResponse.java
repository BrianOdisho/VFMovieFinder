package org.brianodisho.vfmoviefinder.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * POJO for the response returned by the discover endpoint of the api.
 */

public class DiscoverResponse {

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


    public static class Movie {

        @SerializedName("vote_count")
        private int voteCount;

        @SerializedName("id")
        private int id;

        @SerializedName("video")
        private boolean video;

        @SerializedName("vote_average")
        private double voteAverage;

        @SerializedName("title")
        private String title;

        @SerializedName("popularity")
        private double popularity;

        @SerializedName("poster_path")
        private String posterPath;

        @SerializedName("original_language")
        private String originalLanguage;

        @SerializedName("original_title")
        private String originalTitle;

        @SerializedName("genre_ids")
        private List<Integer> genreIds;

        @SerializedName("backdrop_path")
        private String backdropPath;

        @SerializedName("adult")
        private boolean adult;

        @SerializedName("overview")
        private String overview;

        @SerializedName("release_date")
        private String releaseDate;


        public int getVoteCount() {
            return voteCount;
        }


        public int getId() {
            return id;
        }


        public boolean isVideo() {
            return video;
        }


        public double getVoteAverage() {
            return voteAverage;
        }


        public String getTitle() {
            return title;
        }


        public double getPopularity() {
            return popularity;
        }


        public String getPosterPath() {
            return posterPath;
        }


        public String getOriginalLanguage() {
            return originalLanguage;
        }


        public String getOriginalTitle() {
            return originalTitle;
        }


        public List<Integer> getGenreIds() {
            return genreIds;
        }


        public String getBackdropPath() {
            return backdropPath;
        }


        public boolean isAdult() {
            return adult;
        }


        public String getOverview() {
            return overview;
        }


        public String getReleaseDate() {
            return releaseDate;
        }
    }
}
