package org.brianodisho.vfmoviefinder.movie;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import org.brianodisho.vfmoviefinder.R;

public class MovieActivity extends AppCompatActivity {

    private static final String KEY_MOVIE_ID = "KEY_MOVIE_ID";


    public static void start(Context context, int movieId) {
        Intent starter = new Intent(context, MovieActivity.class);
        starter.putExtra(KEY_MOVIE_ID, movieId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
