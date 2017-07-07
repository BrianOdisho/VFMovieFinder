package org.brianodisho.vfmoviefinder;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Defines the contract between the MainView and MainPresenter.
 */

interface MainContract {

    interface MainView extends MvpView {
    }

    interface MainPresenter extends MvpPresenter<MainView> {
        void onViewReady();
        void onInTheatersSelected();
        void onDiscoverSelected();
        void onSearchSelected();
    }
}
