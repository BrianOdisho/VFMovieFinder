package org.brianodisho.vfmoviefinder.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * POJO for the movie item inside the response returned by the discover endpoint of the api.
 */

public class Movie implements Parcelable {

    private static final String IMAGE_BASE_URL_BACKDROP = "https://image.tmdb.org/t/p/w780";
    private static final String IMAGE_BASE_URL_POSTER = "https://image.tmdb.org/t/p/w500";

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


    Movie(Parcel in) {
        voteCount = in.readInt();
        id = in.readInt();
        video = in.readByte() != 0;
        voteAverage = in.readDouble();
        title = in.readString();
        popularity = in.readDouble();
        posterPath = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        backdropPath = in.readString();
        adult = in.readByte() != 0;
        overview = in.readString();
        releaseDate = in.readString();
    }


    public static final Creator<org.brianodisho.vfmoviefinder.model.Movie> CREATOR = new Creator<org.brianodisho.vfmoviefinder.model.Movie>() {
        @Override
        public org.brianodisho.vfmoviefinder.model.Movie createFromParcel(Parcel in) {
            return new org.brianodisho.vfmoviefinder.model.Movie(in);
        }

        @Override
        public org.brianodisho.vfmoviefinder.model.Movie[] newArray(int size) {
            return new org.brianodisho.vfmoviefinder.model.Movie[size];
        }
    };


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


    public String getPosterPathFull() {
        return posterPath == null ? null : IMAGE_BASE_URL_POSTER + posterPath;
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


    public String getBackdropPathFull() {
        return backdropPath == null ? null : IMAGE_BASE_URL_BACKDROP + backdropPath;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(voteCount);
        dest.writeInt(id);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeDouble(voteAverage);
        dest.writeString(title);
        dest.writeDouble(popularity);
        dest.writeString(posterPath);
        dest.writeString(originalLanguage);
        dest.writeString(originalTitle);
        dest.writeString(backdropPath);
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(overview);
        dest.writeString(releaseDate);
    }
}

