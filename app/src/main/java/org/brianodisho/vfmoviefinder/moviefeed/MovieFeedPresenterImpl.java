package org.brianodisho.vfmoviefinder.moviefeed;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import org.brianodisho.vfmoviefinder.model.DiscoverMoviesResponse;
import org.brianodisho.vfmoviefinder.model.source.remote.MovieApi;
import org.brianodisho.vfmoviefinder.moviefeed.MovieFeedContract.MovieFeedPresenter;
import org.brianodisho.vfmoviefinder.moviefeed.MovieFeedContract.MovieFeedView;

import java.util.ArrayList;

import java.util.List;

import javax.inject.Inject;


public class MovieFeedPresenterImpl extends MvpBasePresenter<MovieFeedView> implements MovieFeedPresenter {

    private final String movieFeed;

    private List<DiscoverMoviesResponse.Movie> data;

    @Inject
    MovieApi movieApi;


    MovieFeedPresenterImpl(String movieFeed) {
        this.movieFeed = movieFeed;
    }

    @Override
    public void loadData(final boolean pullToRefresh) {
        if (getView() != null) {
            getView().showLoading(pullToRefresh);
        }

        if (data == null) {
            data = new ArrayList<>();
        } else if (!data.isEmpty()) {
            data.clear();
        }

    }

    @Override
    public void onMovieClicked(DiscoverMoviesResponse.Movie movie) {

    }

    @Override
    public void onShareClicked(DiscoverMoviesResponse.Movie movie) {

    }
}
