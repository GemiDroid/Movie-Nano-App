package com.gemi.ahmedgemi.movie_app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Ahmed Gemi on 13/10/2017.
 */
public class Trailers_Holder extends RecyclerView.ViewHolder {


    TextView name;

    public Trailers_Holder(View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.trailer_name);
    }
}
