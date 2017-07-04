package se.paulo.mypersistenceproject.features.recipes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }

    private void setupRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recipesRecyclerView.setLayoutManager(layoutManager);

        recipesRecyclerView.setHasFixedSize(true);
        adapter = new RecipesAdapter(this);
        recipesRecyclerView.setAdapter(adapter);
    }


}
