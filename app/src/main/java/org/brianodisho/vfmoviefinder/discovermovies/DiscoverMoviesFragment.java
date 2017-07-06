package org.brianodisho.vfmoviefinder.discovermovies;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import org.brianodisho.vfmoviefinder.R;
import org.brianodisho.vfmoviefinder.discovermovies.DiscoverMoviesContract.DiscoverMoviesPresenter;
import org.brianodisho.vfmoviefinder.discovermovies.DiscoverMoviesContract.DiscoverMoviesView;
import org.brianodisho.vfmoviefinder.moviefeed.MovieFeedFragment;


/**
 * Implementation of the DiscoverMoviesView
 */

public class DiscoverMoviesFragment extends MvpFragment<DiscoverMoviesView, DiscoverMoviesPresenter> implements DiscoverMoviesView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover_movies, container, false);

        String[] tabArray = {"Now Playing", "Coming Soon"};

        DiscoverMoviesPagerAdapter pagerAdapter = new DiscoverMoviesPagerAdapter(getChildFragmentManager(), tabArray);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager_discover_movies);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_discover_movies);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @NonNull
    @Override
    public DiscoverMoviesPresenter createPresenter() {
        return new DiscoverMoviesPresenterImpl();
    }


    private class DiscoverMoviesPagerAdapter extends FragmentStatePagerAdapter {

        private final String[] _data;


        DiscoverMoviesPagerAdapter(FragmentManager fragmentManager, String[] data) {
            super(fragmentManager);
            _data = data;
        }

        @Override
        public Fragment getItem(int position) {
            return MovieFeedFragment.newInstance(_data[position]);
        }

        @Override
        public int getCount() {
            return _data.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return _data[position];
        }
    }
}
