package com.gemi.ahmedgemi.movie_app;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ahmed Gemi on 13/10/2017.
 */
public class Movies_Adapter extends RecyclerView.Adapter<Details_Holder> {


    String poster_images_url = "http://image.tmdb.org/t/p/w185/";
    List<Movie_Class> movie_class_list;
    Movie_Class movie_class;
    Context context;
    static Configuration config;


    public Movies_Adapter(Context context, List<Movie_Class> movie_class_list) {
        this.context = context;
        this.movie_class_list = movie_class_list;
        config = context.getResources().getConfiguration();
    }

    @Override
    public Details_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.movies_model, null);
        Details_Holder holder = new Details_Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Details_Holder holder, int position) {

        movie_class = movie_class_list.get(position);

        final int id = movie_class.getId();
        final String title = movie_class.getTitle();
        final String vote = movie_class.getVoting();
        final String synopsis = movie_class.getSynopsis();
        final String date = movie_class.getRelease();
        final String image = movie_class.getPoster_img();

        Picasso.with(context).load(poster_images_url + image).into(holder.poster);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (config.smallestScreenWidthDp >= 600) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", id);
                    bundle.putString("title", title);
                    bundle.putString("vote", vote);
                    bundle.putString("overview", synopsis);
                    bundle.putString("released", date);
                    bundle.putString("poster", image);
                    MovieFragment detailsFragment = new MovieFragment();
                    detailsFragment.setArguments(bundle);
                    ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_movies, detailsFragment).commit();
                } else {

                    Intent i=new Intent(context,Movie_Details.class);
                  //  Bundle bundle = new Bundle();
                    i.putExtra("id", id);
                    i.putExtra("title", title);
                    i.putExtra("vote", vote);
                    i.putExtra("overview", synopsis);
                    i.putExtra("released", date);
                    i.putExtra("poster", image);
                    context.startActivity(i);

                    /*
                    MovieFragment detailsFragment = new MovieFragment();
                    detailsFragment.setArguments(bundle);
                    ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.container_movies, detailsFragment).commit();*/
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return movie_class_list.size();
    }


}
