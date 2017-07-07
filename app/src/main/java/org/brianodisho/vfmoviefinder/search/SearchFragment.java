package org.brianodisho.vfmoviefinder.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import org.brianodisho.vfmoviefinder.MainRouter;
import org.brianodisho.vfmoviefinder.VFMovieApplication;
import org.brianodisho.vfmoviefinder.R;
import org.brianodisho.vfmoviefinder.search.SearchContract.SearchPresenter;
import org.brianodisho.vfmoviefinder.search.SearchContract.SearchView;
import org.brianodisho.vfmoviefinder.util.TextInputLayoutHelper;

/**
 * Implementation of the SearchPresenter
 */

public class SearchFragment extends MvpFragment<SearchView, SearchPresenter> implements SearchView {

    private EditText searchField;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchField = (EditText) view.findViewById(R.id.editText_search);
        searchField.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    presenter.onSearch(v.getText().toString());
                    searchField.clearFocus();
                    hideKeyboard();
                    return true;
                }
                return false;
            }
        });
        searchField.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    presenter.onSearch(searchField.getText().toString());
                    searchField.clearFocus();
                    hideKeyboard();
                    return true;
                }
                return false;
            }
        });

        searchField.requestFocus();
        showKeyboard();

        return view;
    }

    @NonNull
    @Override
    public SearchPresenter createPresenter() {
        SearchPresenterImpl presenter = new SearchPresenterImpl((MainRouter) getActivity());
        ((VFMovieApplication) getActivity().getApplication()).getApplicationComponent().inject(presenter);
        return presenter;
    }

    @Override
    public void showSearchQueryRequired() {
        TextInputLayoutHelper.setError(searchField, getString(R.string.search_movies_required));
        searchField.requestFocus();
        showKeyboard();
    }


    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchField.getWindowToken(), 0);
    }


    private void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }
}
