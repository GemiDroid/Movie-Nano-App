package com.orchtech.baking_app.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import com.orchtech.baking_app.R;
import com.orchtech.baking_app.models.IngredientsModel;
import com.orchtech.baking_app.ui.activities.BakingCardActivity;


/**
 * Created by pc on 06/12/17.
 */

public class ListViewRemoteViewsFactory implements RemoteViewsFactory {


   /* private Context mContext;

//            private ArrayList<String> records;

    private BakingModel baking_object;
    private int appWidgetId;


    public ListViewRemoteViewsFactory(Context context, Intent intent) {

        mContext = context;

        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

    }




    // Initialize the data set.
    @Override
    public void onCreate() {

        // In onCreate() you set up any connections / cursors to your data source. Heavy lifting,

        // for example downloading or creating content etc, should be deferred to onDataSetChanged()

        // or getViewAt(). Taking more than 20 seconds in this call will result in an ANR.

//                records=new ArrayList/<String>();

        baking_object=new BakingModel(); 

    }



    // Given the position (index) of a WidgetItem in the array, use the item's text value in

    // combination with the app widget item XML file to construct a RemoteViews object.

    @Override
    public RemoteViews getViewAt(int position) {

        // position will always range from 0 to getCount() - 1.

        // Construct a RemoteViews item based on the app widget item XML file, and set the

        // text based on the position.
        final RemoteViews remoteView = new RemoteViews(
                mContext.getPackageName(), R.layout.recipe_ingredient_widget_list_item);
        IngredientsModel listItem = baking_object.getIngredientsModels().get(position);
        remoteView.setTextViewText(R.id.quantity_tv, String.valueOf(listItem.getQuantity()));
        remoteView.setTextViewText(R.id.measure_tv, listItem.getMeasure());
        remoteView.setTextViewText(R.id.ingredient_tv, listItem.getIngredient());


        // feed row

        // end feed row

        // Next, set a fill-intent, which will be used to fill in the pending intent template

        // that is set on the collection view in ListViewWidgetProvider.

        Bundle extras = new Bundle();

        extras.putInt(SimpleAppWidgetProvider.EXTRA_ITEM, position);

//                Intent fillInIntent = new Intent();
//
//                fillInIntent.putExtra("homescreen_meeting",listItem);
//
//                fillInIntent.putExtras(extras);

        // Make it possible to distinguish the individual on-click

        // action of a given item

//                rv.setOnClickFillInIntent(R.id.item_layout, fillInIntent);

        // Return the RemoteViews object.

        return remoteView;

    }

    @Override
    public int getCount() {

        Log.e("data_size=", baking_object.getIngredientsModels().size() + "");

        return baking_object.getIngredientsModels().size();

    }

    @Override
    public void onDataSetChanged() {

        // Fetching JSON data from server and add them to records arraylist


    }

    @Override
    public int getViewTypeCount() {

        return 1;

    }


    @Override
    public long getItemId(int position) {

        return position;

    }

    @Override
    public void onDestroy() {

//                records.clear();

    }

    @Override
    public boolean hasStableIds() {

        return true;

    }

    @Override
    public RemoteViews getLoadingView() {

        return null;

    }
*/


  /*  private ArrayList<IngredientsModel> listItemList = new ArrayList();*/

      /*BakingCardActivity.BakingModel*/


    private Context context = null;
    private int appWidgetId;

    public ListViewRemoteViewsFactory(Context context, Intent intent) {
        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);


//        listItemList = intent.getParcelableArrayListExtra("");

        //  BakingCardActivity.bakingModel=new BakingModel();

        // populateListItem();
    }

    private void populateListItem() {
        for (int i = 0; i < 15; i++) {
            IngredientsModel listItem = new IngredientsModel();


            listItem.setIngredient("");
            listItem.setMeasure("");
            listItem.setQuantity("");
           /* listItemList.add(listItem);*/
        }

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {


    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
//        return listItemList.size();

        if(BakingCardActivity.ingerdientList != null) {

            if (BakingCardActivity.ingerdientList.size() > 0) {
                return BakingCardActivity.ingerdientList.size();
            }
        }

        return 0;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    /*
    *Similar to getView of Adapter where instead of View
    *we return RemoteViews
    *
    */
    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.recipe_ingredient_widget_list_item);
//        IngredientsModel listItem = listItemList.get(position);
        IngredientsModel listItem = BakingCardActivity.ingerdientList.get(position);
        remoteView.setTextViewText(R.id.ingredient_tv, "Ingredient: " + listItem.getIngredient());
        remoteView.setTextViewText(R.id.measure_tv, "Measure: " + listItem.getMeasure());
        remoteView.setTextViewText(R.id.quantity_tv, "Quantity: " + listItem.getQuantity());

        return remoteView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }


}