package org.brianodisho.vfmoviefinder.intheaters;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import org.brianodisho.vfmoviefinder.MainRouter;
import org.brianodisho.vfmoviefinder.intheaters.InTheatersContract.InTheatersPresenter;
import org.brianodisho.vfmoviefinder.intheaters.InTheatersContract.InTheatersView;
import org.brianodisho.vfmoviefinder.model.Movie;
import org.brianodisho.vfmoviefinder.model.MovieResponse;
import org.brianodisho.vfmoviefinder.model.source.MovieApi;
import org.brianodisho.vfmoviefinder.util.Formatter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Implementation of the InTheatersPresenter
 */

public class InTheatersPresenterImpl extends MvpBasePresenter<InTheatersView> implements InTheatersPresenter {

    private final MainRouter router;
    private Call<MovieResponse> inTheatersCall;

    @Inject
    MovieApi movieApi;


    InTheatersPresenterImpl(MainRouter router) {
        this.router = router;
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (inTheatersCall != null) {
            inTheatersCall.cancel();
            inTheatersCall = null;
        }
    }

    @Override
    public void loadData(final boolean pullToRefresh) {
        if (getView() != null) {
            getView().showLoading(pullToRefresh);
        }

        String startDate = Formatter.fromUnixTimestampToDate(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(14));
        String todaysDate = Formatter.fromUnixTimestampToDate(System.currentTimeMillis());

        inTheatersCall = movieApi.discoverMovies(startDate, todaysDate);
        inTheatersCall.enqueue(new Callback<MovieResponse>() {
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
