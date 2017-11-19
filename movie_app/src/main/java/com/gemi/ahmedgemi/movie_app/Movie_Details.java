package com.gemi.ahmedgemi.movie_app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by Ahmed Gemi on 13/10/2017.
 */
public class Movie_Details extends FragmentActivity {
    private MovieFragment fragment;

/*
    Movie_Detais_Adapter detais_adapter;
    Movie_Class movie_class;
    List<Movie_Class>movie_classList;
    RecyclerView recycler_datails;*/
/*
    String poster_images_url = "http://image.tmdb.org/t/p/w185/";
    TextView title, overview, rate, vote;
    ImageView movie_image;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);


        FragmentManager fm = getSupportFragmentManager();
      //  Fragment fragment = fm.findFragmentById(R.id.fragment_movies);

        if (savedInstanceState == null) {
            fragment = new MovieFragment();
            fm.beginTransaction().add(R.id.container_movies, fragment).commit();
        }



       /* title = (TextView) findViewById(R.id.movie_title);
        overview = (TextView) findViewById(R.id.movie_synopsis);
        rate = (TextView) findViewById(R.id.movie_release);
        vote = (TextView) findViewById(R.id.movie_voting);

        movie_image = (ImageView) findViewById(R.id.movie_img);

        Bundle bundle = getIntent().getExtras();

        title.setText(bundle.getString("title"));
        overview.setText(bundle.getString("overview"));
        rate.setText(bundle.getString("released"));
        vote.setText(bundle.getString("vote"));

        Picasso.with(this).load(poster_images_url + bundle.getString("poster")).into(movie_image);
*/
    }


}

