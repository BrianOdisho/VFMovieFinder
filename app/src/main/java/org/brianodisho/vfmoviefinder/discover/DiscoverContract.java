package org.brianodisho.vfmoviefinder.discover;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

import org.brianodisho.vfmoviefinder.model.MovieResponse.Movie;

import java.util.List;

/**
 * Defines the contract between the DiscoverView and DiscoverPresenter.
 */

interface DiscoverContract {

    interface DiscoverView extends MvpLceView<List<Movie>> {
    }

    interface DiscoverPresenter extends MvpPresenter<DiscoverView> {
        void loadData(boolean pullToRefresh);
        void onMovieClicked(Movie movie);
    }
}
