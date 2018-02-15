package org.brianodisho.vfmoviefinder.intheaters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.brianodisho.vfmoviefinder.R;
import org.brianodisho.vfmoviefinder.model.Movie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.wasabeef.picasso.transformations.BlurTransformation;

/**
 * RecyclerView adapter for MovieHolder.
 */

public class InTheatersMovieAdapter extends RecyclerView.Adapter<InTheatersMovieAdapter.MovieHolder> {

    private final LayoutInflater inflater;
    private final MovieHolder.OnMovieClickListener listener;

    private List<Movie> data;

    @Inject
    Picasso picasso;


    InTheatersMovieAdapter(@NonNull Context context, MovieHolder.OnMovieClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieHolder(inflater.inflate(R.layout.item_in_theaters, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        Movie movie = data.get(position);

        picasso.load(movie.getPosterPathFull())
                .transform(new BlurTransformation(holder.itemView.getContext(), 25, 5))
                .into(holder.imageBackground);

        picasso.load(movie.getPosterPathFull()).into(holder.image);
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


        final ImageView image, imageBackground;


        MovieHolder(View itemView, @NonNull final OnMovieClickListener onMovieClickListener) {
            super(itemView);
            image = itemView.findViewById(R.id.image_item_in_theaters);
            imageBackground = itemView.findViewById(R.id.image_item_in_theaters_background);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onMovieClickListener.onMovieClick(getLayoutPosition());
                }
            });
        }
    }
}
