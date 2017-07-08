package org.brianodisho.vfmoviefinder.model.source;

import org.brianodisho.vfmoviefinder.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Defines the Retrofit interface for the MovieApi.
 */

public interface MovieApi {

    String BASE_URL = "https://api.themoviedb.org/3/";
    String DISCOVER_MOVIES_US_ENDPOINT = "discover/movie?language=en-US&region=US&vote_average.gte=1&vote_count.gte=10";
    String SEARCH_MOVIES_US_ENDPOINT = "search/movie?language=en-US&region=US&include_adult=false";
    String API_KEY = "194dac02fb685b5e765287119c58d37f";


    @GET(DISCOVER_MOVIES_US_ENDPOINT)
    Call<MovieResponse> discoverMovies(@Query("primary_release_date.gte") String primaryReleaseDateGreaterThan,
                                       @Query("primary_release_date.lte") String primaryReleaseDateLessThan);

    @GET(SEARCH_MOVIES_US_ENDPOINT)
    Call<MovieResponse> search(@Query("query") String searchQuery);
}
