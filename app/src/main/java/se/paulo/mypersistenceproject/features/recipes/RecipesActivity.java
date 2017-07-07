package se.paulo.mypersistenceproject.features.recipes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.List;

import se.paulo.mypersistenceproject.R;
import se.paulo.mypersistenceproject.db.RecipesDataProvider;
import se.paulo.mypersistenceproject.db.TopsyTurveyDataSource;
import se.paulo.mypersistenceproject.models.Recipe;

public class RecipesActivity extends AppCompatActivity {

    private static final String TAG = RecipesActivity.class.getSimpleName();

    private RecyclerView recipesRecyclerView;
    private RecipesAdapter adapter;
    private TopsyTurveyDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recipesRecyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);

        dataSource = new TopsyTurveyDataSource(this);
        setupRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataSource.open();

        for (Recipe recipe : RecipesDataProvider.recipesList) {
            dataSource.createRecipe(recipe);
        }

        List<Recipe> allRecipes = getRecipes();
        Recipe updatedRecipe = allRecipes.get(0);
        updatedRecipe.setDescription("Wonderful, yellow cake!");
        dataSource.updateRecipe(updatedRecipe);
//        dataSource.deleteRecipe(updatedRecipe);
//        dataSource.deleteAllRecipe();

        getRecipes();
    }

    private List<Recipe> getRecipes(){
        List<Recipe> allRecipes = dataSource.getAllRecipes();
        for (Recipe recipe : allRecipes) {
            Log.i(TAG, "The recipe: " + recipe);
        }
        adapter.setRecipes(dataSource.getAllRecipes());

        return allRecipes;
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recipesRecyclerView.setLayoutManager(layoutManager);

        recipesRecyclerView.setHasFixedSize(true);

        adapter = new RecipesAdapter(this);
        recipesRecyclerView.setAdapter(adapter);
    }

}
