package com.gemi.ahmedgemi.movie_app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

/**
 * Created by Ahmed Gemi on 13/10/2017.
 */
public class Home extends FragmentActivity {
  static boolean mTwoPane;
    private PosterFragment poster_fragment;
   private TestFragment test_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);


        // Need here more examination ...


        FragmentManager manager = getSupportFragmentManager();

        if (findViewById(R.id.fragment_movies) != null) {


            if (savedInstanceState == null) {
                try {
                     test_fragment = new TestFragment();
                    manager.beginTransaction().replace(R.id.fragment_movies, test_fragment).commit();
                } catch (Exception e) {
                    Toast.makeText(this, "Please select movie first ..", Toast.LENGTH_LONG).show();
                }
            }
            mTwoPane = true;

        } else {
            poster_fragment = new PosterFragment();
            manager.beginTransaction().add(R.id.poster_activity, poster_fragment).commit();
            mTwoPane = false;
        }
    }

}



    /*  recycler = (RecyclerView) findViewById(R.id.recycler_movies);
        recycler.setLayoutManager(new GridLayoutManager(this, 2));*/
/* spin_category = (Spinner) findViewById(R.id.spin_category);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Categories);
        spin_category.setAdapter(adapter);
        spin_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        //      get_movies(movies_url_popular);
                        break;
                    case 1:
                        //   get_movies(movies_url_top);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/



