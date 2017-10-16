package com.alimertozdemir.omdbapp.search;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alimertozdemir.omdbapp.R;
import com.alimertozdemir.omdbapp.search.model.Movie;

import java.util.List;

/**
 * Created by alimertozdemir on 14.10.2017.
 */

public class SearchMovieResultAdapter extends RecyclerView.Adapter<SearchMovieResultAdapter.ResultHolder> {
    private List<Movie> movies;
    private OnItemClickListener onItemClickListener;
    public class ResultHolder extends RecyclerView.ViewHolder implements SearchMovieContract.View {

        ProgressBar pBar;
        Bitmap thumbImage;
        ImageView ivThumbnail;
        TextView tvName;
        TextView tvYear;
        SearchMovieContract.Presenter presenter = new SearchMoviePresenter(this);

        public ResultHolder(View view) {
            super(view);
            pBar = view.findViewById(R.id.pb_img_loading);
            ivThumbnail = view.findViewById(R.id.iv_thumbnail);
            tvName = view.findViewById(R.id.tv_title);
            tvYear = view.findViewById(R.id.tv_year);
        }

        public void getThumbnail(String posterUrl) {
            if (thumbImage != null)
                return;
            presenter.getThumbImageFromURL(posterUrl);
        }

        @Override
        public void setMovieResult(List<Movie> movies) {
        }

        @Override
        public void showErrorMessage(String errorMessage) {
        }

        @Override
        public void setThumbImage(Bitmap thumbImage) {
            if (this.thumbImage != null)
                return;

            pBar.setVisibility(View.GONE);
            ivThumbnail.setImageBitmap(thumbImage);
            ivThumbnail.setVisibility(View.VISIBLE);
        }

        @Override
        public void goToMovieDetail(Movie movie) {

        }
    }

    public SearchMovieResultAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public SearchMovieResultAdapter.ResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new SearchMovieResultAdapter.ResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final SearchMovieResultAdapter.ResultHolder holder, int position) {
        final Movie item = movies.get(position);

        holder.pBar.setVisibility(View.VISIBLE);
        holder.ivThumbnail.setVisibility(View.GONE);

        holder.getThumbnail(item.getPoster());
        holder.tvName.setText(item.getTitle());
        holder.tvYear.setText(item.getYear());

        if (holder.thumbImage != null) {
            holder.pBar.setVisibility(View.GONE);
            holder.ivThumbnail.setImageBitmap(holder.thumbImage);
            holder.ivThumbnail.setVisibility(View.VISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (movies == null)
            return 0;

        return movies.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void updateMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onClick(Movie item);
    }

}