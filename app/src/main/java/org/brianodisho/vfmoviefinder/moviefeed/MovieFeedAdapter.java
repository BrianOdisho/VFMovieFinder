package org.brianodisho.vfmoviefinder.moviefeed;

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
import org.brianodisho.vfmoviefinder.model.DiscoverMoviesResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class MovieFeedAdapter extends RecyclerView.Adapter<MovieFeedAdapter.NewsFeedArticleHolder> {

    private final LayoutInflater inflater;
    private final NewsFeedArticleHolder.OnArticleClickListener listener;

    private List<DiscoverMoviesResponse.Movie> data;

    @Inject
    Picasso picasso;


    MovieFeedAdapter(@NonNull Context context, NewsFeedArticleHolder.OnArticleClickListener articleClickListener) {
        inflater = LayoutInflater.from(context);
        listener = articleClickListener;
    }

    @Override
    public NewsFeedArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsFeedArticleHolder(inflater.inflate(R.layout.item_movie_feed, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(NewsFeedArticleHolder holder, int position) {
        DiscoverMoviesResponse.Movie movie = data.get(position);
        picasso.load(movie.getBackdropPath()).into(holder.image);
        holder.textTitle.setText(movie.getTitle());
//        holder.textDate.setText(Formatter.toRelativeTimeSpanString(movie.getPublishedAt()));
//        holder.textSource.setText(movie.getSource());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    public void setData(List<DiscoverMoviesResponse.Movie> newsFeedArticles) {
        if (data == null) {
            data = new ArrayList<>();
        }
        if (!data.isEmpty()) {
            data.clear();
        }
        data.addAll(newsFeedArticles);
        notifyDataSetChanged();
    }


    DiscoverMoviesResponse.Movie getItem(int position) {
        if (position < 0 || data == null || position >= data.size()) {
            throw new IllegalArgumentException();
        }
        return data.get(position);
    }


    static class NewsFeedArticleHolder extends RecyclerView.ViewHolder {

        interface OnArticleClickListener {
            void onArticleClick(int position);
            void onShareClick(int position);
        }


        final ImageView image;
        final TextView textTitle;


        NewsFeedArticleHolder(View itemView, @NonNull final OnArticleClickListener onArticleClickListener) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image_movie_feed_item_backdrop);
            textTitle = (TextView) itemView.findViewById(R.id.text_movie_feed_item_title);
//            textDate = (TextView) itemView.findViewById(R.id.text_news_feed_article_date);
//            textSource = (TextView) itemView.findViewById(R.id.text_news_feed_article_source);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onArticleClickListener.onArticleClick(getLayoutPosition());
                }
            });
            itemView.findViewById(R.id.button_movie_feed_item_share).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onArticleClickListener.onShareClick(getLayoutPosition());
                }
            });


        }
    }
}
