package org.brianodisho.vfmoviefinder.model.source.remote;

import org.brianodisho.vfmoviefinder.model.DiscoverResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Defines the Retrofit interface for the MovieApi.
 */

public interface MovieApi {

    String BASE_URL = "https://api.themoviedb.org/3/";
    String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/";
    String IMAGE_BACKDROP_SIZE_SMALL = "w300";
    String IMAGE_BACKDROP_SIZE_MED = "w780";
    String IMAGE_BACKDROP_SIZE_LARGE = "w1280";
    String IMAGE_POSTER_SIZE_SMALL = "w500";
    String DISCOVER_MOVIES_US_ENDPOINT = "discover/movie?language=en-US&region=US&certification_country=US";
    String API_KEY = "194dac02fb685b5e765287119c58d37f";

    @GET(DISCOVER_MOVIES_US_ENDPOINT)
    Call<DiscoverResponse> discoverMovies(@Query("sort_by") String sortBy, @Query("certification") String certification);
}
