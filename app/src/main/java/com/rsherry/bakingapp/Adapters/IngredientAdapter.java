package com.rsherry.bakingapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rsherry.bakingapp.R;
import com.rsherry.bakingapp.data.Ingredients;
import com.rsherry.bakingapp.data.Recipe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    private List<Ingredients> mIngredients;

    public IngredientAdapter(List<Ingredients> ingredients) {
        mIngredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return mIngredients.size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ingredient_checkbox) CheckBox mIngredientCheckbox;
        @BindView(R.id.ingredient_quantity_measure) TextView mIngredientMeasure;
        @BindView(R.id.ingredient_name) TextView mIngredientName;
        private int mIndex;

        public IngredientViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bindView(int position) {
            Ingredients ingredient = mIngredients.get(position);
            mIndex = position;
            String ingredientText = ingredient.getMquantity() + " " + ingredient.getMeasure() + " ";
            mIngredientMeasure.setText(ingredientText);
            mIngredientName.setText(ingredient.getIngredient());
        }
    }
    public void setIngredients(List<Ingredients> ingredients) {
        mIngredients = ingredients;
        notifyDataSetChanged();
    }
}
