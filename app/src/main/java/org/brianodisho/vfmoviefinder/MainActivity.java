package org.brianodisho.vfmoviefinder;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import org.brianodisho.vfmoviefinder.MainContract.MainPresenter;
import org.brianodisho.vfmoviefinder.MainContract.MainView;
import org.brianodisho.vfmoviefinder.discover.DiscoverFragment;


/**
 * Implementation of the MainView
 */

public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView, MainRouter, BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation_main);
        navigationView.setOnNavigationItemSelectedListener(this);
        navigationView.setSelectedItemId(R.id.bottom_navigation_discover);

//        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigationView.getChildAt(0);
//        for (int index = 0, count = menuView.getChildCount(); index < count; index++) {
//            BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(index);
//
//            //noinspection RestrictedApi
//            itemView.setShiftingMode(false);
//            //noinspection RestrictedApi
//            itemView.setChecked(false);
//        }

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

    @Override
    public void showInTheatersView() {
        //        showContentFragment(new InTheatersFragment());
    }

    @Override
    public void showDiscoverView() {
        showContentFragment(new DiscoverFragment());
    }

    @Override
    public void showSearchView() {
//        showContentFragment(new SearchFragment());
    }


    private void showContentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();
    }
}
