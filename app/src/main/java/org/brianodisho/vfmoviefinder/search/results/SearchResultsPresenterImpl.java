package org.brianodisho.vfmoviefinder.search.results;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import org.brianodisho.vfmoviefinder.MainRouter;
import org.brianodisho.vfmoviefinder.model.MovieResponse;
import org.brianodisho.vfmoviefinder.model.MovieResponse.Movie;
import org.brianodisho.vfmoviefinder.model.source.MovieApi;
import org.brianodisho.vfmoviefinder.search.results.SearchResultsContract.SearchResultsPresenter;
import org.brianodisho.vfmoviefinder.search.results.SearchResultsContract.SearchResultsView;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of the SearchResultsPresenter
 */

public class SearchResultsPresenterImpl extends MvpBasePresenter<SearchResultsView> implements SearchResultsPresenter {

    private final MainRouter router;
    private Call<MovieResponse> searchCall;

    @Inject
    MovieApi movieApi;


    SearchResultsPresenterImpl(MainRouter router) {
        this.router = router;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (searchCall != null) {
            searchCall.cancel();
            searchCall = null;
        }
    }

    @Override
    public void searchMovies(final boolean pullToRefresh, String searchQuery) {
        if (getView() != null) {
            getView().showLoading(pullToRefresh);
        }

        searchCall = movieApi.search(searchQuery);
        searchCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    if (movieResponse != null) {
                        if (getView() != null) {
                            getView().setResultCount(movieResponse.getMovies().size());
                            getView().setData(movieResponse.getMovies());
                            getView().showContent();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                if (getView() != null) {
                    getView().showError(t, pullToRefresh);
                }
            }
        });
    }

    @Override
    public void onMovieClicked(Movie movie) {
        router.showMovieView(movie);
    }
}
