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
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.orchtech.baking_app.R;
import com.orchtech.baking_app.manager.BakingManager;
import com.orchtech.baking_app.models.BakingModel;
import com.orchtech.baking_app.models.IngredientsModel;
import com.orchtech.baking_app.models.Preferences;
import com.orchtech.baking_app.ui.adapters.BakingCardAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by pc on 11/18/2017.
 */

public class BakingCardActivity extends AppCompatActivity {

    public static BakingModel bakingModel = null;
    public static ArrayList<IngredientsModel> ingerdientList = null;
    static int x;
    RecyclerView rec_baking_card;
    LinearLayoutManager linearLayoutManager;
    AppBarLayout MyAppbar;
    Toolbar toolbar;
    String languageToLoad;
    int currentVisiblePosition = 0;
    BakingCardAdapter bakingCardAdapter;
    BakingManager bakingManager;
    Configuration config;
    ProgressBar prog_baking;
    List<BakingModel> bakingModelList;

    static boolean isExpanding = true;


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
        MyAppbar = findViewById(R.id.MyAppbar);
        MyAppbar.setExpanded(isExpanding);

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapse_toolbar);
        collapsingToolbar.setTitle(getString(R.string.app_name));
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        collapsingToolbar.setContentScrim(getResources().getDrawable(R.drawable.actionbar_background));

        MyAppbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    isExpanding = true;

                } else {
                    isExpanding = false;
                }
            }
        });


        rec_baking_card = findViewById(R.id.rec_baking_card);

        prog_baking = findViewById(R.id.prog_baking);

       /* if (config.orientation == Configuration.ORIENTATION_LANDSCAPE &&  config.smallestScreenWidthDp < 600)  {
           x=3;
        } else */
        if (config.orientation == Configuration.ORIENTATION_PORTRAIT && config.smallestScreenWidthDp < 600) {
            x = 1;
        } else {
            x = 3;
        }

        linearLayoutManager = new GridLayoutManager(this, x);

        rec_baking_card.setLayoutManager(linearLayoutManager);


        GetBakingCards();


    }

    private void GetBakingCards() {

        prog_baking.setVisibility(View.VISIBLE);

        if (bakingManager == null) {
            bakingManager = new BakingManager();
        }

        if (bakingModelList == null) {
            bakingModelList = new ArrayList<>();
        }

        bakingManager.GetBakings().enqueue(new Callback<List<BakingModel>>() {
            @Override
            public void onResponse(Call<List<BakingModel>> call, Response<List<BakingModel>> response) {

                if (response.code() >= 200 && response.code() < 300) {

                    if (response.body() == null) {
                        // Empty List
                    } else {
                        if (bakingModel == null)
                            if (response.body().size() > 0)
                                ingerdientList = response.body().get(0).getIngredientsModels();
                        bakingModelList = response.body();

                        bakingCardAdapter = new BakingCardAdapter(bakingModelList, BakingCardActivity.this);
                        rec_baking_card.setAdapter(bakingCardAdapter);
                        bakingCardAdapter.notifyDataSetChanged();

                        //  Preferences.setList(BakingCardActivity.this,"baking_list",bakingModelList);

                    }
                } else {
                    Toast.makeText(BakingCardActivity.this, getString(R.string.issue_in_server), Toast.LENGTH_SHORT).show();
                }

                prog_baking.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<BakingModel>> call, Throwable t) {

                t.printStackTrace();
                Toast.makeText(BakingCardActivity.this, getString(R.string.issue_in_internet), Toast.LENGTH_SHORT).show();

                prog_baking.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        (rec_baking_card.getLayoutManager()).scrollToPosition(currentVisiblePosition);
        currentVisiblePosition = 0;

        MyAppbar.setExpanded(Boolean.valueOf(Preferences.getFromPreference(this, "isExpanding")));


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        currentVisiblePosition = ((LinearLayoutManager) rec_baking_card.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        Preferences.saveInPreference(this, "isExpanding", "" + isExpanding);
    }
}
