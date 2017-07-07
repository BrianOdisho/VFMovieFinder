package org.brianodisho.vfmoviefinder.discover;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

import org.brianodisho.vfmoviefinder.model.DiscoverResponse;

import java.util.List;

/**
 * Defines the contract between the DiscoverView and DiscoverPresenter.
 */

interface DiscoverContract {

    interface DiscoverView extends MvpLceView<List<DiscoverResponse.Movie>> {
    }

    interface DiscoverPresenter extends MvpPresenter<DiscoverView> {
        void loadData(boolean pullToRefresh);
        void onMovieClicked(DiscoverResponse.Movie movie);
        void onShareClicked(DiscoverResponse.Movie movie);
    }
}
