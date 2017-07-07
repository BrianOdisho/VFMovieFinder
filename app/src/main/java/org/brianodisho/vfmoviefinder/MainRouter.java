package org.brianodisho.vfmoviefinder;

/**
 * Defines a view router for the main view.
 */

public interface MainRouter {
    void showInTheatersView();
    void showDiscoverView();
    void showSearchView();
    void showMovieView(int movieId);
}
