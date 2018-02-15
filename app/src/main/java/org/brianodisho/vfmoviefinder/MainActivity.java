package org.brianodisho.vfmoviefinder;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import org.brianodisho.vfmoviefinder.MainContract.MainPresenter;
import org.brianodisho.vfmoviefinder.MainContract.MainView;
import org.brianodisho.vfmoviefinder.discover.DiscoverFragment;
import org.brianodisho.vfmoviefinder.intheaters.InTheatersFragment;
import org.brianodisho.vfmoviefinder.model.Movie;
import org.brianodisho.vfmoviefinder.movie.MovieActivity;
import org.brianodisho.vfmoviefinder.search.SearchFragment;
import org.brianodisho.vfmoviefinder.search.results.SearchResultsFragment;

/**
 * Implementation of the MainView
 */

public class MainActivity extends MvpActivity<MainView, MainPresenter>
        implements MainView, MainRouter, OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation_main);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.bottom_navigation_discover);

        presenter.onViewReady();
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        return new MainPresenterImpl(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bottom_navigation_in_theaters:
                presenter.onInTheatersSelected();
                break;
            case R.id.bottom_navigation_discover:
                presenter.onDiscoverSelected();
                break;
            case R.id.bottom_navigation_search:
                presenter.onSearchSelected();
                break;
        }
        return true;
    }


    //region MainRouter
    @Override
    public void showInTheatersView() {
        showContentFragment(new InTheatersFragment(), false);
        setActionBarTitle(R.string.bottom_navigation_in_theaters);
    }

    @Override
    public void showDiscoverView() {
        showContentFragment(new DiscoverFragment(), false);
        setActionBarTitle(R.string.bottom_navigation_discover);
    }

    @Override
    public void showSearchView() {
        showContentFragment(new SearchFragment(), false);
        setActionBarTitle(R.string.bottom_navigation_search);
    }

    @Override
    public void showSearchResultsView(String searchQuery) {
        showContentFragment(SearchResultsFragment.newInstance(searchQuery), true);
        setActionBarTitle(R.string.search_results);
    }

    @Override
    public void showMovieView(Movie movie) {
        MovieActivity.start(this, movie);
    }
    //endregion


    private void setActionBarTitle(@StringRes int resId) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(resId);
        }
    }


    private void showContentFragment(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main, fragment);

        if (addToBackstack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }
}
