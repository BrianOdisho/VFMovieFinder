package org.brianodisho.vfmoviefinder;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import org.brianodisho.vfmoviefinder.MainContract.MainPresenter;
import org.brianodisho.vfmoviefinder.MainContract.MainView;

/**
 * Implementation of the MainPresenter
 */

class MainPresenterImpl extends MvpBasePresenter<MainView> implements MainPresenter {

    private final MainRouter router;


    MainPresenterImpl(MainRouter router) {
        this.router = router;
    }

    @Override
    public void onViewReady() {
        router.showDiscoverView();
    }

    @Override
    public void onInTheatersSelected() {
        router.showInTheatersView();
    }

    @Override
    public void onDiscoverSelected() {
        router.showDiscoverView();
    }

    @Override
    public void onSearchSelected() {
        router.showSearchView();
    }
}
