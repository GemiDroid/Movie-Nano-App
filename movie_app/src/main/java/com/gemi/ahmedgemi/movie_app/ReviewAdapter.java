package com.gemi.ahmedgemi.movie_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ahmed Gemi on 13/10/2017.
 */
public class ReviewAdapter extends BaseAdapter {

    List<Reviews_Class> review_list;
    Context context;


    public ReviewAdapter(Context context, List<Reviews_Class> review_list) {
        this.context = context;
        this.review_list = review_list;
    }

    @Override
    public int getCount() {
        return review_list.size();
    }

    @Override
    public Object getItem(int position) {
        return review_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.review_model,null);
        }

        TextView author = (TextView) convertView.findViewById(R.id.author_name);
        TextView content = (TextView) convertView.findViewById(R.id.review_content);

        Reviews_Class class_review = (Reviews_Class) getItem(position);

        author.setText(class_review.getAuthor_name());
        content.setText(class_review.getContent());

        return convertView;

    }
}
