package com.orchtech.baking_app.ui.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.orchtech.baking_app.R;
import com.orchtech.baking_app.models.IngredientsModel;
import com.orchtech.baking_app.models.StepsModel;
import com.orchtech.baking_app.ui.activities.ReceipeCardActivity;
import com.orchtech.baking_app.ui.activities.RecipeDetailActivity;
import com.orchtech.baking_app.ui.adapters.IngredientsAdapter;

import java.util.ArrayList;


/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ReceipeCardActivity}
 * in two-pane mode (on tablets) or a {@link RecipeDetailActivity}
 * on handsets.
 */

public class RecipeDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "step_id";
    public static final String StepDesc = "step_desc";
    public static final String StepVideoUrl = "step_video";
    public static final String StepThumbnail = "step_thumb";


    public static final String IngredientList = "ingredient_list";


    ArrayList<IngredientsModel> ingredientsModelList;

    SimpleExoPlayerView exoPlayer;
    TextView txt_desc;
    SimpleExoPlayer player;
    ImageView step_img;
    IngredientsAdapter ingredientsAdapter;
    LinearLayout lin_steps, lin_ingredient;
    RecyclerView rec_ingredients;
    LinearLayoutManager linearLayoutManager;
    /**
     * The dummy content this fragment is presenting.
     */

    private StepsModel mItem;
    private String VideoUrl,ImageUrl, StepsDesc;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            if (getArguments().containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.

                if (getArguments().getString(StepVideoUrl).equals("")) {
                    ImageUrl = getArguments().getString(StepThumbnail);
                } else {
                    VideoUrl = getArguments().getString(StepVideoUrl);
                }
                StepsDesc = getArguments().getString(StepDesc);

                Activity activity = this.getActivity();
                CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
                if (appBarLayout != null) {
                    appBarLayout.setTitle(StepsDesc);
                }
            }
        } catch (Exception e) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);


        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            //   ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);

        }

        lin_steps = rootView.findViewById(R.id.lin_steps);

        lin_ingredient = rootView.findViewById(R.id.lin_ingredient);

        exoPlayer = rootView.findViewById(R.id.exoPlayer);
        step_img=rootView.findViewById(R.id.step_img);
        txt_desc = rootView.findViewById(R.id.txt_desc);

        try {
            if (!getArguments().getParcelableArrayList("ingredient_list").equals("")) {

                lin_ingredient.setVisibility(View.VISIBLE);
                lin_steps.setVisibility(View.GONE);

                ingredientsModelList = getArguments().getParcelableArrayList("ingredient_list");
                rec_ingredients = rootView.findViewById(R.id.rec_ingredients);
                linearLayoutManager = new LinearLayoutManager(getActivity());
                rec_ingredients.setLayoutManager(linearLayoutManager);
                ingredientsAdapter = new IngredientsAdapter(ingredientsModelList, getActivity());
                rec_ingredients.setAdapter(ingredientsAdapter);
            }
        } catch (Exception e) {

            lin_ingredient.setVisibility(View.GONE);
            lin_steps.setVisibility(View.VISIBLE);
            txt_desc.setText(StepsDesc);

            try{
                if(VideoUrl==null){



                    exoPlayer.setVisibility(View.GONE);
                    step_img.setVisibility(View.VISIBLE);

                    Glide.with(getActivity()).load(ImageUrl).into(step_img);
                }

            }
            catch (Exception ee) {
                step_img.setVisibility(View.GONE);
                exoPlayer.setVisibility(View.VISIBLE);
                initializePlayer();
            }
        }


        return rootView;
    }

    private void initializePlayer() {



       /* SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getActivity().getApplicationContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);
        video_view.setPlayer(player);*/

        player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getActivity().getApplicationContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());

        player.setPlayWhenReady(true);

        //player.seekTo(currentWindow, playbackPosition);


        Uri uri = Uri.parse(VideoUrl);
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);

        exoPlayer.setPlayer(player);
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource(uri,
                new DefaultHttpDataSourceFactory("ua"),
                new DefaultExtractorsFactory(), null, null);
    }

    @Override
    public void onStop() {
        super.onStop();

        releasePlayer();
    }

    private void releasePlayer() {

        if (player != null) {
           /* playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();*/
            player.release();
            player = null;
        }
    }
}
