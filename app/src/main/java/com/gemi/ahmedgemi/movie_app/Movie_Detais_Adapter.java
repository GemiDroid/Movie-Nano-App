package com.gemi.ahmedgemi.movie_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ahmed Gemi on 13/10/2017.
 */


// Unused Adapter..
public class Movie_Detais_Adapter extends RecyclerView.Adapter<Details_Holder> {


    Context context;
    List<Movie_Class> movie_classList;
    Movie_Class movie_class;
    String poster_images_url = "http://image.tmdb.org/t/p/w185/";


    public Movie_Detais_Adapter(List<Movie_Class> movie_classList, Context context) {
        this.movie_classList = movie_classList;
        this.context = context;
    }

    @Override
    public Details_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.movie_details, null);
        Details_Holder holder = new Details_Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Details_Holder holder, int position) {

        movie_class = movie_classList.get(position);

        holder.title.setText(movie_class.getTitle());
        holder.date.setText(movie_class.getRelease());
        holder.synopsis.setText(movie_class.getSynopsis());
        holder.vote.setText(movie_class.getVoting());

        Picasso.with(context).load(poster_images_url + movie_class.getPoster_img()).into(holder.poster);

    }

    @Override
    public int getItemCount() {
        return movie_classList.size();
    }
}
