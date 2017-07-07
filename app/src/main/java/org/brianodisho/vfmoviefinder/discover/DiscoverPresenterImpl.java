package org.brianodisho.vfmoviefinder.discover;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import org.brianodisho.vfmoviefinder.MainRouter;
import org.brianodisho.vfmoviefinder.discover.DiscoverContract.DiscoverPresenter;
import org.brianodisho.vfmoviefinder.discover.DiscoverContract.DiscoverView;
import org.brianodisho.vfmoviefinder.model.DiscoverResponse;
import org.brianodisho.vfmoviefinder.model.DiscoverResponse.Movie;
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
    private Call<DiscoverResponse> discoverCall;

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

        discoverCall = movieApi.discoverMovies(null, null);
        discoverCall.enqueue(new Callback<DiscoverResponse>() {
            @Override
            public void onResponse(Call<DiscoverResponse> call, Response<DiscoverResponse> response) {
                if (response.isSuccessful()) {
                    DiscoverResponse discoverResponse = response.body();
                    if (discoverResponse != null) {
                        if (getView() != null) {
                            getView().setData(discoverResponse.getMovies());
                            getView().showContent();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DiscoverResponse> call, Throwable t) {
                if (getView() != null) {
                    getView().showError(t, pullToRefresh);
                }
            }
        });
    }

    @Override
    public void onMovieClicked(Movie movie) {
        router.showMovieView(movie.getId());
    }
}
