package com.gemi.ahmedgemi.movie_app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Ahmed Gemi on 13/10/2017.
 */
public class Trailer_Adapter extends RecyclerView.Adapter<Trailers_Holder> {

    Trailers_Class trailer_class;
    List<Trailers_Class> trailer_list;
    Context context;

    public Trailer_Adapter(List<Trailers_Class> trailer_list, Context context) {
        this.trailer_list = trailer_list;
        this.context = context;
    }

    @Override
    public Trailers_Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View trailer_view = LayoutInflater.from(context).inflate(R.layout.trailer_model, null);
        Trailers_Holder holder = new Trailers_Holder(trailer_view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Trailers_Holder holder, int position) {


        final String video_key;

        trailer_class=trailer_list.get(position);
        video_key=trailer_class.getKey_videos();

        holder.name.setText("Trailer"+(position+1));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri= Uri.parse("https://m.youtube.com/watch?v="+video_key);
                Intent youtube_watching=new Intent(Intent.ACTION_VIEW,uri);
                context.startActivity(youtube_watching);
            }
        });


    }

    @Override
    public int getItemCount() {
        return trailer_list.size();
    }
}