package org.brianodisho.vfmoviefinder.search.results;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.lce.MvpLceView;

import org.brianodisho.vfmoviefinder.model.Movie;

import java.util.List;

/**
 * Defines the contract between the SearchResultsView and SearchResultsPresenter.
 */

interface SearchResultsContract {

    interface SearchResultsView extends MvpLceView<List<Movie>> {
        void setResultCount(int resultCount);
    }

    interface SearchResultsPresenter extends MvpPresenter<SearchResultsView> {
        void searchMovies(boolean pullToRefresh, String searchQuery);

        void onMovieClicked(Movie movie);
    }
}
