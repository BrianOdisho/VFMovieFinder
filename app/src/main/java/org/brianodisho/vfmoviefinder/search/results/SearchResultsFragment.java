package org.brianodisho.vfmoviefinder.search.results;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.lce.MvpLceFragment;

import org.brianodisho.vfmoviefinder.MainRouter;
import org.brianodisho.vfmoviefinder.R;
import org.brianodisho.vfmoviefinder.VFMovieApplication;
import org.brianodisho.vfmoviefinder.model.MovieResponse.Movie;
import org.brianodisho.vfmoviefinder.search.results.SearchResultsContract.SearchResultsPresenter;
import org.brianodisho.vfmoviefinder.search.results.SearchResultsContract.SearchResultsView;

import java.util.List;


/**
 * Implementation of the DiscoverView
 */

public class SearchResultsFragment extends MvpLceFragment<RecyclerView, List<Movie>, SearchResultsView, SearchResultsPresenter>
        implements SearchResultsContract.SearchResultsView, SearchResultsMovieAdapter.MovieHolder.OnMovieClickListener {

    private static final String EXTRA_SEARCH_QUERY = "EXTRA_SEARCH_QUERY";

    private SearchResultsMovieAdapter adapter;
    private TextView resultCount;


    public static SearchResultsFragment newInstance(String searchQuery) {
        Bundle args = new Bundle();
        args.putString(EXTRA_SEARCH_QUERY, searchQuery);
        SearchResultsFragment fragment = new SearchResultsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_results, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new SearchResultsMovieAdapter(getContext(), this);
        ((VFMovieApplication) getActivity().getApplication()).getApplicationComponent().inject(adapter);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        resultCount = (TextView) view.findViewById(R.id.text_search_result_count);

        loadData(false);
    }

    @NonNull
    @Override
    public SearchResultsPresenter createPresenter() {
        SearchResultsPresenterImpl presenter = new SearchResultsPresenterImpl((MainRouter) getActivity());
        ((VFMovieApplication) getActivity().getApplication()).getApplicationComponent().inject(presenter);
        return presenter;
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.searchMovies(pullToRefresh, getArguments().getString(EXTRA_SEARCH_QUERY));
    }

    @Override
    public void setData(@NonNull List<Movie> data) {
        adapter.setData(data);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return getString(R.string.error_searching);
    }

    @Override
    public void showContent() {
        super.showContent();
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
    }

    @Override
    public void onMovieClick(int position) {
        presenter.onMovieClicked(adapter.getItem(position));
    }

    @Override
    public void setResultCount(int resultCount) {
        this.resultCount.setText(getString(R.string.search_results_count, resultCount));
    }
}
