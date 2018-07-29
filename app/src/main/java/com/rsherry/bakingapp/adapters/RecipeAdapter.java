package com.rsherry.bakingapp.adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rsherry.bakingapp.R;
import com.rsherry.bakingapp.fragments.RecipeListFragment;
import com.rsherry.bakingapp.data.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<Recipe> mRecipes;
    private final RecipeListFragment.OnRecipeSelectedInterface mListener;

    public RecipeAdapter(List<Recipe> recipes, RecipeListFragment.OnRecipeSelectedInterface listener) {
        mRecipes = recipes;
        mListener = listener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        if (mRecipes != null) {
            return mRecipes.size();
        } else {
            return 0;
        }
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Bind views here
        @BindView(R.id.recipe_name)
        TextView mRecipeName;
        @BindView(R.id.recipe_image)
        ImageView mRecipeImage;
        private int mIndex;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position) {
            mIndex = position;
            Recipe recipe = mRecipes.get(position);
            Uri uri = Uri.parse(recipe.getImage());

            Picasso.get().load(uri)
                    .error(R.drawable.no_image_available)
                    .into(mRecipeImage);
            mRecipeName.setText(recipe.getName());
        }

        @Override
        public void onClick(View v) {
            mListener.onListRecipeSelected(mIndex);
        }
    }

    public void setRecipes(List<Recipe> recipeList) {
        mRecipes = recipeList;
        notifyDataSetChanged();
    }


}
