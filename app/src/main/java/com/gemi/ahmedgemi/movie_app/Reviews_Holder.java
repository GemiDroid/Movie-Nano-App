package com.gemi.ahmedgemi.movie_app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Ahmed Gemi on 13/10/2017.
 */
public class Reviews_Holder extends RecyclerView.ViewHolder {


    TextView author,content;

    public Reviews_Holder(View itemView) {
        super(itemView);
        author = (TextView) itemView.findViewById(R.id.author_name);
        content=(TextView)itemView.findViewById(R.id.review_content);
    }
}
