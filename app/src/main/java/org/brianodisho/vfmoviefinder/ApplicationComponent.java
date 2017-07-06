package org.brianodisho.vfmoviefinder;

import org.brianodisho.vfmoviefinder.dagger.NetworkModule;
import org.brianodisho.vfmoviefinder.moviefeed.MovieFeedAdapter;
import org.brianodisho.vfmoviefinder.moviefeed.MovieFeedPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    void inject(MovieFeedPresenterImpl movieFeedPresenterImpl);
    void inject(MovieFeedAdapter movieFeedAdapter);
}
