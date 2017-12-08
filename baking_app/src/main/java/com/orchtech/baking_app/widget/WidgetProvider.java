/*
package com.orchtech.baking_app.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.orchtech.baking_app.R;

*/
/**
 * Created by pc on 12/08/2017.
 *//*





    public class WidgetProvider extends AppWidgetProvider {

        @Override
        public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
            final int N = appWidgetIds.length;
            ComponentName component;
            for (int i = 0; i < N; ++i) {
                RemoteViews remoteViews = updateWidgetListView(context,
                        appWidgetIds[i]);
                appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);

                component=new ComponentName(context,WidgetProvider.class);
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.appwidget_text);
                appWidgetManager.updateAppWidget(component, remoteViews);
            }

            super.onUpdate(context, appWidgetManager, appWidgetIds);
        }

        private RemoteViews updateWidgetListView(Context context, int appWidgetId) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.baking);

            Intent svcIntent = new Intent(context, WidgetService.class);
            svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
            remoteViews.setRemoteAdapter(R.id.appwidget_text, svcIntent);
            remoteViews.setEmptyView(R.id.appwidget_text, R.id.empty_view);
            return remoteViews;
        }


}
*/
