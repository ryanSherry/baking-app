package com.rsherry.bakingapp.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.rsherry.bakingapp.R;
import com.rsherry.bakingapp.data.Ingredients;

import java.util.List;

import static android.appwidget.AppWidgetManager.ACTION_APPWIDGET_UPDATE;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidgetProvider extends AppWidgetProvider {

    static List<Ingredients> mWidgetIngredients;

    @Override
    public void onReceive(Context context, Intent intent) {

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, BakingAppWidgetProvider.class));

        if(intent.getAction().equals(ACTION_APPWIDGET_UPDATE)) {
            mWidgetIngredients = intent.getParcelableArrayListExtra(UpdateAppWidgetService.INGREDIENT_LIST);
            if (mWidgetIngredients != null) {
                for (int appWidgetId : appWidgetIds) {
                    appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widgetListView);
                    updateAppWidget(context, appWidgetManager, appWidgetId);
                }
            }
        }
        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        if (mWidgetIngredients != null) {
            for(int appWidgetId : appWidgetIds) {
                appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widgetListView);
                updateAppWidget(context, appWidgetManager, appWidgetId);
            }
        } else {
            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);
            views.setEmptyView(R.id.widgetListView, R.id.empty_view);
            for(int appWidgetId : appWidgetIds) {
                appWidgetManager.updateAppWidget(appWidgetId, views);
            }
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget);

        Intent serviceIntent = new Intent(context, BakingAppWidgetService.class);
        views.setRemoteAdapter(R.id.widgetListView, serviceIntent);

        views.setEmptyView(R.id.widgetListView, R.id.empty_view);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

