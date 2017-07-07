package org.brianodisho.vfmoviefinder.discover;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;

import org.brianodisho.vfmoviefinder.R;
import org.brianodisho.vfmoviefinder.CustomApplication;
import org.brianodisho.vfmoviefinder.discover.DiscoverContract.DiscoverPresenter;
import org.brianodisho.vfmoviefinder.discover.DiscoverContract.DiscoverView;
import org.brianodisho.vfmoviefinder.model.DiscoverResponse.Movie;

import java.util.List;


/**
 * Implementation of the DiscoverView
 */

public class DiscoverFragment extends MvpLceFragment<SwipeRefreshLayout, List<Movie>, DiscoverView, DiscoverPresenter>
        implements DiscoverView, DiscoverMovieAdapter.MovieHolder.OnMovieClickListener, SwipeRefreshLayout.OnRefreshListener {

    private DiscoverMovieAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contentView.setOnRefreshListener(this);

        adapter = new DiscoverMovieAdapter(getContext(), this);
        ((CustomApplication) getActivity().getApplication()).getApplicationComponent().inject(adapter);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        loadData(false);
    }

    @NonNull
    @Override
    public DiscoverPresenter createPresenter() {
        DiscoverPresenterImpl presenter = new DiscoverPresenterImpl();
        ((CustomApplication) getActivity().getApplication()).getApplicationComponent().inject(presenter);
        return presenter;
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(pullToRefresh);
    }

    @Override
    public void setData(@NonNull List<Movie> data) {
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

    @Override
    public void onMovieClick(int position) {
        presenter.onMovieClicked(adapter.getItem(position));
    }

    @Override
    public void onShareClick(int position) {
        presenter.onShareClicked(adapter.getItem(position));
    }
}
