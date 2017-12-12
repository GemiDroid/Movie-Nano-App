package com.orchtech.baking_app.widget;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by hossam on 06/12/17.
 */

public class ListViewRemoveViewsService extends RemoteViewsService {

    /*public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {

        int appWidgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        return new ListViewRemoteViewsFactory(this.getApplicationContext(), intent);

    }*/


    @Override
    public RemoteViewsService.RemoteViewsFactory onGetViewFactory(Intent intent) {

        int appWidgetId = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);

        return (new ListViewRemoteViewsFactory(this.getApplicationContext(), intent));
    }

}