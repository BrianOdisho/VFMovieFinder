package org.brianodisho.vfmoviefinder.intheaters;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

import org.brianodisho.vfmoviefinder.model.Movie;

import java.util.List;

/**
 * Defines the contract between the InTheatersView and InTheatersPresenter.
 */

interface InTheatersContract {

    interface InTheatersView extends MvpLceView<List<Movie>> {
    }

    interface InTheatersPresenter extends MvpPresenter<InTheatersView> {
        void loadData(boolean pullToRefresh);
        void onMovieClicked(Movie movie);
    }
}
