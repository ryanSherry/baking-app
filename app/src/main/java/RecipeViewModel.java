import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.rsherry.bakingapp.data.Recipe;

import java.util.List;

public class RecipeViewModel extends ViewModel {
    private MutableLiveData<List<Recipe>> mRecipes;
    public LiveData<List<Recipe>> getRecipes() {
        if (mRecipes == null) {
            mRecipes = new MutableLiveData<List<Recipe>>();

        }
        return mRecipes;
    }
}
