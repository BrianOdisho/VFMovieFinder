package org.brianodisho.vfmoviefinder;

import org.brianodisho.vfmoviefinder.model.Movie;

/**
 * Defines a view router for the main view.
 */

public interface MainRouter {
    void showInTheatersView();
    void showDiscoverView();
    void showSearchView();
    void showSearchResultsView(String searchQuery);
    void showMovieView(Movie movie);
}
