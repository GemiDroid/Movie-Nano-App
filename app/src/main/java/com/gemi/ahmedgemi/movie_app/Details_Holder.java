package com.gemi.ahmedgemi.movie_app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ahmed Gemi on 11/08/2016.
 */
public class Details_Holder extends RecyclerView.ViewHolder{

    TextView title,vote,synopsis,date;
    ImageView poster;


    public Details_Holder(View itemView) {
        super(itemView);

        title=itemView.findViewById(R.id.movie_title);
        vote=itemView.findViewById(R.id.movie_voting);
        synopsis=itemView.findViewById(R.id.movie_synopsis);
        date=itemView.findViewById(R.id.movie_release);

        poster=itemView.findViewById(R.id. grid_images);
    }
}
