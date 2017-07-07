package org.brianodisho.vfmoviefinder.discover;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import org.brianodisho.vfmoviefinder.MainRouter;
import org.brianodisho.vfmoviefinder.discover.DiscoverContract.DiscoverPresenter;
import org.brianodisho.vfmoviefinder.discover.DiscoverContract.DiscoverView;
import org.brianodisho.vfmoviefinder.model.Movie;
import org.brianodisho.vfmoviefinder.model.MovieResponse;
import org.brianodisho.vfmoviefinder.model.source.MovieApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of the DiscoverPresenter
 */

public class DiscoverPresenterImpl extends MvpBasePresenter<DiscoverView> implements DiscoverPresenter {

    private final MainRouter router;
    private Call<MovieResponse> discoverCall;

    @Inject
    MovieApi movieApi;


    DiscoverPresenterImpl(MainRouter router) {
        this.router = router;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (discoverCall != null) {
            discoverCall.cancel();
            discoverCall = null;
        }
    }

    @Override
    public void loadData(final boolean pullToRefresh) {
        if (getView() != null) {
            getView().showLoading(pullToRefresh);
        }

        discoverCall = movieApi.discoverMovies();
        discoverCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    if (movieResponse != null) {
                        if (getView() != null) {
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
