package com.rsherry.bakingapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rsherry.bakingapp.adapters.IngredientAdapter;
import com.rsherry.bakingapp.activities.MainActivity;
import com.rsherry.bakingapp.R;
import com.rsherry.bakingapp.data.Ingredients;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class IngredientsFragment extends Fragment {
    @BindView(R.id.ingredientsListRecyclerView)
    RecyclerView mRecyclerView;
    List<Ingredients> mIngredients;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            mIngredients = bundle.getParcelableArrayList(MainActivity.RECIPE_INGREDIENT_LIST);
            IngredientAdapter adapter = new IngredientAdapter(mIngredients);
            mRecyclerView.setAdapter(adapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(layoutManager);
        } else {
            Timber.e("bundle sent to ingredientsFragment is null");
        }

        return view;
    }
}
