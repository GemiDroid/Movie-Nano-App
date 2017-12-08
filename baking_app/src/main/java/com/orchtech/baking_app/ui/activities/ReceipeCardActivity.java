package com.orchtech.baking_app.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.orchtech.baking_app.R;
import com.orchtech.baking_app.dummy.DummyContent;
import com.orchtech.baking_app.models.IngredientsModel;
import com.orchtech.baking_app.models.StepsModel;
import com.orchtech.baking_app.ui.fragments.RecipeDetailFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RecipeDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ReceipeCardActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    Bundle b;
    Button btn_ingredients;
  static   ArrayList<StepsModel> StepsList;
  static   ArrayList<IngredientsModel>IngredientsList;
    RecyclerView recyclerView;
    int currentVisiblePosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipe_card_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());



       /* FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/



        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        StepsList = new ArrayList<>();
        IngredientsList=new ArrayList<>();
        try {
           /* if (getIntent().getExtras().get("ingredientsList") != null) {
*//*
                for (int i = 0; i < getIntent().getExtras().getStringArrayList("ingredientsList").size(); i++) {

                    SafeToast.makeText(this, getIntent().getExtras().getParcelableArrayList("ingredientsList").get(i).toString() + "", Toast.LENGTH_LONG).show();
                }*//*
            }*/

            StepsList = getIntent().getParcelableArrayListExtra("stepsList");//stepsList
            IngredientsList=getIntent().getParcelableArrayListExtra("ingredientsList");//ingredientsList

            Log.d("StepsSize", "onCreate: "+StepsList.size());
            Log.d("IngredientsSize", "onCreate: "+IngredientsList.size());

        } catch (Exception e) {
        }

         recyclerView = findViewById(R.id.item_list);
        btn_ingredients=findViewById(R.id.btn_ingredients);

        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView,StepsList);

        btn_ingredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putParcelableArrayList(RecipeDetailFragment.IngredientList,IngredientsList);
                    RecipeDetailFragment fragment = new RecipeDetailFragment();
                    fragment.setArguments(arguments);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, RecipeDetailActivity.class);
                    intent.putParcelableArrayListExtra(RecipeDetailFragment.IngredientList,IngredientsList);
                    context.startActivity(intent);
                }

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        currentVisiblePosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        (recyclerView.getLayoutManager()).scrollToPosition(currentVisiblePosition);
        currentVisiblePosition = 0;
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<StepsModel> StepsList) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, StepsList, mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ReceipeCardActivity mParentActivity;
        private final List<StepsModel> mValues;
        StepsModel stepObject;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();

            }
        };

        SimpleItemRecyclerViewAdapter(ReceipeCardActivity parent,
                                      List<StepsModel> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.receipe_card_model, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {


            stepObject=  mValues.get(position);
            holder.txt_steps.setText(stepObject.getShortDesc());
            final String StepId=stepObject.getId();
            final String VideoUrl=stepObject.getVideoUrl();
            final String StepDesc=stepObject.getDescription();
            final String Thumb=stepObject.getThumbUrl();
      /*      holder.mContentView.setText(mValues.get(position).content);*/

            holder.itemView.setTag(mValues.get(position));



            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {



                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(RecipeDetailFragment.ARG_ITEM_ID,StepId);
                        arguments.putString(RecipeDetailFragment.StepVideoUrl,VideoUrl);
                        arguments.putString(RecipeDetailFragment.StepThumbnail,Thumb);

                        arguments.putString(RecipeDetailFragment.StepDesc,StepDesc);


                        RecipeDetailFragment fragment = new RecipeDetailFragment();
                        fragment.setArguments(arguments);
                        mParentActivity.getSupportFragmentManager().beginTransaction()
                                .replace(R.id.item_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = view.getContext();
                        Intent intent = new Intent(context, RecipeDetailActivity.class);
                        intent.putExtra(RecipeDetailFragment.ARG_ITEM_ID,StepId);
                        intent.putExtra(RecipeDetailFragment.StepVideoUrl,VideoUrl);
                        intent.putExtra(RecipeDetailFragment.StepThumbnail,Thumb);
                        intent.putExtra(RecipeDetailFragment.StepDesc,StepDesc);
                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {

            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView txt_steps;
          /*  final TextView mContentView;*/

            ViewHolder(View view) {
                super(view);
                txt_steps = view.findViewById(R.id.txt_card_name);
               /* mContentView = (TextView) view.findViewById(R.id.content);*/
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /* int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, ReceipeCardActivity.class));
            return true;
        }*/


        onBackPressed();
        return true;
    }
}
