package com.rsherry.bakingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.Toast;

import com.rsherry.bakingapp.data.Recipe;
import com.rsherry.bakingapp.networking.GetEndpointData;
import com.rsherry.bakingapp.networking.RetrofitClientInstance;

import java.io.IOException;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    List<Recipe> mRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Logging with Timber

        Timber.plant(new Timber.DebugTree());
        getRecipes();
    }

    public void getRecipes() {
        GetEndpointData service = RetrofitClientInstance.getRetrofitInstance().create(GetEndpointData.class);
        retrofit2.Call<List<Recipe>> call = service.getRecipeResults();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Recipe>> call, Response<List<Recipe>> response) {
                mRecipes = response.body();
            }

            @Override
            public void onFailure(retrofit2.Call<List<Recipe>> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(getApplicationContext(), "There is currently no network connection", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "This is a software bug. Please contact the developer of this app to                        investigate", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
