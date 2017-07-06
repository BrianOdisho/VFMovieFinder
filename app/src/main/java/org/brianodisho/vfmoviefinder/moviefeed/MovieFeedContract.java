package org.brianodisho.vfmoviefinder.moviefeed;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

import org.brianodisho.vfmoviefinder.model.DiscoverMoviesResponse;

import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */

interface MovieFeedContract {

    interface MovieFeedView extends MvpLceView<List<DiscoverMoviesResponse.Movie>> {
    }

    interface MovieFeedPresenter extends MvpPresenter<MovieFeedView> {
        void loadData(boolean pullToRefresh);
        void onMovieClicked(DiscoverMoviesResponse.Movie movie);
        void onShareClicked(DiscoverMoviesResponse.Movie movie);
    }
}
