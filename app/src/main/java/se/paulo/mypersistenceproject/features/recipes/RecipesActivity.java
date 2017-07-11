package se.paulo.mypersistenceproject.features.recipes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.List;

import io.realm.OrderedRealmCollection;
import se.paulo.mypersistenceproject.R;
import se.paulo.mypersistenceproject.db.RecipesDataProvider;
import se.paulo.mypersistenceproject.db.TopsyTurveyDataSource;
import se.paulo.mypersistenceproject.models.Recipe;

public class RecipesActivity extends AppCompatActivity {

    private static final String TAG = RecipesActivity.class.getSimpleName();

    private RecyclerView recipesRecyclerView;
    private TopsyTurveyDataSource dataSource;
    private RecipesAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recipesRecyclerView = (RecyclerView) findViewById(R.id.recipes_recycler_view);


        dataSource = new TopsyTurveyDataSource();
        dataSource.open();

        setupRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        dataSource.createRecipe(RecipesDataProvider.recipesList.get(0));

        /**Create a 3 seconds delay time between views..*/
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                dataSource.createRecipe(RecipesDataProvider.recipesList.get(1));
//            }
//        }, 3000);

        for (Recipe recipe : RecipesDataProvider.recipesList) {
            dataSource.createRecipe(recipe);
        }

        List<Recipe> allRecipes = dataSource.getAllRecipes();
        for (Recipe recipe : allRecipes) {
            Log.i(TAG, "Recipe: " + recipe);
        }

//        dataSource.deleteRecipe(allRecipes.get(0));
//        dataSource.deleteAllRecipe();

        dataSource.modifyDescription();

        Recipe unManaged = new Recipe("Red Velvet", "Yummy!", R.drawable.cake_2, 5);
        unManaged.setId(allRecipes.get(0).getId());

        dataSource.createRecipe(unManaged);

    }




    @Override
    protected void onDestroy() {
        dataSource.close();
        super.onDestroy();
    }


    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recipesRecyclerView.setLayoutManager(layoutManager);

        recipesRecyclerView.setHasFixedSize(true);

        recipeAdapter = new RecipesAdapter((OrderedRealmCollection<Recipe>) dataSource.getAllRecipes(), true);
        recipesRecyclerView.setAdapter(recipeAdapter);

    }

}
