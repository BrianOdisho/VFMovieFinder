package org.brianodisho.vfmoviefinder.intheaters;

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

import org.brianodisho.vfmoviefinder.MainRouter;
import org.brianodisho.vfmoviefinder.R;
import org.brianodisho.vfmoviefinder.VFMovieApplication;
import org.brianodisho.vfmoviefinder.intheaters.InTheatersContract.InTheatersPresenter;
import org.brianodisho.vfmoviefinder.intheaters.InTheatersContract.InTheatersView;
import org.brianodisho.vfmoviefinder.model.Movie;

import java.util.List;

/**
 * Implementation of the InTheatersView
 */

public class InTheatersFragment extends MvpLceFragment<SwipeRefreshLayout, List<Movie>, InTheatersView, InTheatersPresenter>
        implements InTheatersView, InTheatersMovieAdapter.MovieHolder.OnMovieClickListener, SwipeRefreshLayout.OnRefreshListener {

    private InTheatersMovieAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lce, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contentView.setOnRefreshListener(this);

        adapter = new InTheatersMovieAdapter(getContext(), this);
        ((VFMovieApplication) getActivity().getApplication()).getApplicationComponent().inject(adapter);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        loadData(false);
    }

    @NonNull
    @Override
    public InTheatersPresenter createPresenter() {
        InTheatersPresenterImpl presenter = new InTheatersPresenterImpl((MainRouter) getActivity());
        ((VFMovieApplication) getActivity().getApplication()).getApplicationComponent().inject(presenter);
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
        if (pullToRefresh) {
            return getString(R.string.error_loading_retry);
        } else {
            return getString(R.string.error_loading);
        }
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
}