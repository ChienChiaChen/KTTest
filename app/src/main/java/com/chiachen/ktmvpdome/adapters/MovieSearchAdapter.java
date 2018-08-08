package com.chiachen.ktmvpdome.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chiachen.ktmvpdome.R;
import com.mvp.moviedbapi.constants.Urls;
import com.mvp.moviedbapi.models.apis.SearchResults;
import com.squareup.picasso.Picasso;

/**
 * Created by jianjiacheng on 2018/8/8.
 */

public class MovieSearchAdapter extends RecyclerView.Adapter<MovieSearchAdapter.ViewHolder> {

    private SearchResults mSearchResults;

    public MovieSearchAdapter(SearchResults searchResults) {
        mSearchResults = searchResults;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_movie_cell, parent, false);
        return new MovieSearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (null == holder || null == holder.mImageView || null == holder.mImageView.getContext()) {
            return;
        }

        Picasso.with(holder.mImageView.getContext()).load(getUrlFromResults(position)).into(holder.mImageView);
    }

    private String getUrlFromResults(int position) {
        if (mSearchResults == null || mSearchResults.getResults() == null || mSearchResults.getResults().size() < position) {
            return "";
        }

        //TODO handle different phone resolutions (download images which sizes are close to the device resolution, for better performances)
        StringBuilder stringBuilder = new StringBuilder(Urls.IMAGE_BASE_URL);
        stringBuilder.append(Urls.IMAGE_SIZE_HD).append(mSearchResults.getResults().get(position).getPosterPath());
        return stringBuilder.toString();
    }

    @Override
    public int getItemCount() {
        if (null == mSearchResults || null == mSearchResults.getResults()) {
            return 0;
        }
        return mSearchResults.getResults().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;

        ViewHolder(View view) {
            super(view);
            this.mImageView = (ImageView) view.findViewById(R.id.view_movie_cell_image_view);
        }
    }

    public MovieSearchAdapter setSearchResults(SearchResults searchResults) {
        mSearchResults = searchResults;
        return this;
    }
}
