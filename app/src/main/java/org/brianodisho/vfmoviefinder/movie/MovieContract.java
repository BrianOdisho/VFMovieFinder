package org.brianodisho.vfmoviefinder.movie;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import org.brianodisho.vfmoviefinder.model.MovieResponse.Movie;

/**
 * Defines the contract between the MovieView and MoviePresenter.
 */

interface MovieContract {

    interface MovieView extends MvpView {
        void setBackdropImage(String url);
        void setTitle(String title);
        void setStarRating(double starRating, int votes);
        void setOverview(String overview);
        void showShareDialog(Movie movie);
    }

    interface MoviePresenter extends MvpPresenter<MovieView> {
        void onViewReady();
        void onShareClicked();
    }
}
