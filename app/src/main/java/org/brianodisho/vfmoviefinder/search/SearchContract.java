package org.brianodisho.vfmoviefinder.search;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Defines the contract between the SearchView and SearchPresenter.
 */

interface SearchContract {

    interface SearchView extends MvpView {
        void showSearchQueryRequired();
    }

    interface SearchPresenter extends MvpPresenter<SearchView> {
        void onSearch(String searchQuery);
    }
}
