package org.brianodisho.vfmoviefinder.dagger;

import android.app.Application;

import com.squareup.picasso.Picasso;

import org.brianodisho.vfmoviefinder.model.source.MovieApi;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger module that is used to pass network dependencies to components.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Picasso providePicasso(Application application) {
        return new Picasso.Builder(application)
                .build();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request interceptedRequest = chain.request();

                        HttpUrl newHttpUrl = interceptedRequest.url().newBuilder()
                                .addQueryParameter("api_key", MovieApi.API_KEY)
                                .build();

                        Request request = interceptedRequest.newBuilder()
                                .url(newHttpUrl)
                                .build();

                        return chain.proceed(request);
                    }
                })
                .build();
    }

    @Provides
    @Singleton
    MovieApi provideMovieApi(GsonConverterFactory converterFactory, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(converterFactory)
                .baseUrl(MovieApi.BASE_URL)
                .client(okHttpClient)
                .build()
                .create(MovieApi.class);
    }
}
