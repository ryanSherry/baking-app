package com.rsherry.bakingapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.rsherry.bakingapp.data.Recipe;

import java.util.List;

import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<Recipe> mRecipes;

    public RecipeAdapter(List<Recipe> recipes) {
        mRecipes = recipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate view here then return it
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = mRecipes.get(position);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        //Bind views here

       public RecipeViewHolder(View itemView) {
           super(itemView);
           ButterKnife.bind(this, itemView);
       }
   }

   public void setRecipes(List<Recipe> recipeList){
        mRecipes = recipeList;
        notifyDataSetChanged();
   }
}
