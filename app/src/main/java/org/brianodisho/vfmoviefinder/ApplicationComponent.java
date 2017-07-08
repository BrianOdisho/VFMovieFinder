package org.brianodisho.vfmoviefinder;

import org.brianodisho.vfmoviefinder.dagger.NetworkModule;
import org.brianodisho.vfmoviefinder.discover.DiscoverMovieAdapter;
import org.brianodisho.vfmoviefinder.discover.DiscoverPresenterImpl;
import org.brianodisho.vfmoviefinder.intheaters.InTheatersMovieAdapter;
import org.brianodisho.vfmoviefinder.intheaters.InTheatersPresenterImpl;
import org.brianodisho.vfmoviefinder.movie.MovieActivity;
import org.brianodisho.vfmoviefinder.search.SearchPresenterImpl;
import org.brianodisho.vfmoviefinder.search.results.SearchResultsMovieAdapter;
import org.brianodisho.vfmoviefinder.search.results.SearchResultsPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    void inject(InTheatersPresenterImpl presenter);
    void inject(InTheatersMovieAdapter adapter);
    void inject(DiscoverPresenterImpl presenter);
    void inject(DiscoverMovieAdapter adapter);
    void inject(MovieActivity activity);
    void inject(SearchPresenterImpl presenter);
    void inject(SearchResultsPresenterImpl presenter);
    void inject(SearchResultsMovieAdapter adapter);
}
