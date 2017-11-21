package com.orchtech.baking_app.ui.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.orchtech.baking_app.R;
import com.orchtech.baking_app.manager.BakingManager;
import com.orchtech.baking_app.models.BakingModel;
import com.orchtech.baking_app.models.Preferences;
import com.orchtech.baking_app.ui.adapters.BakingCardAdapter;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pc on 11/18/2017.
 */

public class BakingCardActivity extends AppCompatActivity {

    RecyclerView rec_baking_card;
    LinearLayoutManager linearLayoutManager;
    AppBarLayout MyAppbar;
    Toolbar toolbar;
    String languageToLoad;
    int currentVisiblePosition = 0;
    BakingCardAdapter bakingCardAdapter;
    BakingManager  bakingManager;
    static int x;
Configuration config;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        languageToLoad = Preferences.getFromPreference(getApplicationContext(), "MyLang");
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_card_list);

        toolbar = findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        config = getResources().getConfiguration();
        MyAppbar =findViewById(R.id.MyAppbar);
        MyAppbar.setExpanded(true);

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapse_toolbar);
        collapsingToolbar.setTitle(getString(R.string.app_name));
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        collapsingToolbar.setContentScrim(getResources().getDrawable(R.drawable.actionbar_background));

        rec_baking_card=findViewById(R.id.rec_baking_card);

       /* if (config.orientation == Configuration.ORIENTATION_LANDSCAPE &&  config.smallestScreenWidthDp < 600)  {
           x=3;
        } else */if(config.orientation==Configuration.ORIENTATION_PORTRAIT && config.smallestScreenWidthDp <600) {
           x=1;
        }else {
            x=3;
        }

        linearLayoutManager=new GridLayoutManager(this,x);

        rec_baking_card.setLayoutManager(linearLayoutManager);

        GetBakingCards();


    }

    private void GetBakingCards() {

        if(bakingManager==null){bakingManager=new BakingManager();}

        bakingManager.GetBakings().enqueue(new Callback<List<BakingModel>>() {
            @Override
            public void onResponse(Call<List<BakingModel>> call, Response<List<BakingModel>> response) {

                if(response.code()>=200&&response.code()<300){

                    if(response.body()==null){
                        // Empty List
                    }
                    else {

                        bakingCardAdapter=new BakingCardAdapter(response.body(),BakingCardActivity.this);
                        rec_baking_card.setAdapter(bakingCardAdapter);
                        bakingCardAdapter.notifyDataSetChanged();

                    }
                }
                else {
                    Toast.makeText(BakingCardActivity.this, getString(R.string.issue_in_server), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<BakingModel>> call, Throwable t) {

                t.printStackTrace();
                Toast.makeText(BakingCardActivity.this, getString(R.string.issue_in_internet), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

       super.onRestoreInstanceState(savedInstanceState);
        (rec_baking_card.getLayoutManager()).scrollToPosition(currentVisiblePosition);
        currentVisiblePosition = 0;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        currentVisiblePosition = ((LinearLayoutManager)rec_baking_card.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
    }
}
