package org.brianodisho.vfmoviefinder.moviefeed;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;

import org.brianodisho.vfmoviefinder.R;
import org.brianodisho.vfmoviefinder.VFMovieFinderApplication;
import org.brianodisho.vfmoviefinder.model.DiscoverMoviesResponse;
import org.brianodisho.vfmoviefinder.moviefeed.MovieFeedContract.MovieFeedPresenter;
import org.brianodisho.vfmoviefinder.moviefeed.MovieFeedContract.MovieFeedView;

import java.util.List;

public class MovieFeedFragment extends MvpLceFragment<SwipeRefreshLayout, List<DiscoverMoviesResponse.Movie>, MovieFeedView, MovieFeedPresenter>
        implements MovieFeedView, MovieFeedAdapter.NewsFeedArticleHolder.OnArticleClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String EXTRA_NEWS_FEED_CATEGORY = "EXTRA_NEWS_FEED_CATEGORY";

    private MovieFeedAdapter adapter;


    public static MovieFeedFragment newInstance(String newsFeedCategory) {
        MovieFeedFragment movieFeedFragment = new MovieFeedFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString(EXTRA_NEWS_FEED_CATEGORY, newsFeedCategory);
        movieFeedFragment.setArguments(bundle);
        return movieFeedFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_feed, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contentView.setOnRefreshListener(this);

        adapter = new MovieFeedAdapter(getContext(), this);
        ((VFMovieFinderApplication) getActivity().getApplication()).getApplicationComponent().inject(adapter);


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        loadData(false);
    }

    @NonNull
    @Override
    public MovieFeedPresenter createPresenter() {
        MovieFeedPresenterImpl presenter = new MovieFeedPresenterImpl(getArguments().getString(EXTRA_NEWS_FEED_CATEGORY));
        ((VFMovieFinderApplication) getActivity().getApplication()).getApplicationComponent().inject(presenter);
        return presenter;
    }

    @Override
    public void onArticleClick(int position) {
        presenter.onMovieClicked(adapter.getItem(position));
    }

    @Override
    public void onShareClick(int position) {
        presenter.onShareClicked(adapter.getItem(position));
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(pullToRefresh);
    }

    @Override
    public void setData(@NonNull List<DiscoverMoviesResponse.Movie> data) {
        adapter.setData(data);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
//        if (pullToRefresh) {
//            return getString(R.string.error_news_feed);
//        } else {
//            return getString(R.string.error_news_feed_retry);
//        }
        return e.getMessage();
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }
}
