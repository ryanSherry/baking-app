package com.rsherry.bakingapp.Adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rsherry.bakingapp.R;
import com.rsherry.bakingapp.data.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<Recipe> mRecipes;

    public RecipeAdapter(List<Recipe> recipes) {
        mRecipes = recipes;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = mRecipes.get(position);
        Uri uri = Uri.parse(recipe.getImage());

        Picasso.get().load(uri)
                .error(R.drawable.no_image_available)
                .into(holder.mRecipeImage);
        holder.mRecipeName.setText(recipe.getName());
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        //Bind views here
        @BindView(R.id.recipe_name)
        TextView mRecipeName;
        @BindView(R.id.recipe_image)
        ImageView mRecipeImage;

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
