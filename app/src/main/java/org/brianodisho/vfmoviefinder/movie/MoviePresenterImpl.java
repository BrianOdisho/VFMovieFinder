package org.brianodisho.vfmoviefinder.movie;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import org.brianodisho.vfmoviefinder.model.MovieResponse.Movie;
import org.brianodisho.vfmoviefinder.movie.MovieContract.MoviePresenter;
import org.brianodisho.vfmoviefinder.movie.MovieContract.MovieView;

/**
 * Implementation of the MoviePresenter
 */

class MoviePresenterImpl extends MvpBasePresenter<MovieView> implements MoviePresenter {

    private final Movie movie;


    MoviePresenterImpl(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void onViewReady() {
        if (getView() != null) {
            if (movie.getBackdropPathFull() != null) {
                getView().setBackdropImage(movie.getBackdropPathFull());
            } else {
                getView().setBackdropImage(movie.getPosterPathFull());
            }
            getView().setTitle(movie.getTitle());
            getView().setStarRating(movie.getVoteAverage(), movie.getVoteCount());
            getView().setOverview(movie.getOverview());
        }
    }

    @Override
    public void onShareClicked() {
        if (getView() != null) {
            getView().showShareDialog(movie);
        }
    }
}
