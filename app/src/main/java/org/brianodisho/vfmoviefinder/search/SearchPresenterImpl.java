package org.brianodisho.vfmoviefinder.search;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import org.brianodisho.vfmoviefinder.MainRouter;
import org.brianodisho.vfmoviefinder.model.source.MovieApi;
import org.brianodisho.vfmoviefinder.search.SearchContract.SearchView;

import javax.inject.Inject;

/**
 * Implementation of the SearchPresenter
 */

public class SearchPresenterImpl extends MvpBasePresenter<SearchView> implements SearchContract.SearchPresenter {

    private final MainRouter router;

    @Inject
    MovieApi movieApi;


    SearchPresenterImpl(MainRouter router) {
        this.router = router;
    }

    @Override
    public void onSearch(String searchQuery) {
        if (searchQuery.isEmpty()) {
            if (getView() != null) {
                getView().showSearchQueryRequired();
            }
        } else {
            router.showSearchResultsView(searchQuery);
        }
    }
}
