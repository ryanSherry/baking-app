package com.rsherry.bakingapp.widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.rsherry.bakingapp.MainActivity;
import com.rsherry.bakingapp.data.Ingredients;

import java.util.ArrayList;
import java.util.List;

public class UpdateAppWidgetService extends IntentService {
    public static final String INGREDIENT_LIST = "ingredient_list";
    private static List<Ingredients> mIngredients;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public UpdateAppWidgetService() {
        super("UpdateAppWidgetService");
    }

    public static void startActionUpdateAppWidgetService(Context context, List<Ingredients> list) {
        Intent intent = new Intent(context, UpdateAppWidgetService.class);
        intent.putParcelableArrayListExtra(INGREDIENT_LIST, (ArrayList<Ingredients>) list);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            mIngredients = intent.getParcelableArrayListExtra(INGREDIENT_LIST);
            handleActionUpdateList(mIngredients);
        }
    }

    private void handleActionUpdateList(List<Ingredients> list) {
        Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putParcelableArrayListExtra(INGREDIENT_LIST, (ArrayList<Ingredients>) list);
        sendBroadcast(intent);
    }
}
