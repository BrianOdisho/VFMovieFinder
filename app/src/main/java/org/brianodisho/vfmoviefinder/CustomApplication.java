package org.brianodisho.vfmoviefinder;

import android.app.Application;

import org.brianodisho.vfmoviefinder.dagger.NetworkModule;

public class CustomApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule())
                .build();
    }


    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
