package org.brianodisho.vfmoviefinder.movie;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.squareup.picasso.Picasso;

import org.brianodisho.vfmoviefinder.VFMovieApplication;
import org.brianodisho.vfmoviefinder.R;
import org.brianodisho.vfmoviefinder.model.MovieResponse.Movie;
import org.brianodisho.vfmoviefinder.movie.MovieContract.MoviePresenter;
import org.brianodisho.vfmoviefinder.movie.MovieContract.MovieView;

import javax.inject.Inject;

/**
 * Implementation of the MovieView
 */

public class MovieActivity extends MvpActivity<MovieView, MoviePresenter> implements MovieView {

    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";

    private ImageView backdrop;
    private TextView title, starRating, overview;

    @Inject
    Picasso picasso;


    public static void start(Context context, Movie movie) {
        Intent starter = new Intent(context, MovieActivity.class);
        starter.putExtra(EXTRA_MOVIE, movie);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((VFMovieApplication) getApplication()).getApplicationComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        backdrop = (ImageView) findViewById(R.id.image_movie_backdrop);
        title = (TextView) findViewById(R.id.text_movie_title);
        starRating = (TextView) findViewById(R.id.text_movie_starRating);
        overview = (TextView) findViewById(R.id.text_movie_overview);

        presenter.onViewReady();
    }

    @NonNull
    @Override
    public MoviePresenter createPresenter() {
        Movie movie = getIntent().getExtras().getParcelable(EXTRA_MOVIE);
        return new MoviePresenterImpl(movie);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_movie_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_share:
                presenter.onShareClicked();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setBackdropImage(String url) {
        picasso.load(url).into(backdrop);
    }

    @Override
    public void setTitle(String title) {
        this.title.setText(title);
    }

    @Override
    public void setStarRating(double starRating, int votes) {
        this.starRating.setText(getString(R.string.movie_star_rating, starRating, votes));
    }

    @Override
    public void setOverview(String overview) {
        this.overview.setText(overview);
    }

    @Override
    public void showShareDialog(Movie movie) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, movie.getTitle());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Share Article"));
    }
}
