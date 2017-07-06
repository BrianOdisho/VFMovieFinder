package org.brianodisho.vfmoviefinder.model.source.remote;

import org.brianodisho.vfmoviefinder.model.DiscoverMoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Interface for
 */

public interface MovieApi {

    String BASE_URL = "https://api.themoviedb.org/3/";
    String DISCOVER_MOVIES_US_ENDPOINT = "discover/movie?language=en-US&region=US&certification_country=US";
    String API_KEY = "194dac02fb685b5e765287119c58d37f";

    @GET(DISCOVER_MOVIES_US_ENDPOINT)
    Call<DiscoverMoviesResponse> discoverMovies(@Query("sort_by") String sortBy, @Query("certification") String certification);
}
