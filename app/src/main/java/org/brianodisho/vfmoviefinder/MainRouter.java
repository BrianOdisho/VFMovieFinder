package org.brianodisho.vfmoviefinder;

import org.brianodisho.vfmoviefinder.model.DiscoverResponse.Movie;

/**
 * Defines a view router for the main view.
 */

public interface MainRouter {
    void showInTheatersView();
    void showDiscoverView();
    void showSearchView();
    void showMovieView(Movie movie);
}
