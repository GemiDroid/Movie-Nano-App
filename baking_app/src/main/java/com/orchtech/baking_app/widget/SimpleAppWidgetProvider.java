package com.orchtech.baking_app.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.orchtech.baking_app.R;


/**
 * Created by pc on 06/12/17.
 */

public class SimpleAppWidgetProvider extends AppWidgetProvider {


    public static void sendRefreshBroadcast(Context context) {
        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.setComponent(new ComponentName(context, SimpleAppWidgetProvider.class));
        context.sendBroadcast(intent);
    }

  public static final String UPDATE_MEETING_ACTION = "android.appwidget.action.APPWIDGET_UPDATE";
  /*  public static final String EXTRA_ITEM = "com.example.edockh.EXTRA_ITEM";

    public SimpleAppWidgetProvider() {
        super();
    }



    @Override
    public void onReceive(Context context, Intent intent) {

        AppWidgetManager mgr = AppWidgetManager.getInstance(context);

        if (intent.getAction().equals(UPDATE_MEETING_ACTION)) {

            int appWidgetIds[] = mgr.getAppWidgetIds(new ComponentName(context, SimpleAppWidgetProvider.class));

            Log.e("received", intent.getAction());

            mgr.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.appwidget_list);


        }

        super.onReceive(context, intent);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,

                         int[] appWidgetIds) {


        // update each of the app widgets with the remote adapter

        for (int i = 0; i < appWidgetIds.length; ++i) {


            // Set up the intent that starts the ListViewService, which will

            // provide the views for this collection.

            Intent intent = new Intent(context, ListViewRemoveViewsService.class);

            // Add the app widget ID to the intent extras.

            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);

            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

            // Instantiate the RemoteViews object for the app widget layout.

            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.simple_app_widget_info);

            // Set up the RemoteViews object to use a RemoteViews adapter.

            // This adapter connects

            // to a RemoteViewsService  through the specified intent.

            // This is how you populate the data.

          *//*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {*//*
//               setRemoteAdapter(context, rv);
//            } else {
//                setRemoteAdapterV11(context, rv);
//            }

            rv.setRemoteAdapter(appWidgetIds[i], R.id.appwidget_list, intent);

            // Trigger listview item click

            Intent startActivityIntent = new Intent(context, RecipeDetailActivity.class);

            PendingIntent startActivityPendingIntent = PendingIntent.getActivity(context, 0, startActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            rv.setPendingIntentTemplate(R.id.appwidget_list, startActivityPendingIntent);

            // The empty view is displayed when the collection has no items.

            // It should be in the same layout used to instantiate the RemoteViews  object above.

         //   rv.setEmptyView(R.id.appwidget_list, R.id.empty_view);

            //

            // Do additional processing specific to this app widget...

            //

            appWidgetManager.updateAppWidget(appWidgetIds[i], rv);

        }

        super.onUpdate(context, appWidgetManager, appWidgetIds);

    }*/



    /**
     * this method is called every 30 mins as specified on widgetinfo.xml
     * this method is also called on every phone reboot
     **/

    @Override
    public void onUpdate(Context context, AppWidgetManager
            appWidgetManager, int[] appWidgetIds) {

/*int[] appWidgetIds holds ids of multiple instance
 * of your widget
 * meaning you are placing more than one widgets on
 * your homescreen*/
        final int N = appWidgetIds.length;
        for (int i = 0; i <N; ++i) {
            RemoteViews remoteViews = updateWidgetListView(context,
                    appWidgetIds[i]);
            appWidgetManager.updateAppWidget(appWidgetIds[i],
                    remoteViews);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private RemoteViews updateWidgetListView(Context context,
                                             int appWidgetId) {

        //which layout to show on widget
        RemoteViews remoteViews = new RemoteViews(
                context.getPackageName(), R.layout.baking);

        //RemoteViews Service needed to provide adapter for ListView
        Intent svcIntent = new Intent(context, ListViewRemoveViewsService.class);
        //passing app widget id to that RemoteViews Service
        svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        //setting a unique Uri to the intent
        //don't know its purpose to me right now
        svcIntent.setData(Uri.parse(
                svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
        //setting adapter to listview of the widget
        remoteViews.setRemoteAdapter(appWidgetId, R.id.appwidget_list,
                svcIntent);
        //setting an empty view in case of no data
        remoteViews.setEmptyView(R.id.appwidget_list, R.id.empty_view);
        return remoteViews;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        AppWidgetManager mgr = AppWidgetManager.getInstance(context);

        if (intent.getAction().equals(UPDATE_MEETING_ACTION)) {

            int appWidgetIds[] = mgr.getAppWidgetIds(new ComponentName(context, SimpleAppWidgetProvider.class));

            Log.e("received", intent.getAction());

            mgr.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.appwidget_list);


        }

        super.onReceive(context, intent);

    }

}
