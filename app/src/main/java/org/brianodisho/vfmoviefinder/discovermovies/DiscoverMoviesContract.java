package org.brianodisho.vfmoviefinder.discovermovies;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * This specifies the contract between the view and the presenter.
 */

interface DiscoverMoviesContract {

    interface DiscoverMoviesView extends MvpView {
    }

    interface DiscoverMoviesPresenter extends MvpPresenter<DiscoverMoviesView> {
    }
}
