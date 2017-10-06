package com.gemi.ahmedgemi.movie_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Ahmed Gemi on 13/10/2017.
 */
public class Review_Rec_Adapter  extends RecyclerView.Adapter<Reviews_Holder> {

    Reviews_Class review_class;
    List<Reviews_Class> review_list;
    Context context;

    public Review_Rec_Adapter(Context context, List<Reviews_Class> review_list) {
        this.context = context;
        this.review_list = review_list;
    }

    @Override
    public Reviews_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View review_view = LayoutInflater.from(context).inflate(R.layout.review_model, null);
        Reviews_Holder holder = new Reviews_Holder(review_view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Reviews_Holder holder, int position) {

        review_class=review_list.get(position);

        holder.author.setText(review_class.getAuthor_name());
        holder.content.setText(review_class.getContent());
    }

    @Override
    public int getItemCount() {
        return review_list.size();
    }


}
