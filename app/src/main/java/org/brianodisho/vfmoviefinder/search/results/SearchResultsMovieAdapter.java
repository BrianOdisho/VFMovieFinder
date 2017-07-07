package org.brianodisho.vfmoviefinder.search.results;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.brianodisho.vfmoviefinder.R;
import org.brianodisho.vfmoviefinder.model.MovieResponse.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * RecyclerView adapter for MovieHolder.
 */

public class SearchResultsMovieAdapter extends RecyclerView.Adapter<SearchResultsMovieAdapter.MovieHolder> {

    private final LayoutInflater inflater;
    private final Context context;
    private final MovieHolder.OnMovieClickListener listener;

    private List<Movie> data;

    @Inject
    Picasso picasso;


    SearchResultsMovieAdapter(@NonNull Context context, MovieHolder.OnMovieClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieHolder(inflater.inflate(R.layout.item_discover_movie, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        Movie movie = data.get(position);

        picasso.load(movie.getPosterPathFull()).into(holder.image);
        holder.textTitle.setText(movie.getTitle());
        holder.textStarRating.setText(context.getString(R.string.discovery_movie_star_rating, movie.getVoteAverage()));
        holder.textOverview.setText(movie.getOverview());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public void setData(List<Movie> newsFeedArticles) {
        if (data == null) {
            data = new ArrayList<>();
        }
        if (!data.isEmpty()) {
            data.clear();
        }
        data.addAll(newsFeedArticles);
        notifyDataSetChanged();
    }


    Movie getItem(int position) {
        if (position < 0 || data == null || position >= data.size()) {
            throw new IllegalArgumentException();
        }
        return data.get(position);
    }


    static class MovieHolder extends RecyclerView.ViewHolder {

        interface OnMovieClickListener {
            void onMovieClick(int position);
        }


        final ImageView image;
        final TextView textTitle, textStarRating, textOverview;


        MovieHolder(View itemView, @NonNull final OnMovieClickListener onMovieClickListener) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image_item_discover_movie_poster);
            textTitle = (TextView) itemView.findViewById(R.id.text_item_discover_movie_title);
            textStarRating = (TextView) itemView.findViewById(R.id.text_item_discover_movie_starRating);
            textOverview = (TextView) itemView.findViewById(R.id.text_item_discover_movie_overview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMovieClickListener.onMovieClick(getLayoutPosition());
                }
            });
        }
    }
}
