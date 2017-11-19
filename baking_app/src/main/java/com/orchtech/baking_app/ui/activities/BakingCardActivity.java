package com.orchtech.baking_app.ui.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.orchtech.baking_app.R;
import com.orchtech.baking_app.models.Preferences;
import com.orchtech.baking_app.ui.adapters.BakingCardAdapter;

import java.util.Locale;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        languageToLoad = Preferences.getFromPreference(getApplicationContext(), "MyLang");
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_card_list);

        toolbar = findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        MyAppbar =findViewById(R.id.MyAppbar);
        MyAppbar.setExpanded(true);

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapse_toolbar);
        collapsingToolbar.setTitle(getString(R.string.app_name));
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        collapsingToolbar.setContentScrim(getResources().getDrawable(R.drawable.actionbar_background));

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
