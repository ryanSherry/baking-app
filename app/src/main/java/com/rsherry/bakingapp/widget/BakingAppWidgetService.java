package com.rsherry.bakingapp.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.rsherry.bakingapp.data.Ingredients;

import java.util.List;

import android.content.Context;
import android.widget.RemoteViews;

import com.rsherry.bakingapp.R;

public class BakingAppWidgetService extends RemoteViewsService {
    List<Ingredients> mIngredients;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new BakingAppWidgetRemoteViewsFactory(getApplicationContext(), intent);
    }

    class BakingAppWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
        private Context mContext;

        public BakingAppWidgetRemoteViewsFactory(Context context, Intent intent) {
            mContext = context;

        }

        @Override
        public void onCreate() {
        }

        @Override
        public void onDataSetChanged() {
            mIngredients = BakingAppWidgetProvider.mWidgetIngredients;
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return mIngredients.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.ingredient_baking_app_widget);
            if (mIngredients != null) {
                remoteViews.setTextViewText(R.id.widget_ingredient_name, mIngredients.get(position).getIngredient());
                String quantityMeasure = mIngredients.get(position).getMquantity() + " " + mIngredients.get(position).getMeasure();
                remoteViews.setTextViewText(R.id.widget_ingredient_quantity_measure, quantityMeasure);
            }
            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
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
        public boolean hasStableIds() {
            return true;
        }
    }
}

