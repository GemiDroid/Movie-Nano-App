package com.gemi.ahmedgemi.movie_app;


import android.content.Context;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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
public class PosterFragment extends Fragment {

    RecyclerView recycler;
    List<Movie_Class> list_movie;
    Movie_Class movie_class;
    Spinner spin_category;
    Movies_Adapter adapter;
    Configuration config;

    DBHelper.database data;


    //  private PosterFragment poster_fragment;

    private String movies_url_top = "https://api.themoviedb.org/3/movie/top_rated?api_key=";

    private String movies_url_popular = "https://api.themoviedb.org/3/movie/popular?api_key=";

    String[] Categories = {"Most Popular", "Highest Rated", "Favourites"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_poster, container, false);

        config = getResources().getConfiguration();

        recycler = (RecyclerView) view.findViewById(R.id.recycler_movies);
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recycler.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        } else {
            recycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        }


        spin_category = (Spinner) view.findViewById(R.id.spin_category);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, Categories);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_category.setAdapter(adapter);
        spin_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        get_movies(movies_url_popular);
                        break;
                    case 1:
                        get_movies(movies_url_top);
                        break;
                    case 2:
                        get_favourites();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return view;


    }

    public void get_favourites() {
        data = new DBHelper.database(getActivity());
        adapter = new Movies_Adapter(getActivity(), data.get_from_favourites());
        recycler.setAdapter(adapter);

    }

    public void get_movies(String url) {


        new async_movies(getActivity()).execute(url);

    }

    public class async_movies extends AsyncTask<String, Void, Void> {
        Context context;

        public async_movies(Context context) {
            this.context = context;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            try {
                if (!list_movie.isEmpty()) {
                    adapter = new Movies_Adapter(context, list_movie);
                    recycler.setAdapter(adapter);
                }
            } catch (NullPointerException e) {
                //  String data_status = Connection.getConnectivityStatusString(getActivity());
                Toast.makeText(getActivity(), "Not Connected\nTurn on Internet access", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPreExecute() {
            Toast.makeText(context, "Plase,wait getting posters ", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(String... urls) {

            try {
                String lines;
                URL url = new URL(urls[0]);
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
                list_movie = new ArrayList<>();

                for (int i = 0; i < arr.length(); i++) {

                    JSONObject jsonObject = arr.getJSONObject(i);
                    int id = jsonObject.getInt("id");
                    String posters = jsonObject.getString("poster_path");
                    String title = jsonObject.getString("original_title");
                    String votes = jsonObject.getString("vote_average");
                    String released = jsonObject.getString("release_date");
                    String overview = jsonObject.getString("overview");
                    // data for reviews and videos links

                    movie_class = new Movie_Class(id, posters, released, overview, title, votes);
                    list_movie.add(movie_class);
                }


            } catch (MalformedURLException e) {
                //   Toast.makeText(getActivity(), "Movies Page has wrong issues,try again(404)", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                // Toast.makeText(getActivity(), "Turn on Connection first,try again(404)", Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                // Toast.makeText(getActivity(), "Movie Data not found", Toast.LENGTH_SHORT).show();
            }


            return null;
        }
    }


}
