package com.gemi.ahmedgemi.movie_app;

import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Gemi on 13/10/2017.
 */
public class MovieFragment extends Fragment {


    DBHelper.database data;
    String poster_images_url = "http://image.tmdb.org/t/p/w185/";
    private static final String KEY = "6c08ba20de298358919a0cfd7c0c066a";
    TextView title, overview, rate, vote, author, content;
    ImageView movie_image, movie_star;

    // Every thing related to Trailers ....
    RecyclerView trailer_recycler;
    Trailer_Adapter adapter_trailers;
    Trailers_Class trailer_model;
    List<Trailers_Class> trailer_list;

    // Every thing related to Reviews ....
    List<Reviews_Class> review_list;
    Reviews_Class class_review;
    RecyclerView review_rec;
    Review_Rec_Adapter review_rec_adapter;

    // List<String> review_content_list;
    //  HashMap<String, String> review_content_HashMap;
    //  ReviewAdapter adapter_reviews;

    //LinearListView listview_review;
    //  ExpandableListView ExpListView;
    //   ListView listview_review;
    Bundle args;
    Bundle i;
    static int id;
    static String image_url;
    ScrollView scl_movie_details;
    int ScrollPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        i = getActivity().getIntent().getExtras();
        args = getArguments();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        scl_movie_details.setVerticalScrollbarPosition(ScrollPosition);
        restoreData();
    }

    private void restoreData() {


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ScrollPosition = scl_movie_details.getVerticalScrollbarPosition();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.movie_fragment, container, false);

        title = (TextView) v.findViewById(R.id.movie_title);
        overview = (TextView) v.findViewById(R.id.movie_synopsis);
        rate = (TextView) v.findViewById(R.id.movie_release);
        vote = (TextView) v.findViewById(R.id.movie_voting);
    /*    author = (TextView) v.findViewById(R.id.author_name);
        content = (TextView) v.findViewById(R.id.review_content);
*/
//        reviews = (TextView) v.findViewById(R.id.movie_reviews);
        //      videos_key = (TextView) v.findViewById(R.id.movie_videos);


        movie_image = (ImageView) v.findViewById(R.id.movie_img);
        movie_star = (ImageView) v.findViewById(R.id.movie_star_img);

        scl_movie_details = v.findViewById(R.id.scl_movie_details);


        trailer_recycler = (RecyclerView) v.findViewById(R.id.trailers_recycler);
        trailer_recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        review_rec = (RecyclerView) v.findViewById(R.id.reviewsList);
        review_rec.setLayoutManager(new LinearLayoutManager(getActivity()));

        //  LinearLayout linear=(LinearLayout)v.findViewById(R.id.fragment_movies);

        //  ExpListView = (ExpandableListView) v.findViewById(R.id.lvExp);
        //  ExpListView.expandGroup(1);

        // listview_review = (LinearListView) v.findViewById(R.id.reviewsList);
        // listview_review = (ListView) v.findViewById(R.id.reviewsList);
        //   listview_review.addHeaderView(v);
        //   listview_review.setScrollingCacheEnabled(true);


        // final Bundle bundle = getActivity().getIntent().getExtras();
        if (getArguments() != null) {

            title.setText(getArguments().getString("title"));
            overview.setText(getArguments().getString("overview"));
            rate.setText(getArguments().getString("released"));
            vote.setText(getArguments().getString("vote"));
            id = getArguments().getInt("id");
            image_url = getArguments().getString("poster");
//            reviews.setText(bundle.getString("reviews"));
            //          videos_key.setText(bundle.getString("videos"));


            Picasso.with(getContext()).load(poster_images_url + image_url).into(movie_image);
        } else {
            title.setText(i.getString("title"));
            overview.setText(i.getString("overview"));
            rate.setText(i.getString("released"));
            vote.setText(i.getString("vote"));
            id = i.getInt("id");
            image_url = i.getString("poster");
//            reviews.setText(bundle.getString("reviews"));
            //          videos_key.setText(bundle.getString("videos"));


            Picasso.with(getContext()).load(poster_images_url + image_url).into(movie_image);

        }
        //   Log.d("MovieID",id+"");

        getTrailers();
        getReviews();

        movie_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              /*  data = new DBHelper.database(getActivity());*/

                boolean added_to_favourites =
                        isAdd();

                if (added_to_favourites) {
                    Toast.makeText(getActivity(), "Movie Added to Favourites :) ", Toast.LENGTH_LONG).show();
                    movie_star.setImageResource(android.R.drawable.btn_star_big_on);
                    movie_star.setEnabled(false);
                } else {
                    Toast.makeText(getActivity(), "Something goes wrong :(\nTry again later", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return v;

    }

    private boolean isAdd() {

        // Add a new student record
        ContentValues values = new ContentValues();
        values.put(MyContentProvider.TITLE,
                title.getText().toString());

        values.put(MyContentProvider.RATE,
                rate.getText().toString());

        values.put(MyContentProvider.POSTER,
                image_url);


        /*Uri uri = getActivity().getContentResolver().insert(
                MyContentProvider.CONTENT_URI, values);*/
        getActivity().getContentResolver().insert(MyContentProvider.CONTENT_URI, values);

      /*  Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();*/

        return true;
    }


    public void getTrailers() {
        new Async_Trailer().execute("https://api.themoviedb.org/3/movie/" + id + "/videos?api_key=" + KEY);
    }

    public void getReviews() {
        new Async_Reviews().execute("https://api.themoviedb.org/3/movie/" + id + "/reviews?api_key=" + KEY);
    }

    public class Async_Trailer extends AsyncTask<String, Void, Void>

    {
        @Override
        protected Void doInBackground(String... params) {

            try {
                String lines;
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                InputStreamReader input_reader = new InputStreamReader(con.getInputStream());
                BufferedReader buffer_reader = new BufferedReader(input_reader);
                StringBuilder builder = new StringBuilder();
                Log.e("Builder", builder.toString());

                while ((lines = buffer_reader.readLine()) != null) {
                    builder.append(lines);
                }

                String result_array = builder.toString();


                JSONObject object = new JSONObject(result_array);
                JSONArray arr = object.getJSONArray("results");
                trailer_list = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {

                    JSONObject jsonObject = arr.getJSONObject(i);
                    String key = jsonObject.getString("key");
                    trailer_model = new Trailers_Class(key);
                    trailer_list.add(trailer_model);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            try {
                if (trailer_list.isEmpty()) {
                    Toast.makeText(getActivity(), "Trailers not found", Toast.LENGTH_LONG).show();
                } else {
                    adapter_trailers = new Trailer_Adapter(trailer_list, getActivity());
                    trailer_recycler.setAdapter(adapter_trailers);
                }
            } catch (NullPointerException e) {
                Toast.makeText(getActivity(), "Not Connected", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(getActivity(), "Getting Trailers", Toast.LENGTH_LONG).show();
        }
    }


    public class Async_Reviews extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {

            try {
                String lines;
                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                InputStreamReader input_reader = new InputStreamReader(con.getInputStream());
                BufferedReader buffer_reader = new BufferedReader(input_reader);
                StringBuilder builder = new StringBuilder();
                Log.e("Builder", builder.toString());

                while ((lines = buffer_reader.readLine()) != null) {
                    builder.append(lines);
                }

                String result_array = builder.toString();


                JSONObject object = new JSONObject(result_array);
                JSONArray arr = object.getJSONArray("results");
                review_list = new ArrayList<>();
                //review_content_list = new ArrayList<>();
                //review_content_HashMap = new HashMap<>();
                for (int i = 0; i < arr.length(); i++) {

                    JSONObject jsonObject = arr.getJSONObject(i);
                    String author = jsonObject.getString("author");
                    String content = jsonObject.getString("content");
                    class_review = new Reviews_Class(author, content);
                    review_list.add(class_review);
                    // review_content_list.add(content);
                    //    review_content_HashMap.put(review_author_list.get(i), review_content_list.get(i));
                }
                Log.d("HAshMap", review_list.size() + "");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
           /* try {
                if (review_list.isEmpty()) {
                    author.setText("There is No Reviews for this Movie :( ");
                } else {

                    for (int i = 0; i < review_list.size(); i++) {
                        author.setText(review_list.get(i).getAuthor_name());
                        content.setText(review_list.get(i).getContent());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            try {
                if (review_list.isEmpty()) {
                    Toast.makeText(getActivity(), "No Review for this movie :(", Toast.LENGTH_LONG).show();
                } else {
                    review_rec_adapter = new Review_Rec_Adapter(getActivity(), review_list);
                    review_rec.setAdapter(review_rec_adapter);

                }
            } catch (NullPointerException e) {
//                Toast.makeText(getActivity(), "Not Connected", Toast.LENGTH_LONG).show();
            }

        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(getActivity(), "Getting Reviews", Toast.LENGTH_LONG).show();
        }
    }
}
