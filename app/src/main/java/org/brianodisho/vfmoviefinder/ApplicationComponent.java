package org.brianodisho.vfmoviefinder;

import org.brianodisho.vfmoviefinder.dagger.NetworkModule;
import org.brianodisho.vfmoviefinder.discover.DiscoverMovieAdapter;
import org.brianodisho.vfmoviefinder.discover.DiscoverPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    void inject(DiscoverPresenterImpl discoverPresenterImpl);
    void inject(DiscoverMovieAdapter discoverMovieAdapter);
}
